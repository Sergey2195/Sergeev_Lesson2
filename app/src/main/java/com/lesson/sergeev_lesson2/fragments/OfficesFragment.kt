package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.databinding.FragmentMainBinding
import com.lesson.sergeev_lesson2.databinding.FragmentOfficesBinding

class OfficesFragment : Fragment() {

    private var _binding: FragmentOfficesBinding? = null
    private val binding
        get() = _binding!!
    private val viewClickListener = View.OnClickListener {
        val textView = it as TextView
        (requireActivity() as MainActivity).openOfficeDetails(textView.text.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        val listViews = listOf(
            binding.MoscowTV,
            binding.GomelTV,
            binding.KazanTV,
            binding.MinskTV,
            binding.RostovOnDonTV
        )
        listViews.forEach { it.setOnClickListener(viewClickListener) }
    }

    companion object {
        fun newInstance() = OfficesFragment()
    }
}