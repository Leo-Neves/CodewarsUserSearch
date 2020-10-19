package com.santana.codewars.ui.userchallenges.completed

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santana.codewars.R
import com.santana.codewars.domain.model.ChallengesCompletedBO
import com.santana.codewars.ui.userchallenges.ChallengesAdapter
import com.santana.codewars.ui.userchallenges.completed.ChallengesCompletedAdapter.CompletedHolder
import com.santana.codewars.utils.splitStringList
import com.santana.codewars.utils.toTimeString

class ChallengesCompletedAdapter(
    private val activity: Activity,
    data: List<ChallengesCompletedBO>,
    challengeListener: ChallengeListener<ChallengesCompletedBO>
) : ChallengesAdapter<ChallengesCompletedBO, CompletedHolder>(
    activity, data, challengeListener
) {

    override fun createChallengeHolder(parent: ViewGroup): CompletedHolder =
        CompletedHolder(inflateAdapter(R.layout.row_challenge_completed, parent))

    override fun bindChallengeHolder(
        holder: CompletedHolder,
        challenge: ChallengesCompletedBO
    ) {
        holder.tvName.text = challenge.name
        holder.tvSlug.text = challenge.slug
        holder.tvCompletedDate.text = challenge.completedAt.toTimeString()
        holder.tvLanguages.text =
            challenge.completedLanguages.splitStringList(activity, R.string.no_languages_completed, R.string.languages_completed)
    }

    class CompletedHolder(
        val itemView: View,
        val tvName: TextView = itemView.findViewById(R.id.tvChallenge),
        val tvSlug: TextView = itemView.findViewById(R.id.tvSlug),
        val tvCompletedDate: TextView = itemView.findViewById(R.id.tvCompletedAt),
        val tvLanguages: TextView = itemView.findViewById(R.id.tvLanguages)
    ) : RecyclerView.ViewHolder(itemView)
}