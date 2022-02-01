package com.example.neuralapp.ui.add

import android.R
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.neuralapp.databinding.FragmentAddHouseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddHouseFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentAddHouseBinding

    private val viewModel: AddHouseViewModel by viewModels()

    private val conditionAdapter by lazy {
        context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, conditions) }
    }

    private val qualityAdapter by lazy {
        context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, qualityState) }
    }

    private var conditions = arrayOf("Inhabitable", "Poor", "Decent", "Good", "Excellent")
    private var qualityState = arrayOf("Tragic", "Old", "Normal", "Good", "Great")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddHouseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinners()
        subscribeToViewModel()

        binding.apply {
            calculateButton.setOnClickListener {
                progressBar.visibility = View.VISIBLE

                Handler().postDelayed({
                    priceText.text = "Estimated house price: 69 420$"
                    progressBar.visibility = View.GONE
                    priceText.visibility = View.VISIBLE
                }, 3000)

                //TODO here
//                handleButton()
            }
        }
    }

    private fun handleButton() {
        with(binding) {
            val bedrooms = bedroomsInput.text.convertToInt()
            val surface = surfaceInput.text.convertToInt()
            val outsideArea = outsideInput.text.convertToInt()
            val condition = conditionSpinner.selectedItemPosition * 2
            val quality = qualitySpinner.selectedItemPosition * 2

            viewModel.calculateHousePrice(
                bedrooms = bedrooms,
                surface = surface,
                outsideArea = outsideArea,
                condition = condition,
                quality = quality
            )
        }
    }

    private fun subscribeToViewModel() {
        viewModel.priceLiveData.observe(viewLifecycleOwner) { price ->
            binding.apply {
                priceText.text = "Estimated house price: $price"
                progressBar.visibility = View.GONE
                priceText.visibility = View.VISIBLE
            }
        }
    }

    private fun Editable?.convertToInt(): Int {
        return this?.toString()?.toIntOrNull() ?: 0
    }

    private fun setSpinners() {
        conditionAdapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        qualityAdapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        with(binding.conditionSpinner)
        {
            adapter = conditionAdapter
            setSelection(2, false)
            onItemSelectedListener = this@AddHouseFragment
            prompt = "Select"
            gravity = Gravity.CENTER

        }

        with(binding.qualitySpinner)
        {
            adapter = qualityAdapter
            setSelection(2, false)
            onItemSelectedListener = this@AddHouseFragment
            gravity = Gravity.CENTER
            prompt = "Select"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
}