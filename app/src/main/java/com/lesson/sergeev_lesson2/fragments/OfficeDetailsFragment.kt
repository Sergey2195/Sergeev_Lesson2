package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.databinding.FragmentOfficeDetailsBinding


private const val OFFICE_NAME = "office name"
private const val OFFICE_DESCRIPTION = "office description"

class OfficeDetailsFragment : Fragment() {

    private var officeName: String? = null
    private var officeDescription: String? = null
    private var _binding: FragmentOfficeDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            officeName = it.getString(OFFICE_NAME)
            officeDescription = it.getString(OFFICE_DESCRIPTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews(){
        binding.officeName.text = officeName
        binding.officeDescription.text = officeDescription
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(officeName: String, officeDescription: String) =
            OfficeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(OFFICE_NAME, officeName)
                    putString(OFFICE_DESCRIPTION, officeDescription)
                }
            }
    }
}