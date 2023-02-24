package com.lesson.sergeev_lesson2.models

import com.lesson.sergeev_lesson2.R

sealed class OfficeDto {
    abstract val officeName: String
    abstract val officeDetails: String
    abstract val type: Int
}

data class RuOfficeDto(
    override val officeName: String,
    override val officeDetails: String,
    override val type: Int = R.layout.ru_office_item
) : OfficeDto()

data class ByOfficeDto(
    override val officeName: String,
    override val officeDetails: String,
    override val type: Int = R.layout.by_office_item
):OfficeDto()

