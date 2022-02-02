package com.example.neuralapp.ui.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neuralapp.data.model.House
import com.example.neuralapp.databinding.ItemHouseBinding

class HousesAdapter(
    private val houses: List<House>,
    private val onHouseClicked: (House) -> Unit
) : RecyclerView.Adapter<HousesAdapter.HouseItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseItemViewHolder {
        val binding: ItemHouseBinding = ItemHouseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HouseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseItemViewHolder, position: Int) {
        holder.bind(houses[position])
    }

    override fun getItemCount() = houses.size

    inner class HouseItemViewHolder(
        private val binding: ItemHouseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(house: House) {
            binding.apply {
                houseName.text = house.id.toString()
                housePrice.text = house.price.toString()

                root.setOnClickListener {
                    onHouseClicked.invoke(house)
                }
            }
        }
    }
}