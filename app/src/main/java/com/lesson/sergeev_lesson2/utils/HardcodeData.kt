package com.lesson.sergeev_lesson2.utils

import android.content.Context
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.models.ByOfficeDto
import com.lesson.sergeev_lesson2.models.OfficeDto
import com.lesson.sergeev_lesson2.models.RuOfficeDto
import com.lesson.sergeev_lesson2.models.VacancyDto
import kotlin.random.Random

object HardcodeData {
    private val directionName = arrayOf("Android", "Java", "IOS", "Flutter", "React Native", "KMM")
    fun getHardcodedListOfOffices(context: Context): List<OfficeDto> {
        return listOf(
            RuOfficeDto(
                context.getString(R.string.moscowOfficeName),
                context.getString(R.string.moscowOfficeDesription)
            ),
            ByOfficeDto(
                context.getString(R.string.gomelOfficeName),
                context.getString(R.string.gomelOfficeDesription)
            ),
            RuOfficeDto(
                context.getString(R.string.kazanOfficeName),
                context.getString(R.string.kazanOfficeDesription)
            ),
            ByOfficeDto(
                context.getString(R.string.minskOfficeName),
                context.getString(R.string.minskOfficeDesription)
            ),
            RuOfficeDto(
                context.getString(R.string.rostovOnDonOfficeName),
                context.getString(R.string.rostovOnDonOfficeDesription)
            )
        )
    }

    fun getHardcodedVacancies() = List(40){ index->
        VacancyDto(
            id = index,
            vacancyTitle = "${directionName.random()} Developer",
            vacancyDescription = "Опыт работы от ${Random.nextInt(2, 6)} лет"
        )
    }
}