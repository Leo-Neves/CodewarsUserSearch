package com.santana.codewars.ui.challengedetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.santana.codewars.R
import com.santana.codewars.databinding.ActivityDetailsBinding
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.state.StateResponse
import com.santana.codewars.state.StateResponse.*
import com.santana.codewars.ui.userchallenges.ChallengesActivity.Companion.CHALLENGE_SELECTED
import com.santana.codewars.utils.setVisibility
import com.santana.codewars.utils.splitStringList
import com.santana.codewars.utils.toTimeString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDetailsActivity : AppCompatActivity() {

    private val viewModel: ChallengeDetailsViewModel by viewModels()
    private val challengeId by lazy { intent.getStringExtra(CHALLENGE_SELECTED) }

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupObserver()
        viewModel.fetchChallengeDetails()
    }

    private fun setupViews() {
        showLoading()
    }

    private fun showLoading() {
        binding.groupDetails.setVisibility(false)
        binding.pbDetails.setVisibility(true)
    }

    private fun hideLoading() {
        binding.groupDetails.setVisibility(true)
        binding.pbDetails.setVisibility(false)
    }

    private fun setupObserver() {
        val observer = Observer<StateResponse<CodeChallengeBO>> { state ->
            when (state) {
                is StateLoading -> showLoading()
                is StateSuccess -> {
                    hideLoading()
                    populateChallengeDetails(state.data)
                }
                is GenericError -> {
                    hideLoading()
                    showErrorMessage()
                }
            }
        }
        viewModel.challengeLiveData.observe(this, observer)
        viewModel.selectChallenge(challengeId)
    }

    private fun populateChallengeDetails(challengeDetails: CodeChallengeBO) {
        challengeDetails.apply {
            binding.tvChallenge.text = name
            binding.tvSlug.text = getString(R.string.slug_value, slug)
            binding.tvCategory.text = getString(R.string.category_value, category)
            binding.tvDescription.text = getString(R.string.description_value, description)
            binding.tvTotalAttempts.text = getString(R.string.attempts_value, totalAttempts)
            binding.tvTotalCompleted.text = getString(R.string.completed_value, totalCompleted)
            binding.tvTotalStars.text = getString(R.string.stars_value, totalStars)
            binding.tvVoteScore.text = getString(R.string.votes_value, voteScore)
            binding.tvPublishedAt.text =
                getString(R.string.published_value, publishedAt?.toTimeString())
            binding.tvApprovedAt.text =
                getString(R.string.approved_value, approvedAt?.toTimeString())
            binding.tvLanguages.text = languages.splitStringList(
                baseContext,
                R.string.no_languages,
                R.string.languages_value
            )
            binding.tvTags.text = tags.splitStringList(
                baseContext,
                R.string.no_tags,
                R.string.tags_value
            )
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(this, R.string.error_generic, Toast.LENGTH_LONG).show()
    }
}