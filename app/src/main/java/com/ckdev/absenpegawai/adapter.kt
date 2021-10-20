package com.ckdev.absenpegawai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ckdev.absenpegawai.databinding.CustomtampilBinding
import org.json.JSONObject
import android.app.Activity




class adapter (private val context: Context, private  val result:ArrayList<model>): RecyclerView.Adapter<adapter.MyHolder>() {
    private var Items = ArrayList<model>()
    init {
        this.Items=result
    }
    inner class  MyHolder(val binding: CustomtampilBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            CustomtampilBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.tvNama.text=result.nama
            binding.tvMapel.text= result.mapel
            binding.tvJrs.text=result.kehadiran

            //popup

            binding.root.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Pilih Pengaturan")
                val pilihan = arrayOf("Edit", "Delete", "Cancel")
                builder.setItems(pilihan) { dialog, which ->
                    when (which) {
                        0 -> {
                            val a = Intent(context, edit::class.java)
                            a.putExtra("id", result.id_crud)
                            context.startActivity(a)
                        }

                        1 -> {
                            hapus(result.id_crud)
                        }

                        2 -> {
                            //digunakan untuk tidak memunculkan apapun
                            //cancel
                            dialog.dismiss()
                        }
                    }
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }

    fun hapus (id : String){
        AndroidNetworking.post("http://192.168.43.2/api/hapus.php")
            .addBodyParameter("id",id)

            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    if(response.getInt("succes")==1){
                        Toast.makeText(context,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                        (context as Activity).finish()
                    }else{
                        Toast.makeText(context,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(error: ANError) {
                    Toast.makeText(context,error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }
}

