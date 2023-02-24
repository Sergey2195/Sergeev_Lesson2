package com.lesson.sergeev_lesson2.models

data class MainScreenDto(
    val viewType: Int,
    val content: String = "",
    val contentType: Int = 0
)