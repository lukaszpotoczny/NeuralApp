package com.example.neuralapp.ui.register

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.neuralapp.R
import com.example.neuralapp.databinding.FragmentLoginBinding
import com.example.neuralapp.databinding.FragmentRegisterBinding
import com.example.neuralapp.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

        binding.registerInButton.setOnClickListener {
            val name = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            viewModel.onRegisterClick(name, password)
        }
    }

    private fun subscribeToViewModel() {
        viewModel.registerStateLiveData.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) onUserRegistered()
            else onUserExists()
        }
    }

    private fun onUserExists() {
        binding.apply {
            errorText.visibility = View.VISIBLE
            successText.visibility = View.GONE
        }
    }

    private fun onUserRegistered() {
        binding.apply {
            errorText.visibility = View.GONE
            successText.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
        }
        Handler(Looper.getMainLooper()).postDelayed({
            binding.root.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }, 3000)
    }
}