package com.example.barbershop.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershop.databinding.ActivitySchedulingBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Scheduling : AppCompatActivity() {

    private lateinit var binding: ActivitySchedulingBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var day = dayOfMonth.toString()

            if (dayOfMonth < 10) {
                day = "0$dayOfMonth"
            }
            val month: String = if (monthOfYear < 10) {
                "" + (monthOfYear + 1)

            } else {
                (monthOfYear + 1).toString()
            }

            data = "$day / $month / $year"
        }

        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->

            val minuto: String = if (minute < 10) {
                "0$minute"
            } else {
                minute.toString()

            }

            hora = "$hourOfDay:$minuto" //19:00
        }

        binding.timePicker.setIs24HourView(true) //formato de hora24h

        binding.btScheduling.setOnClickListener {
            val barber1 = binding.barber1
            val barber2 = binding.barber2
            val barber3 = binding.barber3

            when {
                hora.isEmpty() -> {
                    mensagem(it, PRENCHA_O_HORARIO, RED_COLOR_POPUPS)
                }

                hora < "8:00" && hora > "19:00" -> {
                    mensagem(
                        it,
                        ESTABELECIMENTO_FECHADO_BARBER_SHOP,
                        RED_COLOR_POPUPS
                    )
                }

                data.isEmpty() -> {
                    mensagem(it, DATA_AGENDAMENTO_BARBER_SHOP, RED_COLOR_POPUPS)
                }

                barber1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, AGENDAMENTO_COM_SUCESSO_BARBER_SHOP, BLUE_GRADIENT_POPUPS)
                }

                barber2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, AGENDAMENTO_COM_SUCESSO_BARBER_SHOP, BLUE_GRADIENT_POPUPS)
                }

                barber3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it, AGENDAMENTO_COM_SUCESSO_BARBER_SHOP, BLUE_GRADIENT_POPUPS)
                }

                else -> {
                    mensagem(it, ESCOLHA_O_BARBEIRO_BARBER_SHOP, RED_COLOR_POPUPS)

                }

            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }

    companion object {
        // avisos emitidos ao tentar marcar data/horario de agendamento
        const val PRENCHA_O_HORARIO = "Prencha o horario"
        const val ESTABELECIMENTO_FECHADO_BARBER_SHOP =
            "Barber Shop Esta fechado - horario de atendimento das 08:00 as 19:00!"
        const val DATA_AGENDAMENTO_BARBER_SHOP = "Coloque uma data!"
        const val AGENDAMENTO_COM_SUCESSO_BARBER_SHOP = "Agendamento realizado com sucesso!"
        const val ESCOLHA_O_BARBEIRO_BARBER_SHOP = "Escolha um barbeiro"

        // Cores dos pop-ups disparados ao tentar realizar agendamento
        const val RED_COLOR_POPUPS = "#FF0000"
        const val BLUE_GRADIENT_POPUPS = "#FF03DAC5"

    }
}