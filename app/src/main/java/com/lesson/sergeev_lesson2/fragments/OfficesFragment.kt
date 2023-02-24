package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.adapters.OfficeAdapter
import com.lesson.sergeev_lesson2.databinding.FragmentOfficesBinding
import com.lesson.sergeev_lesson2.models.ByOfficeDto
import com.lesson.sergeev_lesson2.models.OfficeDto
import com.lesson.sergeev_lesson2.models.RuOfficeDto

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
        adapter.submitList(getHardcodedListOfOffices())
        adapter.clickListener = { officeDto ->
            (requireActivity() as MainActivity).openOfficeDetails(officeDto)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getHardcodedListOfOffices(): List<OfficeDto> {
        return listOf(
            RuOfficeDto(
                requireContext().getString(R.string.moscowOfficeName),
                requireContext().getString(R.string.moscowOfficeDesription)
            ),
            ByOfficeDto(
                requireContext().getString(R.string.gomelOfficeName),
                requireContext().getString(R.string.gomelOfficeDesription)
            ),
            RuOfficeDto(
                requireContext().getString(R.string.kazanOfficeName),
                requireContext().getString(R.string.kazanOfficeDesription)
            ),
            ByOfficeDto(
                requireContext().getString(R.string.minskOfficeName),
                requireContext().getString(R.string.minskOfficeDesription)
            ),
            RuOfficeDto(
                requireContext().getString(R.string.rostovOnDonOfficeName),
                requireContext().getString(R.string.rostovOnDonOfficeDesription)
            )
        )
    }

    companion object {
        fun newInstance() = OfficesFragment()
    }
}