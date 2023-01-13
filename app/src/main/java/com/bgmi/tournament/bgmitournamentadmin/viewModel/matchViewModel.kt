package com.bgmi.tournament.bgmitournamentadmin.viewModel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.bgmi.tournament.bgmitournamentadmin.repository.matchDataRepository

class matchViewModel:ViewModel() {

    private val repository:matchDataRepository
    private val _allMatchData = MutableLiveData<List<createMatchModal>>()
    val allMatch:LiveData<List<createMatchModal>> = _allMatchData


    init {
        repository=matchDataRepository().getInstance()
        repository.loadMatchData(_allMatchData)

    }

    fun deleteMatch(matchModal: createMatchModal, position: Int,context: Context){

        repository.DeleteMatch(matchModal,position,context)
    }

}