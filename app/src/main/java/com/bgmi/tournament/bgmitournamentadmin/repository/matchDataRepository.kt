package com.bgmi.tournament.bgmitournamentadmin.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.firebase.database.*

class matchDataRepository {

    private val databaseReference:DatabaseReference=FirebaseDatabase.getInstance().getReference("Matches")

    @Volatile private var INSTANCE:matchDataRepository?=null

    fun getInstance():matchDataRepository{

        return INSTANCE?: synchronized(this){
            val instance=matchDataRepository()
            INSTANCE=instance
            instance
        }

    }

    fun loadMatchData(matchList:MutableLiveData<List<createMatchModal>>){
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    var _matchList:List<createMatchModal> = snapshot.child("Morning").children.map { dataSnapshot ->
                        dataSnapshot.getValue(createMatchModal::class.java)!!

                    }
                    var _matchListMorning:List<createMatchModal> = snapshot.child("Afternoon").children.map { dataSnapshot ->
                        dataSnapshot.getValue(createMatchModal::class.java)!!
                    }

                    var _matchListEvening:List<createMatchModal> = snapshot.child("Evening").children.map { dataSnapshot ->
                        dataSnapshot.getValue(createMatchModal::class.java)!!
                    }

                    _matchList += _matchListMorning
                    _matchList += _matchListEvening
                    matchList.postValue(_matchList)


                }catch(e:Exception){
                    Log.d("ERROR",e.printStackTrace().toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR","SomeThing Went Wrong")
            }

        })

    }

}