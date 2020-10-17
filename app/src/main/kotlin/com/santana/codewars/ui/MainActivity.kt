package com.santana.codewars.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.santana.codewars.R
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.state.StateResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: FetchUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val observer = Observer<StateResponse<List<UserBO>>> {
            when (it) {
                is StateResponse.StateLoading ->{
                    Log.i("State", "loading")
                }
                is StateResponse.StateSuccess -> {
                    Log.i("State", "total of users: "+it.data.size)
                }
                is StateResponse.StateError -> {
                    Log.i("State", "Error")
                    it.error.printStackTrace()
                }
            }
        }
        viewModel.usersLiveData.observe(this, observer)
        viewModel.fetchUsers("Leo-Neves")
    }
}