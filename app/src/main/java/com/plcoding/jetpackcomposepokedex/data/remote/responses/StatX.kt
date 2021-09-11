package com.plcoding.jetpackcomposepokedex.data.remote.responses

data class StatX(
    val name: String,
    val url: String
) {
    override fun toString(): String {
        return name
    }
}