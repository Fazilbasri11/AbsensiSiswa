package com.ckdev.absenpegawai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnIntent : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialisation */
        val btn:Button = findViewById(R.id.btn_intent)

        btn.setOnClickListener{
            val intent= Intent(this, Second::class.java)

            startActivity(intent)
        }


    }


}