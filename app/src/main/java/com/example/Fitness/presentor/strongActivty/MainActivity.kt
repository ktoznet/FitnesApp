package com.example.Fitness.presentor.strongActivty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.Fitness.databinding.ActivityMainBinding
import com.example.Fitness.fragments.strong.DaysFragment
import com.example.Fitness.utils.FragmentManager
import com.example.Fitness.utils.MainViewModel
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseMessaging.getInstance().token.addOnCompleteListener{task ->
            if(!task.isSuccessful){
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("TAG","Token -> $token")
        }
        model.pref = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }


    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysFragment)  super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}