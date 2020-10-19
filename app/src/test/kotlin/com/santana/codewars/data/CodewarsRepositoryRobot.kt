package com.santana.codewars.data

import android.accounts.NetworkErrorException
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.data.repository.CodewarsRepositoryImpl
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.factory.UserFactory.mockUser
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single

object CodewarsRepositoryRobot {
    private val api = mockk<CodewarsApi>()
    private val userDao = mockk<UserDao>()
    private val subject = CodewarsRepositoryImpl(
        api = api,
        userDao = userDao
    )

    class CodewarsRepositoryArrange{

        fun mockUserFromApi(){
            every {
                api.user(any())
            } returns Single.just(mockUser())
        }

        fun mockNetworkError(){
            every {
                api.user(any())
            } returns Single.error(NetworkErrorException())
        }

        infix fun act(func: CodewarsRepositoryAct.() -> Unit) =
            CodewarsRepositoryAct().apply(func)
    }

    class CodewarsRepositoryAct{

        fun getUsers(): Single<UserBO> {
            return subject.getUserInfo("jon")
        }

        infix fun assert(func: CodewarsRepositoryAssert.() -> Unit){
            CodewarsRepositoryAssert().apply(func)
        }
    }

    class CodewarsRepositoryAssert{

        fun success(observer: Single<UserBO>){
            observer.test()
                .assertComplete()
                .assertValue { it.rankLanguages.size == 3 }
                .assertValue { it.rankOverall.score == 2000 }
        }

        fun error(observer: Single<UserBO>){
            observer.test()
                .assertError(NetworkErrorException::class.java)
        }

    }

    infix fun arrange(func: CodewarsRepositoryArrange.() -> Unit) =
        CodewarsRepositoryArrange().apply(func)
}