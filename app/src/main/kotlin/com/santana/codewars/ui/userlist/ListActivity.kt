package com.santana.codewars.ui.userlist

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santana.codewars.R
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.state.UserResponse
import com.santana.codewars.state.UserResponse.*
import com.santana.codewars.ui.userchallenges.ChallengesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list.*

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: FetchUserViewModel by viewModels()

    private val rvUsers by lazy { findViewById<RecyclerView>(R.id.rvUsers) }
    private val etSearch by lazy { findViewById<EditText>(R.id.etSearchUser) }
    private val btSearch by lazy { findViewById<ImageButton>(R.id.btnSearch) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.pbSearch) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setupViews()
        setupObserver()
    }

    private fun hideProgressBar() {
        progressBar.visibility = GONE
        btSearch.visibility = VISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = VISIBLE
        btSearch.visibility = GONE
    }

    private fun setupViews() {
        setupRecyclerView()
        setupSearch()
        setupToolbar()
        viewModel.orderUsersByRecent()
    }

    private fun setupRecyclerView() {
        rvUsers.setHasFixedSize(true)
        rvUsers.layoutManager = LinearLayoutManager(this)
    }

    private fun setupSearch() {
        btSearch.setOnClickListener {
            viewModel.fetchUsers(etSearch.text.toString())
        }
        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.fetchUsers(etSearch.text.toString())
                true
            }
            false
        }
    }

    private fun setupToolbar() {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.itemOrderRank -> viewModel.orderUsersByRank()
                R.id.itemOrderRecent -> viewModel.orderUsersByRecent()
            }
            true
        }
    }

    private fun showUsersList(users: List<UserBO>) {
        rvUsers.adapter = UsersAdapter(this, users) { user ->
            openChallengeActivity(user)
        }
    }

    private fun openChallengeActivity(user: UserBO) {
        val intent = Intent(this, ChallengesActivity::class.java).apply {
            putExtra(USER_SELECTED, user)
        }
        startActivity(intent)
    }

    private fun setupObserver() {
        val observer = Observer<UserResponse<List<UserBO>>> { state ->
            when (state) {
                is UserLoading -> showProgressBar()
                is UserSuccess -> {
                    showUsersList(state.data)
                    hideProgressBar()
                }
                is NetworkError -> {
                    Toast.makeText(this, getString(R.string.error_network), LENGTH_LONG).show()
                    hideProgressBar()
                }
                is GenericError -> {
                    Toast.makeText(this, getString(R.string.error_network), LENGTH_LONG).show()
                    hideProgressBar()
                }
                is UserNotFound -> {
                    Toast.makeText(
                        this,
                        getString(R.string.error_user, state.username),
                        LENGTH_LONG
                    ).show()
                    hideProgressBar()
                }
            }
        }
        viewModel.usersLiveData.observe(this, observer)
    }

    companion object {
        const val USER_SELECTED = "USER_SELECTED"
    }
}