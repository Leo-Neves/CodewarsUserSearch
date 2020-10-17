package com.santana.codewars.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santana.codewars.data.CodewarsRepositoryRobot.arrange
import com.santana.codewars.domain.model.UserBO
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test

class CodewarsRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `when network is ok return list of users`(){
        lateinit var single: Single<UserBO>
        arrange{
            mockUserFromApi()
        } act {
            single = getUsers()
        } assert {
            success(single)
        }
    }

    @Test
    fun `when network is off return throws an error`(){
        lateinit var single: Single<UserBO>
        arrange{
            mockNetworkError()
        } act {
            single = getUsers()
        } assert {
            error(single)
        }
    }
}