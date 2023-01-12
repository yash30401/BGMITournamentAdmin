package com.bgmi.tournament.bgmitournamentadmin.repository

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

    fun loadMatchData(matchList:MutableList<List<createMatchModal>>){
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    val matchList:List<createMatchModal> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(createMatchModal::class.java)!!
                    }

                }catch(e:Exception){

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}