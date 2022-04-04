package com.example.Fitness.presentor.weight_loss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.Fitness.databinding.ActivityWeightLossBinding
import com.example.Fitness.fragments.strong.DaysFragment
import com.example.Fitness.fragments.weight_loss.DaysWeightLossFragment
import com.example.Fitness.utils.FragmentManager
import com.example.Fitness.utils.MainViewModel

class WeightLossActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeightLossBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightLossBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.pref = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysWeightLossFragment.newInstance(), this)
    }


    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysWeightLossFragment)  super.onBackPressed()
        else FragmentManager.setFragment(DaysWeightLossFragment.newInstance(), this)
    }
}