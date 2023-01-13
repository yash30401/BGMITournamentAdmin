package com.bgmi.tournament.bgmitournamentadmin.fragments.delete

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentDeleteIdAndPassBinding
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.bgmi.tournament.bgmitournamentadmin.viewModel.matchViewModel
import com.google.firebase.database.*


class DeleteIdAndPass : Fragment(R.layout.fragment_delete_id_and_pass),deleteMatchData {

    private lateinit var binding: FragmentDeleteIdAndPassBinding
    private lateinit var reference: DatabaseReference
    lateinit var adapter:DeleteAdapter
    private lateinit var viewModel:matchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDeleteIdAndPassBinding.bind(view)
        binding.progressBar.visibility=View.VISIBLE
       reference=FirebaseDatabase.getInstance().getReference()

       binding.recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        adapter = DeleteAdapter(this)
        binding.recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(matchViewModel::class.java)


        viewModel.allMatch.observe(viewLifecycleOwner, Observer {
            adapter.updateMatchList(it)
            binding.progressBar.visibility=View.GONE
        })


    }





    override fun deleteMatch(matchModal: createMatchModal, position: Int,context: Context) {

        val builder= AlertDialog.Builder(context)
        builder.setMessage("Are You Sure Want Delete This")
        builder.setCancelable(true)
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        builder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->

            viewModel.deleteMatch(matchModal,position,context)
            adapter.notifyItemRemoved(position)

        }).show()

    }




}