package com.example.internalandexternalstorage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internalandexternalstorage.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addListeners()
    }


   
    private fun addListeners() {
        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            saveTextToInternalStorageFile(text)
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        binding.button2.setOnClickListener {
            val text = binding.editText.text.toString()
            if (ContextCompat.checkSelfPermission(
                    applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                saveTextToExternalStorageFile(text)
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

        }
    }

    private fun saveTextToInternalStorageFile(text: String) {
        val path = applicationInfo.dataDir
        val fileName = "test.txt"
        val file = File("$path/$fileName")
        file.writeText(text)
    }

    private fun saveTextToExternalStorageFile(text: String) {
        val path = Environment.getExternalStorageDirectory().path
        val fileName = "sample.txt"
        val file = File("$path/$fileName")
        file.writeText(text)
    }
}