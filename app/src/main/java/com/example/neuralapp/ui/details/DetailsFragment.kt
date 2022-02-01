package com.example.neuralapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.neuralapp.data.AppConst
import com.example.neuralapp.data.model.House
import com.example.neuralapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable(AppConst.BUNDLE_HOUSE_KEY) as? House)?.let { house ->
            binding.apply {
                title.text = "HOUSE DATA"
                houseId.text = house.id.toString()
                houseBedrooms.text = (house.subClass / 10).toString()
                houseCondition.text = (house.condition / 2).toString()
                quality.text = (house.quality / 2).toString()
                houseSurface.text = house.lotFrontage.toString()
                houseArea.text = house.lotArea.toString()
                price.text = house.price.toString()
            }
        }

    }
}