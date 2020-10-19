package com.santana.codewars.ui.userchallenges.authored

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santana.codewars.R
import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.ui.userchallenges.ChallengesAdapter
import com.santana.codewars.ui.userchallenges.authored.ChallengesAuthoredAdapter.AuthoredHolder
import com.santana.codewars.utils.splitStringList

class ChallengesAuthoredAdapter(
    private val activity: Activity,
    data: List<ChallengesAuthoredBO>,
    challengeListener: ChallengeListener<ChallengesAuthoredBO>
) : ChallengesAdapter<ChallengesAuthoredBO, AuthoredHolder>(
    activity, data, challengeListener
) {

    override fun createChallengeHolder(parent: ViewGroup): AuthoredHolder =
        AuthoredHolder(inflateAdapter(R.layout.row_challenge_authored, parent))

    override fun bindChallengeHolder(
        holder: AuthoredHolder,
        challenge: ChallengesAuthoredBO
    ) {
        holder.tvName.text = challenge.name
        holder.tvRanks.apply {
            text = if (challenge.rank <= 0) {
                context.getString(R.string.out_of_rank)
            } else {
                context.getString(R.string.rank_position, challenge.rank)
            }
        }
        holder.tvTags.text = challenge.tags.splitStringList(activity, R.string.no_tags, R.string.tags)
        holder.tvLanguages.text =
            challenge.languages.splitStringList(activity, R.string.no_languages, R.string.languages)
    }


    class AuthoredHolder(
        val itemView: View,
        val tvName: TextView = itemView.findViewById(R.id.tvChallenge),
        val tvRanks: TextView = itemView.findViewById(R.id.tvRank),
        val tvTags: TextView = itemView.findViewById(R.id.tvTags),
        val tvLanguages: TextView = itemView.findViewById(R.id.tvLanguages)
    ) : RecyclerView.ViewHolder(itemView)
}