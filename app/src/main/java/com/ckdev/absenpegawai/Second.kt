package com.ckdev.absenpegawai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ckdev.absenpegawai.databinding.ActivityMainBinding
import com.ckdev.absenpegawai.databinding.ActivitySecondBinding

class Second : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
            
         binding.btInput.setOnClickListener{
             startActivity(Intent(this@Second,simpan::class.java))


         }

        binding.btTampil.setOnClickListener{
            startActivity(Intent(this@Second,Tampil::class.java))
        }
    }
}