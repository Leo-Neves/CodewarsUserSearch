package com.santana.codewars.ui.userchallenges

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.santana.codewars.R
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.ui.userchallenges.authored.ChallengesAuthoredFragment
import com.santana.codewars.ui.userchallenges.completed.ChallengesCompletedFragment
import com.santana.codewars.ui.userlist.ListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengesActivity : AppCompatActivity() {

    private val bottomNavigation by lazy { findViewById<BottomNavigationView>(R.id.bottomNavigation) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val frameLayout by lazy { findViewById<FrameLayout>(R.id.frameLayout) }

    private val user by lazy { intent.getParcelableExtra<UserBO>(ListActivity.USER_SELECTED) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)
        setupToolbar()
        setupBottomNavigation()
        showAuthoredChallenges()
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.challenges, user?.username)
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.itemAuthored -> showAuthoredChallenges()
                    R.id.itemCompleted -> showCompletedChallenges()
                    else -> false
                }
        }
    }

    private fun showAuthoredChallenges():Boolean{
        val bundle = Bundle()
        bundle.putParcelable(CHALLENGE_SELECTED, user)
        val fragment = ChallengesAuthoredFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(frameLayout.id, fragment).commit()
        return true
    }

    private fun showCompletedChallenges(): Boolean {
        val bundle = Bundle()
        bundle.putParcelable(CHALLENGE_SELECTED, user)
        val fragment = ChallengesCompletedFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(frameLayout.id, fragment).commit()
        return true
    }

    companion object{
        const val CHALLENGE_SELECTED = "CHALLENGE_SELECTED"
    }
}