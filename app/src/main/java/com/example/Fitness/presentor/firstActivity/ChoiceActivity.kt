package com.example.Fitness.presentor.firstActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.Fitness.databinding.ActivityChoiceBinding
import com.example.Fitness.presentor.pressActivity.PressActivity
import com.example.Fitness.presentor.strongActivty.MainActivity
import com.example.Fitness.presentor.weight_loss.WeightLossActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChoiceActivity : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){ res ->
        this.onSignInResult(res)
    }

    lateinit var binding: ActivityChoiceBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(binding.root)
        binding.imgStrong.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.imgPress.setOnClickListener {
            startActivity(Intent(this, PressActivity::class.java))
        }
        binding.imgWeightLoss.setOnClickListener {
            startActivity(Intent(this, WeightLossActivity::class.java))
        }
        database = Firebase.database.reference
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        //Create and launch
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult){
        val response = result.idpResponse
        if(result.resultCode == RESULT_OK){
            val authUser = FirebaseAuth.getInstance().currentUser
            val user = authUser?.let {
                val firebaseUser = com.example.Fitness.data.User(authUser.email.toString(), it.uid)

                database.child("users").setValue(firebaseUser)
            }



        }else{

        }
    }
}