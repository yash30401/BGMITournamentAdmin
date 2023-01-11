package com.bgmi.tournament.bgmitournamentadmin.fragments.create

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentCreateMatchBinding
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar


class CreateMatch : Fragment(R.layout.fragment_create_match) {

    private lateinit var binding:FragmentCreateMatchBinding

    private val RQ=101
    private lateinit var bitmap: Bitmap

    private lateinit var matchTimeSpinner:String
    private lateinit var matchCategory:String

    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseStorage: StorageReference

    private lateinit var progressDialog: ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCreateMatchBinding.bind(view)


        firebaseDatabase=FirebaseDatabase.getInstance().getReference("Matches")

        firebaseStorage=FirebaseStorage.getInstance().getReference().child("Matches")



        progressDialog= ProgressDialog(context)

        binding.btnUploadImage.setOnClickListener {

            if(binding.etDate.text.toString().isEmpty()){
                binding.etDate.setError("Empty Date!")
                binding.etDate.requestFocus()
            }else if(binding.etTime.text.toString().isEmpty()){
                binding.etTime.setError("Empty Time!")
                binding.etTime.requestFocus()
            }else if(binding.etRefId.text.toString().isEmpty()){
                binding.etRefId.setError("Empty Reference ID!")
                binding.etRefId.requestFocus()
            }else if(binding.etMatchCharge.text.toString().isEmpty()){
                binding.etMatchCharge.setError("Empty Match Charge!")
                binding.etMatchCharge.requestFocus()
            }else if(binding.etMaxParticipants.text.toString().isEmpty()){
                binding.etMaxParticipants.setError("Empty Slots!")
                binding.etMaxParticipants.requestFocus()
            }else if(binding.etPrizes.text.toString().isEmpty()){
                binding.etPrizes.setError("Empty Prizes!")
                binding.etPrizes.requestFocus()
            }else if(matchTimeSpinner.equals("Select Match Time")){
                Toast.makeText(context, "Please Select Match Time", Toast.LENGTH_SHORT).show()
            }else if(matchCategory.equals("Select Match Category")){
                Toast.makeText(context, "Please Select Match Category", Toast.LENGTH_SHORT).show()
            }else if(bitmap==null){
                Toast.makeText(context, "Please Select Ticket Image", Toast.LENGTH_SHORT).show()
            }else{
                uploadImage()
            }


        }


        binding.spMatchTime.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                matchTimeSpinner=binding.spMatchTime.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spMatchCategory.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                matchCategory=binding.spMatchCategory.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnUpload.setOnClickListener {
            uploadMatch()
        }

    }

    private fun uploadMatch() {
        progressDialog.setMessage("Uploading....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val byteArrayOutputStream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,58,byteArrayOutputStream)
        val finalImage=byteArrayOutputStream.toByteArray()
        val filePath=firebaseStorage.child(binding.etRefId.text.toString()+"jpg")
        val uploadTask:UploadTask=filePath.putBytes(finalImage)



        uploadTask.addOnCompleteListener(OnCompleteListener {task ->  

            if(task.isSuccessful){
                uploadTask.addOnSuccessListener {
                    filePath.downloadUrl.addOnSuccessListener {uri->

                        UploadData(uri)
                        progressDialog.dismiss()
                    }
                }
            }

        }).addOnFailureListener(OnFailureListener { fail->

            Toast.makeText(context, "Check Your Network Connection", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        })


    }

    private fun UploadData(uri: Uri?) {

        val matchDate=binding.etDate.text.toString()
        val matchTime=binding.etTime.text.toString()
        val refID=binding.etRefId.text.toString()
        val slots=binding.etMaxParticipants.text.toString()
        val matchCharge=binding.etMatchCharge.text.toString()
        val roomId="Not Available"
        val roomPass="Not Available"
        val prizes =binding.etPrizes.text.toString()

        val calendar=Calendar.getInstance()
        val currentDate=SimpleDateFormat("dd-MM-yy")
        val date=currentDate.format(calendar.time)


        val currentTime=SimpleDateFormat("hh:mm a")
        val time=currentTime.format(calendar.time)

        val matchData=createMatchModal(date,time,refID,matchCharge,slots,matchTime,matchDate,uri.toString(),matchTimeSpinner,
            matchCategory,roomId,roomPass,prizes)

        Log.d("TIME","${date.toString()}, ${time.toString()}")



        firebaseDatabase.child(matchTimeSpinner).child(refID).setValue(matchData).addOnSuccessListener {
            Toast.makeText(context, "Match Uploaded", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Check Your Connection", Toast.LENGTH_SHORT).show()
        }
    }


    private fun uploadImage() {
        val intent= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,RQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==RQ && resultCode == RESULT_OK){
            val Uri=data?.data

            try {
                bitmap=MediaStore.Images.Media.getBitmap(activity?.contentResolver,Uri)
            }catch (e:IOException){
                Log.d("Error",e.printStackTrace().toString())
            }

            binding.ticketImage.setImageBitmap(bitmap)
            binding.ticketImage.visibility=View.VISIBLE
        }
    }


}