package com.example.laboratorio4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.buttonIniciar);
        button2 = findViewById(R.id.buttonDetalles);
        button3 = findViewById(R.id.buttonDescargar);
        button4 = findViewById(R.id.buttonUbicacion);

        initListeners()
    }

    fun initListeners(){
        button.setOnClickListener{
            Toast.makeText(this, "Sebastián José Solorzano Pérez", Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener{
            val intent = Intent(this, WidgetsActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val link = "https://play.google.com/store/apps/details?id=com.whatsapp"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }

        button4.setOnClickListener {
            val location = "https://www.google.com/maps/place/Tre+Fratelli+Cayala/@14.6112743,-90.4860005,15z/data=!4m5!3m4!1s0x0:0x9922df6e14af3cb2!8m2!3d14.6112743!4d-90.4860005"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }
    }
}