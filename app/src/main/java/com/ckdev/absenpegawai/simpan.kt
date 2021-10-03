package com.ckdev.absenpegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ckdev.absenpegawai.databinding.ActivitySimpanBinding
import com.androidnetworking.error.ANError

import org.json.JSONObject

import com.androidnetworking.interfaces.JSONObjectRequestListener

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority


class simpan : AppCompatActivity() {
    private lateinit var binding: ActivitySimpanBinding
    var jrs = "IPA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySimpanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSimpan.setOnClickListener{
            val nama=binding.etNama.text.toString()
            val mapel=binding.etMapel.text.toString()
            if (nama.isEmpty()){
                binding.etNama.error="kosong"
                binding.etNama.requestFocus()
            }else if (mapel.isEmpty()){
                binding.etNama.error="kosong"
                binding.etNama.requestFocus()
            }else{
                //simpandata
                AndroidNetworking.post("http://192.168.43.2/api/simpan.php")
                    .addBodyParameter("nama", nama)
                    .addBodyParameter("jurusan", jrs)
                    .addBodyParameter("mapel",mapel)
                  
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            if(response.getInt("succes")==1){
                                Toast.makeText(this@simpan,response.getString("pesan"),Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@simpan,response.getString("pesan"),Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(error: ANError) {
                            Toast.makeText(this@simpan,error.toString(),Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
        binding.rgJurusan.setOnCheckedChangeListener { radioGroup, checkId ->
            if (checkId==binding.rbIPA.id){
                jrs="IPA"
            }else{
                jrs="IPS"
            }
        }
    }
}