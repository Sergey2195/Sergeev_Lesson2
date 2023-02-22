package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.databinding.FragmentVacanciesBinding

class VacanciesFragment : Fragment() {
    private var _binding: FragmentVacanciesBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ssv", "vacanciesFragment onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance() = VacanciesFragment()
    }
}