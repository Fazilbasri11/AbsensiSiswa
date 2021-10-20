package com.ckdev.absenpegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ckdev.absenpegawai.databinding.ActivityEditBinding
import org.json.JSONObject

class edit : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    var khdr = "HADIR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cari_data(intent.getStringExtra("id").toString())
        binding.btEdit.setOnClickListener{
            val nama=binding.etNama.text.toString()
            val mapel=binding.etMapel.text.toString()
            if (nama.isEmpty()){
                binding.etNama.error="kosong"
                binding.etNama.requestFocus()
            }else if (mapel.isEmpty()){
                binding.etNama.error="kosong"
                binding.etNama.requestFocus()
            }else{
                //edit data
                AndroidNetworking.post("http://192.168.43.2/api/edit.php")
                    .addBodyParameter("id", intent.getStringExtra("id"))
                    .addBodyParameter("nama", nama)
                    .addBodyParameter("mapel",mapel)
                    .addBodyParameter("kehadiran", khdr)

                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            if(response.getInt("succes")==1){
                                Toast.makeText(this@edit,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@edit,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(error: ANError) {
                            Toast.makeText(this@edit,error.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
        binding.rgKehadiran.setOnCheckedChangeListener { radioGroup, checkId ->
            if (checkId==binding.rbHadir.id){
                khdr="HADIR"
            }else{
                khdr="ALPA"
            }
        }
    }
    fun cari_data(id: String) {
        AndroidNetworking.get("http://192.168.43.2/api/cari.php")
            .addQueryParameter("id_crud", id)

            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    if (response.getInt("success") == 1) {
                        val JSONObject = response.optJSONObject("data")
                        binding.etNama.setText(JSONObject.getString("nama"))
                        binding.etMapel.setText(JSONObject.getString("mapel"))
                        if (JSONObject.getString("kehadiran")=="HADIR"){
                            binding.rbHadir.isChecked=true
                            khdr = "HADIR"
                        }else{
                            binding.rbAlpa.isChecked=true
                            khdr = "ALPA"
                        }
                    } else {
                        Toast.makeText(this@edit, response.getString("pesan"), Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                    Toast.makeText(this@edit, error.toString(), Toast.LENGTH_SHORT).show()

                }
            })


    }}