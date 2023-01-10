package com.bgmi.tournament.bgmitournamentadmin.fragments.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentCreateMatchBinding


class CreateMatch : Fragment(R.layout.fragment_create_match) {


    private lateinit var binding:FragmentCreateMatchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCreateMatchBinding.bind(view)



    }

    override fun onResume() {
        super.onResume()
        if(activity!=null){
            activity?.actionBar?.show()
        }
    }

}