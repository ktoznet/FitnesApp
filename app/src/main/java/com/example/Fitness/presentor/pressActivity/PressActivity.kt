package com.example.Fitness.presentor.pressActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.Fitness.databinding.ActivityPressBinding
import com.example.Fitness.fragments.press.DaysPressFragment
import com.example.Fitness.fragments.strong.DaysFragment
import com.example.Fitness.utils.FragmentManager
import com.example.Fitness.utils.MainViewModel

class PressActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()
    lateinit var binding: ActivityPressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityPressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.pref = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysPressFragment.newInstance(), this)
    }


    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysPressFragment)  super.onBackPressed()
        else FragmentManager.setFragment(DaysPressFragment.newInstance(), this)
    }
}