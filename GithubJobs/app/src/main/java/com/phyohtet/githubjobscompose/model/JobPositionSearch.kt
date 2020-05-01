package com.phyohtet.githubjobscompose.model

data class JobPositionSearch(
        var description: String = "",
        var location: String = "",
        var fullTimeOnly: Boolean = false
)