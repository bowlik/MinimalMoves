package com.example.minimalmoves.data

data class Level(
    val id: Int,
    val start: Int,
    val target: Int,
    val operations: List<String>
)
