package com.bgmi.tournament.bgmitournamentadmin.fragments.delete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.MatchLayoutBinding
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class DeleteAdapter(val delete: deleteMatchData):RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder>() {


    private val allMatchData=ArrayList<createMatchModal>()

    inner class DeleteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var binding=MatchLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteViewHolder {
        val viewHolder=DeleteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_layout,parent,false))


        viewHolder.binding.btnDelete.setOnClickListener {
            delete.deleteMatch(allMatchData[viewHolder.adapterPosition])
            notifyItemRemoved(viewHolder.adapterPosition)
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: DeleteViewHolder, position: Int) {

        val matchData=allMatchData[position]

        holder.binding.fUploadDate.text=matchData.uploadDate.toString()
        holder.binding.fUploadTime.text=matchData.uploadTime.toString()
        holder.binding.fUploadRefId.text=matchData.refId.toString()
        holder.binding.fUploadSlots.text=matchData.slots.toString()
        holder.binding.fUploadMatchCategory.text=matchData.matchDuration.toString()
        holder.binding.fMatchDate.text=matchData.matchDate.toString()
        holder.binding.fMatchTime.text=matchData.matchTime.toString()
        holder.binding.fRoomId.text=matchData.roomId.toString()
        holder.binding.fRoomPass.text=matchData.roomPass.toString()
        holder.binding.map.text=matchData.matchCategory.toString()
        holder.binding.f1stPrize.text=matchData.prize.toString()
        holder.binding.f2ndPrize.text=matchData.prize.toString()
        holder.binding.f3rdPrize.text=matchData.prize.toString()
        holder.binding.fEntryPrice.text="Entery Price: ₹${matchData.matchCharge.toString()}"

        Picasso.get().load(matchData.imageUrl).into(holder.binding.ftikcetImage)

    }

    override fun getItemCount(): Int {
       return allMatchData.size
    }

    fun updateMatchList(allMatchData : List<createMatchModal>){
        this.allMatchData.clear()
        this.allMatchData.addAll(allMatchData)
        notifyDataSetChanged()
    }

}

interface deleteMatchData{
    fun deleteMatch(matchModal: createMatchModal)

}