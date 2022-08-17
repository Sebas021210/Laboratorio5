package com.example.laboratorio4

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class WidgetsActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var button2: Button
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets)

        button = findViewById(R.id.buttonLlamar);
        button2 = findViewById(R.id.buttonVista);

        initListeners()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("TAG", "${permissions[i]} was granted")
                }
            }
        }
    }

    fun initListeners() {
        button.setOnClickListener {
            val phoneNum = "+50224938037"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }

        button2.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission() =
        if (!hasCameraPermission()) {
            checkRequestRationale(android.Manifest.permission.CAMERA)
        } else{
            Toast.makeText(this, "Permiso otorgado, puedes abrir la camara", Toast.LENGTH_LONG).show()
        }

    private fun checkRequestRationale(permission: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
                Snackbar.make(rootLayout, "El acceso es necesario para abrir la camara", Snackbar.LENGTH_LONG).setAction("Ok"){
                    requestCameraPermission()
                }.show()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), 0)
    }
}