package com.mf.distcounter.ui.stationPicker

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mf.distcounter.R
import com.mf.distcounter.databinding.StationPickerDialogBinding
import com.mf.domain.consts.PICKER_DIALOG_CODE
import com.mf.domain.consts.STATIONS_COUNT_TO_GET
import com.mf.domain.models.stationView.StationInfoModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.station_picker_dialog.*

@AndroidEntryPoint
class StationPickerDialog : DialogFragment(), StationItemClickListener {
    private var isShowing = false
    private val viewModel: StationPickerViewModel by viewModels()
    private var hostFragment: Fragment? = null
    private lateinit var binder: StationPickerDialogBinding
    private val adapter by lazy {
        StationsAdapter(this, listOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = StationPickerDialogBinding.inflate(inflater).also {
        binder = it
        binder.lifecycleOwner = viewLifecycleOwner
        binder.pickerViewModel = viewModel
        binder.dialog = this
    }.root


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setWindowAnimations(R.style.AppTheme_Slide)
        setupRecycler()
        setupObservers()
        viewModel.getStationsData()
    }

    private fun setupObservers() {
        viewModel.stationsData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if(it.isEmpty()){
                stations_picker_recycler.visibility = View.GONE
                station_picker_placeholder.visibility = View.VISIBLE
            }else{
                station_picker_placeholder.visibility = View.GONE
                stations_picker_recycler.visibility = View.VISIBLE
                if (it.size == STATIONS_COUNT_TO_GET) {
                    binder.stationsPickerRecycler.scrollToPosition(0)
                }
            }
        })

        binder.stationPickerBaseStationText.setOnFocusChangeListener { _, hasFocus ->
            viewModel.setCurrentFocus(hasFocus)
        }
        binder.stationPickerTargetStationText.setOnFocusChangeListener { _, hasFocus ->
            viewModel.setCurrentFocus(!hasFocus)
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            checkResult(it)
        })
    }

    private fun setupRecycler() {
        stations_picker_recycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        stations_picker_recycler.adapter = adapter
    }

    override fun onPause() {
        dialog?.window?.setWindowAnimations(0)
        super.onPause()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        isShowing = false
    }
    override fun dismiss() {
        dismissAllowingStateLoss()
        isShowing = false
    }

    override fun processItemClick(item: StationInfoModel) {
        viewModel.setStation(item)
    }

    override fun processAdapterLoad(position: Int) = viewModel.getStationsData(position)

    private fun checkResult(it: StationPickerUiState) {
        if (it.firstStation != null && it.secondStation != null) {
            hostFragment?.onActivityResult(
                PICKER_DIALOG_CODE,
                Activity.RESULT_OK,
                Intent().putExtra("Stations", it)
            )
            dismiss()
        }
    }

    override fun setTargetFragment(fragment: Fragment?, requestCode: Int) {
        hostFragment = fragment
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if(!isShowing){
            isShowing = true
            super.show(manager, tag)
        }

    }
}