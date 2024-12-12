package c14220281.paba.latihan_room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import c14220281.paba.latihan_room.database.daftarBelanja

class adapterDaftar(private val daftarBelanja: MutableList<daftarBelanja>) :
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvItemBarang = itemView.findViewById<TextView>(R.id.tvItemBarang)
        var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.tvJumlahBarang)
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)

        var _btnEdit = itemView.findViewById<Button>(R.id.btnEdit)
        var _btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        val daftar = daftarBelanja[position]

        holder._tvTanggal.text = daftar.tanggal
        holder._tvItemBarang.text = daftar.item
        holder._tvJumlahBarang.text = daftar.jumlah

        holder._btnEdit.setOnClickListener {
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder._btnDelete.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }
}
