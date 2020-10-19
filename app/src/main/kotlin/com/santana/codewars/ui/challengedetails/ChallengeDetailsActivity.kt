package com.santana.codewars.ui.challengedetails

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.santana.codewars.R
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.state.StateResponse
import com.santana.codewars.ui.userchallenges.ChallengesActivity.Companion.CHALLENGE_SELECTED
import com.santana.codewars.utils.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDetailsActivity: AppCompatActivity() {

    private val viewModel: ChallengeDetailsViewModel by viewModels()
    private val challengeId by lazy { intent.getStringExtra(CHALLENGE_SELECTED) }

    private val clDetails by lazy { findViewById<ConstraintLayout>(R.id.clDetails) }
    private val pbDetails by lazy { findViewById<ProgressBar>(R.id.pbDetails) }
    private val tvChallenge by lazy { findViewById<TextView>(R.id.tvChallenge) }
    private val tvSlug by lazy { findViewById<TextView>(R.id.tvSlug) }
    private val tvCategory by lazy { findViewById<TextView>(R.id.tvCategory) }
    private val tvPublishedAt by lazy { findViewById<TextView>(R.id.tvPublishedAt) }
    private val tvApprovedAt by lazy { findViewById<TextView>(R.id.tvApprovedAt) }
    private val tvLanguages by lazy { findViewById<TextView>(R.id.tvLanguages) }
    private val tvDescription by lazy { findViewById<TextView>(R.id.tvDescription) }
    private val tvTotalAttempts by lazy { findViewById<TextView>(R.id.tvTotalAttempts) }
    private val tvTotalCompleted by lazy { findViewById<TextView>(R.id.tvTotalCompleted) }
    private val tvTotalStars by lazy { findViewById<TextView>(R.id.tvTotalStars) }
    private val tvVoteScore by lazy { findViewById<TextView>(R.id.tvVoteScore) }
    private val tvTags by lazy { findViewById<TextView>(R.id.tvTags) }
    private val tvUnresolvedIsuues by lazy { findViewById<TextView>(R.id.tvUnresolvedIsuues) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
        setupObserver()
        viewModel.fetchChallengeDetails()
    }

    private fun setupViews() {
        showLoading()
    }

    private fun showLoading(){
        clDetails.setVisibility(false)
        pbDetails.setVisibility(true)
    }

    private fun hideLoading(){
        clDetails.setVisibility(true)
        pbDetails.setVisibility(false)
    }

    private fun setupObserver() {
        val observer = Observer<StateResponse<CodeChallengeBO>>{ state->
            when(state){
                is StateResponse.StateLoading -> showLoading()
                is StateResponse.StateSuccess -> {
                    hideLoading()
                    populateChallengeDetails(state.data)
                }
                is StateResponse.GenericError -> {
                    hideLoading()
                    showErrorMessage()
                }
            }
        }
        viewModel.challengeLiveData.observe(this, observer)
        viewModel.selectChallenge(challengeId)
    }

    private fun populateChallengeDetails(challengeDetails: CodeChallengeBO) {
        tvChallenge.text = challengeDetails.name
    }

    private fun showErrorMessage(){
        Toast.makeText(this, R.string.error_generic, Toast.LENGTH_LONG).show()
    }
}