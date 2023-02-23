package com.lesson.sergeev_lesson2

data class WatchTime(
    var hour: Int,
    var minutes: Int,
    var seconds: Int
){
    fun setTime(hour: Int, minutes: Int, seconds: Int){
        this.hour = hour
        this.minutes = minutes
        this.seconds = seconds
    }
}