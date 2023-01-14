package com.bgmi.tournament.bgmitournamentadmin.fragments.AddPlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bgmi.tournament.bgmitournamentadmin.R
import com.bgmi.tournament.bgmitournamentadmin.databinding.FragmentAddPlayerBinding
import com.bgmi.tournament.bgmitournamentadmin.fragments.delete.DeleteAdapter
import com.bgmi.tournament.bgmitournamentadmin.viewModel.matchViewModel


class AddPlayer : Fragment(R.layout.fragment_add_player) {

    private lateinit var binding: FragmentAddPlayerBinding
    private lateinit var viewModel:matchViewModel
    lateinit var adapter: AddPlayerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAddPlayerBinding.bind(view)

        binding.refRecyclerView.layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.refRecyclerView.setHasFixedSize(true)
        adapter = AddPlayerAdapter()
        binding.refRecyclerView.adapter=adapter

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(matchViewModel::class.java)


        viewModel.allRefId.observe(viewLifecycleOwner, Observer {
            adapter.updateRefList(it)

        })
    }


}