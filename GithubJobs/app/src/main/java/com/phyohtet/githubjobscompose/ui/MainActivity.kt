package com.phyohtet.githubjobscompose.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.setContent

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        setContent {
            GithubJobsApp(viewModel.positions)
        }

        viewModel.find(1)
    }
}
