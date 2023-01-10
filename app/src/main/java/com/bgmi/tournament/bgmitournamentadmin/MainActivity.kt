package com.bgmi.tournament.bgmitournamentadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.bgmi.tournament.bgmitournamentadmin.databinding.ActivityMainBinding
import com.bgmi.tournament.bgmitournamentadmin.fragments.create.CreateMatch
import com.bgmi.tournament.bgmitournamentadmin.fragments.delete.DeleteIdAndPass
import com.bgmi.tournament.bgmitournamentadmin.fragments.upload.UploadIdAndPass

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager=supportFragmentManager

        supportActionBar?.hide()

        binding.addplayer.setOnClickListener {
            fragmentChange(R.id.addplayer) // We Call This Function For All Click Listners
        }

        binding.createMatch.setOnClickListener {
            fragmentChange(R.id.createMatch)
        }

        binding.uploadIdPass.setOnClickListener {
            fragmentChange(R.id.uploadIdPass)
        }

        binding.DeleteIdPass.setOnClickListener {
            fragmentChange(R.id.DeleteIdPass)
        }

    }

    private fun fragmentChange(id: Int) {

        when(id){
            R.id.addplayer->{

            }
            R.id.createMatch-> {
                fragmentManager.beginTransaction().replace(R.id.homelayout, CreateMatch()).addToBackStack("home").commit()
            }
            R.id.uploadIdPass->{
                fragmentManager.beginTransaction().replace(R.id.homelayout, UploadIdAndPass()).addToBackStack("home").commit()
            }
            R.id.DeleteIdPass->{
                fragmentManager.beginTransaction().replace(R.id.homelayout, DeleteIdAndPass()).addToBackStack("home").commit()
            }
        }

    }


}
