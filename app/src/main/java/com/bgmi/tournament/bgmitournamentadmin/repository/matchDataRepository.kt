package com.bgmi.tournament.bgmitournamentadmin.repository

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bgmi.tournament.bgmitournamentadmin.modal.ReferenceIdModal
import com.bgmi.tournament.bgmitournamentadmin.modal.createMatchModal
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class matchDataRepository {

    private val databaseReference:DatabaseReference=FirebaseDatabase.getInstance().getReference("Matches")
    private val reference:DatabaseReference=FirebaseDatabase.getInstance().getReference("ReferenceID")

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

    fun DeleteMatch(matchModal: createMatchModal, position: Int,context: Context){

        val databaseReference=FirebaseDatabase.getInstance().getReference().child("Matches")
            databaseReference.child(matchModal.matchDuration!!).child(matchModal.refId!!).removeValue().addOnSuccessListener {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
            }

        val reference=FirebaseDatabase.getInstance().getReference().child("ReferenceID")
        reference.child(matchModal.refId.toString()).removeValue()


    }

    fun loadRefId(refList:MutableLiveData<List<ReferenceIdModal>>){

        reference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    var _refList:List<ReferenceIdModal> = snapshot.children.map {dataSnapshot ->
                        dataSnapshot.getValue(ReferenceIdModal::class.java)!!
                    }

                    refList.postValue(_refList)
                }catch (e:Exception){
                    Log.d("ERROR",e.printStackTrace().toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR","Something Went Wrong")
            }

        })

    }

}