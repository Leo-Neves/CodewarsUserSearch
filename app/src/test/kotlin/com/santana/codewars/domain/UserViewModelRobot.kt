package com.santana.codewars.domain

import androidx.lifecycle.Observer
import com.santana.codewars.data.mapper.toBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchUsersUseCase
import com.santana.codewars.domain.usecase.ListUsersUseCase
import com.santana.codewars.factory.UserFactory.mockUser
import com.santana.codewars.state.UserResponse
import com.santana.codewars.ui.userlist.UserViewModel
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Single
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import retrofit2.HttpException
import retrofit2.Response

object UserViewModelRobot {

    class UserViewModelArrange {

        fun fetchUsersSuccess(
            usersUseCase: FetchUsersUseCase
        ) {
            every { usersUseCase.execute(any()) } returns Single.just(
                listOf(mockUser().toBO())
            )
        }

        fun fetchUsersError(
            usersUseCase: FetchUsersUseCase
        ) {
            every { usersUseCase.execute(any()) } returns Single.error(
                HttpException(Response.error<String>(404, EMPTY_RESPONSE))
            )
        }

        infix fun act(func: UserViewModelAct.() -> Unit) =
            UserViewModelAct().apply(func)
    }

    class UserViewModelAct {

        fun observeUsers(
            viewModel: UserViewModel,
            observer: Observer<UserResponse<List<UserBO>>>
        ) = viewModel.usersLiveData.observeForever(observer)

        infix fun assert(func: UserViewModelAssert.() -> Unit) =
            UserViewModelAssert().apply(func)
    }

    class UserViewModelAssert {

        fun successFetchUsers(observer: Observer<UserResponse<List<UserBO>>>){
            val slot = slot<UserResponse<List<UserBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is UserResponse.UserSuccess)
        }

        fun failedFetchUsers(observer: Observer<UserResponse<List<UserBO>>>){
            val slot = slot<UserResponse<List<UserBO>>>()
            verify { observer.onChanged(capture(slot)) }
            assertTrue(slot.captured is UserResponse.UserNotFound)
        }

    }

    infix fun arrange(func: UserViewModelArrange.() -> Unit) =
        UserViewModelArrange().apply(func)
}