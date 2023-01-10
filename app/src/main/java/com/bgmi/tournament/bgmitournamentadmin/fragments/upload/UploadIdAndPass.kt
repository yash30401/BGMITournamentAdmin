package com.bgmi.tournament.bgmitournamentadmin.fragments.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentUploadIdAndPassBinding


class UploadIdAndPass : Fragment(R.layout.fragment_upload_id_and_pass) {

    private lateinit var binding:FragmentUploadIdAndPassBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUploadIdAndPassBinding.bind(view)
    }

}