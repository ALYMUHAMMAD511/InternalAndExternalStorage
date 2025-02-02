package com.example.internalandexternalstorage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internalandexternalstorage.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
            saveTextToExternalStorageFile(text)
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTextToInternalStorageFile(text: String) {
        val path = applicationInfo.dataDir
        val fileName = "test.txt"
        val file = File("$path/$fileName")
        file.writeText(text)
    }

    private fun saveTextToExternalStorageFile(text: String) {
        val path = getExternalFilesDir(null)?.path.toString()
        val fileName = "sample.txt"
        val file = File("$path/$fileName")
        file.writeText(text)
    }
}