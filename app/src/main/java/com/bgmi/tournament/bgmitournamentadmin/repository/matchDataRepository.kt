package com.bgmi.tournament.bgmitournamentadmin.repository

import androidx.lifecycle.MutableLiveData
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.firebase.database.*

class matchDataRepository {

    private val databaseReference:DatabaseReference=FirebaseDatabase.getInstance().getReference().child("Matches")

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

                    val _matchList:List<createMatchModal> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(createMatchModal::class.java)!!
                    }

                    matchList.postValue(_matchList)

                }catch(e:Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}