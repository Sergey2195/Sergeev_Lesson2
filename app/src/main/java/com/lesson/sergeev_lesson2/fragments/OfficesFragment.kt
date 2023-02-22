package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.databinding.FragmentMainBinding
import com.lesson.sergeev_lesson2.databinding.FragmentOfficesBinding

class OfficesFragment : Fragment() {

    private var _binding: FragmentOfficesBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ssv", "officesFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = OfficesFragment()
    }
}