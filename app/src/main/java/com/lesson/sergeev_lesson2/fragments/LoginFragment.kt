package com.lesson.sergeev_lesson2.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lesson.sergeev_lesson2.R
import com.lesson.sergeev_lesson2.activity.MainActivity
import com.lesson.sergeev_lesson2.databinding.FragmentLoginBinding
import com.lesson.sergeev_lesson2.viewModels.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlows()
        addListeners()
    }

    private fun addListeners() {
        binding.loginBtn.setOnClickListener {
            val inputText = binding.passwordEditText.text.toString()
            if (loginViewModel.checkPassword(inputText)){
                startMainScreen()
            }
        }
        addPasswordTextChangedListener()
        changeVisibilityIconListener()
    }

    private fun startMainScreen(){
        lifecycleScope.launch {
            binding.groupViews?.isVisible = false
            binding.progressBar?.isVisible = true
            delay(2000)
            (requireActivity() as MainActivity).startMainScreen()
        }
    }

    private fun changeVisibilityIconListener() {
        binding.visibilityImageView.setOnClickListener {
            loginViewModel.changePasswordVisibility()
        }
    }

    private fun changePasswordVisibility(isVisible: Boolean, icon: Drawable?) {
        binding.visibilityImageView.setImageDrawable(icon)
        binding.passwordEditText.transformationMethod =
            if (isVisible) null else PasswordTransformationMethod.getInstance()
    }

    private fun addPasswordTextChangedListener() {
        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loginViewModel.passwordEditTextChanged(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun changeButtonClickableAndAlpha(btnIsClickable: Boolean, btnAlpha: Float) =
        with(binding.loginBtn) {
            isClickable = btnIsClickable
            alpha = btnAlpha
        }

    private fun observeFlows() {
        lifecycleScope.launchWhenStarted {
            loginViewModel.passwordErrorStateFlow.collect { isError ->
                when (isError) {
                    true -> setError()
                    false -> setNoError()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            loginViewModel.btnCanClickedStateFlow.collect { canClick ->
                val alpha = if (canClick) 1f else 0.4f
                changeButtonClickableAndAlpha(canClick, alpha)
            }
        }
        lifecycleScope.launchWhenStarted {
            loginViewModel.passwordIsVisibleStateFlow.collect { isVisible ->
                val iconId = if (!isVisible) R.drawable.ic_invisible else R.drawable.ic_visible
                val drawable = AppCompatResources.getDrawable(requireContext(), iconId)
                changePasswordVisibility(isVisible, drawable)
            }
        }
    }


    private fun setError() = with(binding.passwordEditText) {
        error = requireContext().getString(R.string.password_error_text)
        background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.edit_text_error_background)
        requestFocus()
    }

    private fun setNoError() = with(binding.passwordEditText) {
        error = null
        background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.edit_text_background)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}