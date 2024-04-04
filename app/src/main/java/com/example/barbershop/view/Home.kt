package com.example.barbershop.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapter.ServiceAdapter
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.model.Service

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var serviceAdapter: ServiceAdapter
    private val listService: MutableList<Service> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtUsername.text = getString(R.string.bem_vindo, nome)
        val recyclerViewService = binding.recyclerviewService
        recyclerViewService.layoutManager = GridLayoutManager(this, 2)
        serviceAdapter = ServiceAdapter(this, listService)
        recyclerViewService.setHasFixedSize(true)
        recyclerViewService.adapter = serviceAdapter
        getService()

        binding.btScheduler.setOnClickListener {
            val intent = Intent(this, Scheduling::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
        }

    }

    private fun getService() {
        val service1 = Service(R.drawable.img1, CORTE_DE_CABELO)
        listService.add(service1)

        val service2 = Service(R.drawable.img2, CORTE_DE_BARBA)
        listService.add(service2)

        val service3 = Service(R.drawable.img3, LAVAGEM_DE_CABELO)
        listService.add(service3)

        val service4 = Service(R.drawable.img4, TRATAMENTO_DE_CABELO)
        listService.add(service4)
    }

    companion object {
        const val CORTE_DE_CABELO = "Corte de Cabelo"
        const val CORTE_DE_BARBA = "Corte de Barba"
        const val LAVAGEM_DE_CABELO = "Lavagem de Cabelo"
        const val TRATAMENTO_DE_CABELO = "Tratamento de cabelo"
    }
}