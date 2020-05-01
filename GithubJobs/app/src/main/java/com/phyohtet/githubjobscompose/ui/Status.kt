package com.phyohtet.githubjobscompose.ui

import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO

sealed class Screen {
    object Positions : Screen()
    data class PositionDetail(val dto: JobPositionDTO) : Screen()
}

@Model
object GithubJobsStatus {
    var currentScreen: Screen =
        Screen.Positions
}

fun navigateTo(destination: Screen) {
    GithubJobsStatus.currentScreen = destination
}

fun <T> observe(data: LiveData<T>) = effectOf<T?> {
    val result = +state<T?> { data.value }
    val observer = +memo { Observer<T> { result.value = it } }

    +onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    result.value
}