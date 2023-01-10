package com.bgmi.tournament.bgmitournamentadmin.fragments.create

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentCreateMatchBinding
import java.io.IOException


class CreateMatch : Fragment(R.layout.fragment_create_match) {

    private lateinit var binding:FragmentCreateMatchBinding

    private val RQ=101
    private lateinit var bitmap: Bitmap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCreateMatchBinding.bind(view)

        binding.btnUploadImage.setOnClickListener {
            uploadImage()
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