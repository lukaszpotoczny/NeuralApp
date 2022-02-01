package com.example.neuralapp.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.neuralapp.R
import com.example.neuralapp.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("name")?.let {
            binding.titleLabel.text = "Welcome $it"
        }

        binding.buttonAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_addHouseFragment)
        }

        binding.buttonBrowser.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_houseListFragment)
        }
    }
}