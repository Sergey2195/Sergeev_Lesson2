package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.adapters.OfficeAdapter
import com.lesson.sergeev_lesson2.databinding.FragmentOfficesBinding
import com.lesson.sergeev_lesson2.utils.HardcodeData.getHardcodedListOfOffices

class OfficesFragment : Fragment() {

    private var _binding: FragmentOfficesBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = OfficeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        binding.officesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.officesRecyclerView.adapter = adapter
        adapter.submitList(getHardcodedListOfOffices(requireContext()))
        adapter.clickListener = { officeDto ->
            (requireActivity() as MainActivity).openOfficeDetails(officeDto)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = OfficesFragment()
    }
}