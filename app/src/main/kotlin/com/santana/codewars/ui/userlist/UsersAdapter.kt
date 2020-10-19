package com.santana.codewars.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.santana.codewars.R
import com.santana.codewars.domain.model.UserBO
import kotlin.math.max

class UsersAdapter(
    private val activity: AppCompatActivity,
    private val users: List<UserBO>,
    private val userClickListener: (user: UserBO) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (users.isEmpty()) {
            EmptyHolder(inflateAdapter(R.layout.row_empty_users, parent))
        } else {
            UserHolder(inflateAdapter(R.layout.row_user, parent))
        }
    }

    private fun inflateAdapter(layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(activity).inflate(layoutRes, parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is UserHolder) {
            val user = users.get(position)
            holder.bindValues(user, userClickListener)
        }
    }

    override fun getItemCount(): Int = max(users.size,1)

    class EmptyHolder(itemView: View) : ViewHolder(itemView)

    class UserHolder(
        itemView: View,
        var tvName: TextView = itemView.findViewById(R.id.tvChallenge),
        var tvBestLanguage: TextView = itemView.findViewById(R.id.tvBestLanguage),
        var tvLeaderboardPosition: TextView = itemView.findViewById(R.id.tvLeaderboardPosition)
    ) : ViewHolder(itemView) {

        fun bindValues(
            user: UserBO,
            userClickListener: (user: UserBO) -> Unit
        ) {
            tvName.text = user.username
            tvLeaderboardPosition.apply {
                if (user.leaderboardPosition == null) {
                    setText(R.string.out_of_leaderboard)
                } else {
                    text =
                        context.getString(R.string.leaderboard_position, user.leaderboardPosition)
                }
            }
            tvBestLanguage.apply {
                val bestLanguage = user.getBestLanguage()
                if (bestLanguage == null) {
                    setText(R.string.zero_languages)
                } else {
                    text = context
                        .getString(R.string.best_language, bestLanguage.languageName, bestLanguage.score)
                }
            }
            itemView.setOnClickListener { userClickListener.invoke(user) }
        }
    }
}