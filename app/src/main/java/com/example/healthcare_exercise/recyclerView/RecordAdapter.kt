package com.example.healthcare_exercise.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthcare_exercise.R

class RecordAdapter (private val context: Context): RecyclerView.Adapter<RecordAdapter.ViewHolder>(){
    var datas = mutableListOf<RecordData>()

    fun setListData(data:MutableList<RecordData>){
        datas = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recored_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val num: TextView = itemView.findViewById(R.id.tx_num)
        private val result: TextView = itemView.findViewById(R.id.tx_result)
        private val part: TextView = itemView.findViewById(R.id.tx_part)
        private val date: TextView = itemView.findViewById(R.id.tx_date)


        fun bind(item: RecordData){
            num.text = item.num
            result.text = item.result
            part.text = item.method
            date.text = item.date
        }

    }
}