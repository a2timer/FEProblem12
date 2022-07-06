package com.cartelnetwork.feproblem1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cartelnetwork.feproblem1.R
import com.cartelnetwork.feproblem1.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.container,SelectPlanetsFragment(),false)
    }
}