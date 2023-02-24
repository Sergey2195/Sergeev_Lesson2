package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.adapters.VacancyAdapter
import com.lesson.sergeev_lesson2.databinding.FragmentVacanciesBinding
import com.lesson.sergeev_lesson2.utils.HardcodeData.getHardcodedVacancies

class VacanciesFragment : Fragment() {
    private var _binding: FragmentVacanciesBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = VacancyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        binding.vacanciesRecyclerView.adapter = adapter
        binding.vacanciesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false)
        adapter.submitList(getHardcodedVacancies())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = VacanciesFragment()
    }
}