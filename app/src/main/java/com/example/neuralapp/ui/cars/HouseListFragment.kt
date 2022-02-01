package com.example.neuralapp.ui.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.neuralapp.R
import com.example.neuralapp.data.AppConst.BUNDLE_HOUSE_KEY
import com.example.neuralapp.data.model.House
import com.example.neuralapp.databinding.FragmentHouseListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseListFragment : Fragment() {

    private lateinit var binding: FragmentHouseListBinding
    private lateinit var housesAdapter: HousesAdapter

    private val viewModel: HouseListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHouseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
        viewModel.fakeList()
    }

    private fun subscribeToViewModel() {
        viewModel.houseListLiveData.observe(viewLifecycleOwner) { houses ->
            housesAdapter = HousesAdapter(
                houses = houses,
                onHouseClicked = ::navigateToDetails
            )

            binding.apply {
                noHouseFound.visibility =
                    if (houses.isEmpty()) View.VISIBLE
                    else View.GONE

                progressBar.visibility = View.GONE
                housesRecyclerView.adapter = housesAdapter
            }
        }
    }

    private fun navigateToDetails(house: House) {
        val bundle = bundleOf(BUNDLE_HOUSE_KEY to house)
        binding.root.findNavController()
            .navigate(R.id.action_houseListFragment_to_detailsFragment, bundle)
    }
}