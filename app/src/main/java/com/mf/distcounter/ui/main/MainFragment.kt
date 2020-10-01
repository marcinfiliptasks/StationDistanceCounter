package com.mf.distcounter.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.libraries.maps.*
import com.google.android.libraries.maps.model.*
import com.mf.distcounter.R
import com.mf.distcounter.databinding.MainFragmentBinding
import com.mf.distcounter.ui.stationPicker.StationPickerDialog
import com.mf.distcounter.ui.stationPicker.StationPickerUiState
import com.mf.domain.consts.PICKER_DIALOG_CODE
import com.mf.domain.models.stationView.StationInfoModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*


@AndroidEntryPoint
class MainFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MainViewModel by activityViewModels()
    private var googleMap: GoogleMap? = null
    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater).also {
        it.viewModel = viewModel
        it.lifecycleOwner = viewLifecycleOwner
        it.fragment = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        mapFragment.getMapAsync(this)
    }


    private fun setupEvents() {
        viewModel.stations.observe(viewLifecycleOwner, Observer {
            it?.let { stations ->
                setVisibility(stations.first != null, stations.second != null)
                listOfNotNull(stations.first, stations.second).setMarkers()
            }
        })
    }

    private fun setVisibility(firstStation: Boolean, secondStation: Boolean) {
        if (firstStation && secondStation) {
            station_info_container.visibility = View.VISIBLE
            main_fragment_placeholder.visibility = View.GONE
        } else {
            station_info_container.visibility = View.GONE
            main_fragment_placeholder.visibility = View.VISIBLE
        }
    }

    private fun List<StationInfoModel>.setMarkers() {
        addMarkers(this)
        updateCamera(this)
    }

    private fun addMarkers(stations: List<StationInfoModel>) {
        googleMap?.apply {
            clear()
            stations.forEach { station ->
                addMarker(
                    MarkerOptions()
                        .position(LatLng(station.latitude, station.longitude))
                        .title(station.name)
                )
            }
        }
    }

    fun showDialog() =
        StationPickerDialog().apply {
            setTargetFragment(this@MainFragment, 1)

        }.show(childFragmentManager, null)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICKER_DIALOG_CODE && resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra("Stations")?.let {
                viewModel.setStations(it as StationPickerUiState)
            }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
    }

    private fun updateCamera(stations: List<StationInfoModel>) = with(LatLngBounds.builder()) {
        stations.map { LatLng(it.latitude, it.longitude) }.also { latLngs ->
            latLngs.forEach { include(it) }
            googleMap?.apply {
                val displayMetrics = DisplayMetrics()
                activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                val width = displayMetrics.widthPixels / 5
                animateCamera(CameraUpdateFactory.newLatLngBounds(build(), width))
                addPolyline(
                    PolylineOptions().addAll(latLngs)
                        .color(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                        .jointType(JointType.ROUND)
                )
            }
        }
    }


}