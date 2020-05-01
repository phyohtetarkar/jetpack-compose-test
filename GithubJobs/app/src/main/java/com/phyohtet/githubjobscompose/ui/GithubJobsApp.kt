package com.phyohtet.githubjobscompose.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.ui.animation.Crossfade
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeColor
import com.phyohtet.githubjobscompose.model.ApiResource
import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO
import com.phyohtet.githubjobscompose.ui.detail.PositionDetailScreen
import com.phyohtet.githubjobscompose.ui.positions.PositionsScreen

@Composable
fun GithubJobsApp(liveData: LiveData<ApiResource<List<JobPositionDTO>>>) {

    MaterialTheme(
        colors = appThemeColors
    ) {
        AppContent(liveData)
    }

}

@Composable
private fun AppContent(liveData: LiveData<ApiResource<List<JobPositionDTO>>>) {
    Crossfade(GithubJobsStatus.currentScreen) { screen ->
        Surface(color = +themeColor { background }) {
            when (screen) {
                is Screen.Positions -> PositionsScreen(liveData)
                is Screen.PositionDetail -> PositionDetailScreen(screen.dto)
            }
        }
    }
}