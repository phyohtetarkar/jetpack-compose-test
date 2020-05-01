package com.phyohtet.githubjobscompose.model

data class ApiResource<T>(
    val data: T?,
    val state: NetworkState
)