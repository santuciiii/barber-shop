package com.example.barbershop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershop.databinding.ServiceItemBinding
import com.example.barbershop.model.Service

class ServiceAdapter(
    private val context: Context,
    private val listService: MutableList<Service>
) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemList = ServiceItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServiceViewHolder(itemList)
    }

    override fun getItemCount() = listService.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.imgService.setImageResource(listService[position].img!!)
        holder.txtService.text = listService[position].nome
    }

    inner class ServiceViewHolder(binding: ServiceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val imgService = binding.imgService
            val txtService = binding.txtService

    }
}