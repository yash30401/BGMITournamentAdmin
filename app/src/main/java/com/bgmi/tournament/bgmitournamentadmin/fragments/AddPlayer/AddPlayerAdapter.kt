package com.bgmi.tournament.bgmitournamentadmin.fragments.AddPlayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.AddplayerlayoutBinding
import com.bgmi.tournament.bgmitournamentadmin.modal.ReferenceIdModal
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal

class AddPlayerAdapter:RecyclerView.Adapter<AddPlayerAdapter.AddPlayerViewHolder>() {

    private val allRefData=ArrayList<ReferenceIdModal>()

    inner class AddPlayerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var binding=AddplayerlayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlayerViewHolder {
        val viewHolder=AddPlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.addplayerlayout,parent,false))

        return viewHolder
    }

    override fun getItemCount(): Int {
       return allRefData.size
    }

    override fun onBindViewHolder(holder: AddPlayerViewHolder, position: Int) {

        val refData=allRefData[position]

        holder.binding.refIdText.text=refData.refId.toString()


    }

    fun updateRefList(allRefData : List<ReferenceIdModal>){
        this.allRefData.clear()
        this.allRefData.addAll(allRefData)
        notifyDataSetChanged()
    }

}