package com.example.barbershop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.view.Home
import com.example.barbershop.view.Scheduling.Companion.RED_COLOR_POPUPS
import com.google.android.material.snackbar.Snackbar

class BarberShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener {

            val nome = binding.etNome.text.toString()
            val senha = binding.etSenha.text.toString()

            when {
                nome.isEmpty() -> {
                    mensagem(it, NOME)
                }

                senha.isEmpty() -> {
                    mensagem(it, SENHA)
                }

                senha.length <= 5 -> {
                    mensagem(it, SEIS_CARACTERES_MINIMO)
                }

                else -> {
                    navigateHome(nome)

                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint((Color.parseColor(RED_COLOR_POPUPS)))
        snackbar.setTextColor((Color.parseColor(WHITE_COLOR)))
        snackbar.show()
    }

    private fun navigateHome(nome: String) {
        val intent = Intent(this, Home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }

    companion object {
        // Preenchimento de nome, senha e aviso de 6 caracteres minimos.
        const val NOME = "Coloque o seu nome!"
        const val SENHA = "Preencha com a senha"
        const val SEIS_CARACTERES_MINIMO = "A senha precisa ter pelo menos 6 caracteres"

        //Color white snackbar
        const val WHITE_COLOR = "#FFFFFF"

    }


}