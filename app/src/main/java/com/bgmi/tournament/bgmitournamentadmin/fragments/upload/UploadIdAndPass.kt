package com.bgmi.tournament.bgmitournamentadmin.fragments.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentUploadIdAndPassBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UploadIdAndPass : Fragment(R.layout.fragment_upload_id_and_pass) {

    private lateinit var binding:FragmentUploadIdAndPassBinding

    private lateinit var matchTime:String

    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUploadIdAndPassBinding.bind(view)

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Matches")

        binding.spMatchTimeUpload.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                matchTime=binding.spMatchTimeUpload.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnUploadIDPass.setOnClickListener {
            checkValidation()
        }

    }

    private fun checkValidation() {

        if (binding.etroomID.editText?.text.toString().isEmpty()) {
            binding.etroomID.error = "Empty ROOM ID!"
            binding.etroomID.requestFocus()
        } else if (binding.etroomPass.editText?.text.toString().isEmpty()) {
            binding.etroomPass.error = "Empty ROOM PASS!"
            binding.etroomPass.requestFocus()
        } else if (binding.etRefIdUpload.editText?.text.toString().isEmpty()) {
            binding.etRefIdUpload.error = "Empty Reference ID!"
            binding.etRefIdUpload.requestFocus()
        } else if (matchTime.equals("Select Match Time")) {
            Toast.makeText(context, "Please Select Match Time", Toast.LENGTH_SHORT).show()
        } else {
            uploadIdAndPass()
        }
    }

    private fun uploadIdAndPass() {

    }

}