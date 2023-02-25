package com.lesson.sergeev_lesson2.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lesson.sergeev_lesson2.adapters.VacancyAdapter
import com.lesson.sergeev_lesson2.databinding.FragmentVacanciesBinding
import com.lesson.sergeev_lesson2.utils.HideKeyboard
import com.lesson.sergeev_lesson2.viewModels.VacanciesViewModel

class VacanciesFragment : Fragment() {

    private var _binding: FragmentVacanciesBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = VacancyAdapter()
    private val viewModel: VacanciesViewModel by viewModels()

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
        observeStateFlow()
        setupClickListeners()
    }

    private fun prepareRecyclerView() {
        binding.vacanciesRecyclerView.adapter = adapter
        binding.vacanciesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        binding.vacanciesRecyclerView.addOnScrollListener(getOnScrollListener())
    }

    private fun observeStateFlow() = lifecycleScope.launchWhenCreated {
        viewModel.dataStateFlow.collect { list ->
            adapter.submitList(list)
        }
    }

    private fun setupClickListeners() {
        binding.searchVacancyEditText.addTextChangedListener(getTextChangedListener())
    }

    private fun getTextChangedListener(): TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (text == null) return
            viewModel.textFilterChanged(text.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    private fun getOnScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (checkIsNeedToHideKeyboardWhenScrolling(newState)) {
                    HideKeyboard.hideSoftKeyboard(requireContext(), requireActivity())
                }
            }
        }

    private fun checkIsNeedToHideKeyboardWhenScrolling(newState: Int): Boolean {
        return newState == RecyclerView.SCROLL_STATE_DRAGGING &&
                WindowInsetsCompat.toWindowInsetsCompat(requireView().rootWindowInsets)
                    .isVisible(WindowInsetsCompat.Type.ime())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = VacanciesFragment()
    }
}