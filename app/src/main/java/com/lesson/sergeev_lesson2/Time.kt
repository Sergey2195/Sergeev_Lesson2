package com.lesson.sergeev_lesson2

sealed class TimeWatch(value: Int) {
    data class Hour(val value:Int): TimeWatch(value)
    data class Minutes(val value: Int): TimeWatch(value)
    data class Seconds(val value: Int): TimeWatch(value)
}