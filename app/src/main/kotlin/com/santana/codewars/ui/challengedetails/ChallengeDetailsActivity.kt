package com.santana.codewars.ui.challengedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santana.codewars.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}