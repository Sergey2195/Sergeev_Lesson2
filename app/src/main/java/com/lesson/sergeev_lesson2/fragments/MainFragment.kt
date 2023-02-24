package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lesson.sergeev_lesson2.adapters.MainScreenAdapter
import com.lesson.sergeev_lesson2.databinding.FragmentMainBinding
import com.lesson.sergeev_lesson2.utils.HardcodeData

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = MainScreenAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        binding.mainScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainScreenRecyclerView.adapter = adapter
        adapter.submitList(HardcodeData.getListOfMainScreen(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}