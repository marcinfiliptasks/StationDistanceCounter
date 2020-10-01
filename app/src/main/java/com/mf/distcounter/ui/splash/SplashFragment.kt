package com.mf.distcounter.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mf.distcounter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {


    private val viewModel: SplashViewModel by viewModels()
    private val navController by lazy { findNavController() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        viewModel.checkDb()


    }

    private fun setupEvents() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it.goForward ) {
                goToNextScreen()
            } else if ((it.keywordsDone && it.stationsDone) || (it.failure && it.dateNotNull)) {
                viewModel.setSyncDate()
                goToNextScreen()
            }

        })
    }

    private fun goToNextScreen() =
        navController.navigate(SplashFragmentDirections.actionSplashFragmentToMainFragmentToMainFragment())

}