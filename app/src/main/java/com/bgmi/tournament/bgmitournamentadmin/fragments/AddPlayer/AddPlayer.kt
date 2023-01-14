package com.bgmi.tournament.bgmitournamentadmin.fragments.AddPlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentAddPlayerBinding


class AddPlayer : Fragment(R.layout.fragment_add_player) {

    private lateinit var binding: FragmentAddPlayerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAddPlayerBinding.bind(view)


    }


}