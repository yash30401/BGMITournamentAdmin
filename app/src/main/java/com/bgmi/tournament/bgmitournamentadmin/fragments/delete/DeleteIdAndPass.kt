package com.bgmi.tournament.bgmitournamentadmin.fragments.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentDeleteIdAndPassBinding


class DeleteIdAndPass : Fragment(R.layout.fragment_delete_id_and_pass) {

    private lateinit var binding: FragmentDeleteIdAndPassBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDeleteIdAndPassBinding.bind(view)
    }



}