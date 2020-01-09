package com.example.formulario

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.formulario.constantes.constantes.Companion.EMPTY
import com.example.formulario.constantes.constantes.Companion.INTERLIN
import com.example.formulario.constantes.constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String
    private lateinit var lugarnac : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format ="MM/dd/yyyy"
                val sdf = SimpleDateFormat(format,Locale.US)
                fecha =sdf.format(cal.time).toString()
                tv_fechanac.text = fecha

            }

        }

        tv_fechanac.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        var spinner: Spinner = findViewById(R.id.sp_ciudad)
        ArrayAdapter.createFromResource(this, R.array.city_list, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }

    
        bt_enviar.setOnClickListener {


            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString().toInt()
            val contrasena = et_contrasena.text.toString()
            val recontrasena = et_recontrasena.text.toString()
            var sexo = EMPTY
            var pasatiempos = EMPTY

            lugarnac= spinner.selectedItem.toString()

            if(rb_masculino.isChecked)sexo= getString(R.string.Masculino)
            else sexo=getString(R.string.Femenino)

            if(ch_jugarf.isChecked)pasatiempos=pasatiempos + getString(R.string.jugar_futbol)
            if(ch_cine.isChecked)pasatiempos=pasatiempos + getString(R.string.cine)
            if(ch_pasearenbici.isChecked)pasatiempos=pasatiempos + getString(R.string.pasear_bici)
            if(ch_vertv.isChecked)pasatiempos=SPACE+pasatiempos + getString(R.string.ver_tv)

            //val spinner: Spinner = findViewById(R.id.spinner)


            if(  et_telefono.text.toString().isEmpty() ||  nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || contrasena.isEmpty() || sexo.isEmpty()|| pasatiempos.isEmpty() || fecha.isEmpty() || lugarnac == getString(R.string.elegirunaopcion)  ) {

                Toast.makeText(this, "Debe diligenciar todos los campos", Toast.LENGTH_SHORT).show()
            }

                else{
                    if (contrasena.length > 5) {
                        if (contrasena == recontrasena) {
                            tv_datosingresados.text =
                                getString(R.string.Nombre) + SPACE + nombre + INTERLIN +
                                        getString(R.string.Correo) + SPACE + correo + INTERLIN +
                                        getString(R.string.Telefono) + SPACE + telefono + INTERLIN +
                                        getString(R.string.Contraseña) + SPACE + contrasena + INTERLIN +
                                        getString(R.string.Sexo) + SPACE + sexo + INTERLIN +
                                        getString(R.string.Pasatiempos) + SPACE + pasatiempos +
                                        getString(R.string.Fechadenacimiento)+ SPACE+fecha+ INTERLIN+
                                        getString(R.string.Lugardenacimiento)+SPACE+lugarnac+ INTERLIN




                        } else {
                            Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "La contraseña debe tener minimo 6 digistos", Toast.LENGTH_SHORT).show()
                    }
                }




            }

        }

    }

