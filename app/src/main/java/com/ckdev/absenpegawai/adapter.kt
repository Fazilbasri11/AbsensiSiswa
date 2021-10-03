package com.ckdev.absenpegawai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.ckdev.absenpegawai.databinding.CustomtampilBinding

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
            binding.tvJrs.text=result.jurusan
            binding.tvMapel.text= result.mapel

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
                            /*delete(result.id_crud)*/
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
}

