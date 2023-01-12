package com.bgmi.tournament.bgmitournamentadmin.fragments.delete

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentDeleteIdAndPassBinding
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.firebase.database.*


class DeleteIdAndPass : Fragment(R.layout.fragment_delete_id_and_pass),deleteMatchData {

    private lateinit var binding: FragmentDeleteIdAndPassBinding
    private lateinit var reference: DatabaseReference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDeleteIdAndPassBinding.bind(view)
        binding.progressBar.visibility=View.VISIBLE
       reference=FirebaseDatabase.getInstance().getReference().child("Matches")

       binding.recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)

        val dataList = mutableListOf<createMatchModal>()
        val adapter = DeleteAdapter(this,dataList)
        binding.recyclerView.adapter=adapter



    }




    override fun deleteMatch(matchModal: createMatchModal) {

        val builder=AlertDialog.Builder(context)
        builder.setMessage("Are You Sure Want Delete This")
        builder.setCancelable(true)
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })

        builder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->

            val databaseReference=FirebaseDatabase.getInstance().getReference().child("Matches")
            databaseReference.child(matchModal.matchDuration!!).child(matchModal.refId!!).removeValue().addOnSuccessListener {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
            }

        })

    }




}