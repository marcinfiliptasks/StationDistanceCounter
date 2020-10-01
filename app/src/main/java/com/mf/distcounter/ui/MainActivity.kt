package com.mf.distcounter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mf.distcounter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}