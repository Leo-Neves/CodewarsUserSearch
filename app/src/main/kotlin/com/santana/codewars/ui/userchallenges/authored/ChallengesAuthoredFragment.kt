package com.santana.codewars.ui.userchallenges.authored

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santana.codewars.R
import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.state.StateResponse
import com.santana.codewars.state.StateResponse.*
import com.santana.codewars.ui.challengedetails.ChallengeDetailsActivity
import com.santana.codewars.ui.userchallenges.ChallengesActivity.Companion.CHALLENGE_SELECTED
import com.santana.codewars.ui.userchallenges.ChallengesAdapter.ChallengeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengesAuthoredFragment : Fragment(), ChallengeListener<ChallengesAuthoredBO> {

    private val viewModel: ChallengesAuthoredViewModel by viewModels()

    private lateinit var rootView: View
    private lateinit var adapter: ChallengesAuthoredAdapter
    private val rvChallenges by lazy { rootView.findViewById<RecyclerView>(R.id.rvChallenges) }
    private val user: UserBO? by lazy { arguments?.getParcelable<UserBO>(CHALLENGE_SELECTED) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_challenges, container, false)
        setupObserver()
        setupRecyclerView()
        setupAdapter()
        viewModel.fetchChallengesCompleted(0)
        return rootView
    }

    private fun setupObserver() {
        activity?.apply {
            val observer = Observer<StateResponse<List<ChallengesAuthoredBO>>> { state ->
                when (state) {
                    is StateLoading -> adapter.disableTryAgain()
                    is StateSuccess -> adapter.updateChallengesList(state.data)
                    is DataNotFound -> adapter.finishUpdatingList()
                    is GenericError -> showErrorMessage(R.string.error_generic)
                    is NetworkError -> showErrorMessage(R.string.error_network)
                }
            }
            user?.also {user->
                viewModel.selectUser(user)
                viewModel.challengesLiveData.observe(this, observer)
            }
        }
    }

    private fun setupRecyclerView() {
        rvChallenges.setHasFixedSize(true)
        rvChallenges.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter() {
        activity?.also { activity ->
            adapter = ChallengesAuthoredAdapter(
                activity,
                ArrayList(),
                this
            )
            rvChallenges.adapter = adapter
        }
    }

    private fun showErrorMessage(messageRes: Int) {
        adapter.enableTryAgain()
        context?.also { context ->
            Toast.makeText(context, messageRes, Toast.LENGTH_LONG).show()
        }
    }

    override fun onChallengeClicked(challenge: ChallengesAuthoredBO) {
        activity?.apply {
            val intent = Intent(this, ChallengeDetailsActivity::class.java).apply {
                putExtra(CHALLENGE_SELECTED, challenge)
            }
            startActivity(intent)
        }
    }

    override fun pageLoaded(page: Int) {
        viewModel.fetchChallengesCompleted(page)
    }
}