package com.example.neuralapp.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.neuralapp.R
import com.example.neuralapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

        binding.apply {
            signInButton.setOnClickListener {
                val name = binding.usernameInput.text.toString()
                val password = binding.passwordInput.text.toString()

                viewModel.login(name, password)
            }

            registerText.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun subscribeToViewModel() {
        viewModel.loginStateLiveData.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) onLoginSuccess()
            else onLoginError()
        }
    }

    private fun onLoginError() {
        binding.apply {
            errorText.visibility = View.VISIBLE
        }
    }

    private fun onLoginSuccess() {
        binding.apply {
            errorText.visibility = View.GONE
            val bundle = bundleOf("name" to binding.usernameInput.text.toString())
            signInButton.findNavController().navigate(R.id.action_loginFragment_to_menuFragment, bundle)
        }
    }
}