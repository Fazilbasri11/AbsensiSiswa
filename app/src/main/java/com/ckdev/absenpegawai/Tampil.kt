package com.ckdev.absenpegawai

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ckdev.absenpegawai.databinding.ActivityTampilBinding
import com.androidnetworking.error.ANError

import org.json.JSONArray

import com.androidnetworking.interfaces.JSONArrayRequestListener

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONObject


class Tampil : AppCompatActivity() {
    private lateinit var binding: ActivityTampilBinding
    val result = ArrayList<model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTampilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTampil.setHasFixedSize(true)
        binding.rvTampil.layoutManager=LinearLayoutManager(this)
    }

    //untuk saat back supaya tampilan seblumnya refres
    override fun onResume() {
        super.onResume()
        tampil_data()
    }

    fun tampil_data(){
        AndroidNetworking.get("http://192.168.43.2/api/tampil.php")

            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    if (response.getInt("success") == 1){
                        val jsonArray = response.optJSONArray("data")

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.optJSONObject(i)
                            result.add(
                                model(
                                    jsonObject.getString("id_crud"),
                                    jsonObject.getString("nama"),
                                    jsonObject.getString("mapel"),
                                    jsonObject.getString("jurusan")

                                )
                            )
                        }
                        val tampil_data = adapter(this@Tampil, result)
                        binding.rvTampil.adapter = tampil_data

                    }else{
                        Toast.makeText(this@Tampil,response.getString("pesan"), Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                    Toast.makeText(this@Tampil,error.toString(), Toast.LENGTH_SHORT).show()

                }
            })

    }
}