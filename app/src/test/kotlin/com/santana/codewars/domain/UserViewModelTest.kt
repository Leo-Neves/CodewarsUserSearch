package com.santana.codewars.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.santana.codewars.domain.UserViewModelRobot.arrange
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchUsersUseCase
import com.santana.codewars.domain.usecase.ListUsersUseCase
import com.santana.codewars.state.UserResponse
import com.santana.codewars.ui.userlist.UserViewModel
import io.mockk.mockk
import io.reactivex.Scheduler
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val usersUseCase = mockk<FetchUsersUseCase>()
    private val listUseCase = mockk<ListUsersUseCase>()
    private val scheduler = mockk<Scheduler>()
    private val observer = mockk<Observer<UserResponse<List<UserBO>>>>()
    private val viewModel = UserViewModel(usersUseCase, listUseCase, scheduler)

    @Test
    fun `when fetch users should return user list`() {
        arrange {
            fetchUsersSuccess(usersUseCase)
        } act {
            observeUsers(viewModel, observer)
        } assert {
            successFetchUsers(observer)
        }
    }

    @Test
    fun `when fetch users should return error`() {
        arrange {
            fetchUsersError(usersUseCase)
        } act {
            observeUsers(viewModel, observer)
        } assert {
            failedFetchUsers(observer)
        }
    }

}