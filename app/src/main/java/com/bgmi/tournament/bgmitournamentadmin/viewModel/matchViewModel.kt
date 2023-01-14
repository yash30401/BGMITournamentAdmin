package com.bgmi.tournament.bgmitournamentadmin.viewModel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bgmi.tournament.bgmitournamentadmin.modal.ReferenceIdModal
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.bgmi.tournament.bgmitournamentadmin.repository.matchDataRepository

class matchViewModel:ViewModel() {

    private val repository:matchDataRepository
    private val _allMatchData = MutableLiveData<List<createMatchModal>>()
    private val _allRefId = MutableLiveData<List<ReferenceIdModal>>()

    val allMatch:LiveData<List<createMatchModal>> = _allMatchData
    val allRefId:LiveData<List<ReferenceIdModal>> = _allRefId


    init {
        repository=matchDataRepository().getInstance()
        repository.loadMatchData(_allMatchData)
        repository.loadRefId(_allRefId)

    }

    fun deleteMatch(matchModal: createMatchModal, position: Int,context: Context){

        repository.DeleteMatch(matchModal,position,context)
    }

}