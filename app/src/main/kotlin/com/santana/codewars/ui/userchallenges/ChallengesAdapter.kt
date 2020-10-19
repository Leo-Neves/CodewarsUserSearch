package com.santana.codewars.ui.userchallenges

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.santana.codewars.R
import com.santana.codewars.utils.setVisibility

abstract class ChallengesAdapter<T, V : ViewHolder>(
    private val activity: Activity,
    private var data: List<T>,
    private val challengeListener: ChallengeListener<T>
) : RecyclerView.Adapter<ViewHolder>() {
    private var isAllPagesReached = false
    private var isTryAgainEnabled = false
    private var nextPage = 0;

    fun updateChallengesList(newChallengesList: List<T>) {
        isAllPagesReached = false
        val newData = ArrayList<T>()
        newData.addAll(data)
        newData.addAll(newChallengesList)
        data = newData
        nextPage += 1
        notifyDataSetChanged()
    }

    fun finishUpdatingList() {
        isAllPagesReached = true
        notifyDataSetChanged()
    }

    fun enableTryAgain() {
        isTryAgainEnabled = true
        notifyDataSetChanged()
    }

    fun disableTryAgain() {
        isTryAgainEnabled = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_VIEW_CHALLENGE -> createChallengeHolder(parent)
            TYPE_VIEW_LOAD -> LoadingHolder(inflateAdapter(R.layout.row_loading, parent))
            else -> EmptyHolder(inflateAdapter(R.layout.row_empty_challenges, parent))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_VIEW_CHALLENGE) {
            val challenge = data.get(position)
            bindChallengeHolder(holder as V, challenge)
            holder.itemView.setOnClickListener {
                challengeListener.onChallengeClicked(challenge)
            }
        } else if (holder is LoadingHolder) {
            if (!isTryAgainEnabled && !isAllPagesReached){
                challengeListener.pageLoaded(nextPage)
            }
            holder.pbAdapter.setVisibility(!isTryAgainEnabled)
            holder.btnTryAgain.setVisibility(isTryAgainEnabled)
            holder.btnTryAgain.setOnClickListener {
                challengeListener.pageLoaded(nextPage)
            }
        }
    }

    protected fun inflateAdapter(layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(activity).inflate(layoutRes, parent, false)
    }

    protected abstract fun createChallengeHolder(parent: ViewGroup): V

    protected abstract fun bindChallengeHolder(holder: V, challenge: T)

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) {
            if (isAllPagesReached) TYPE_VIEW_EMPTY
            else TYPE_VIEW_LOAD
        } else TYPE_VIEW_CHALLENGE
    }

    class LoadingHolder(
        val itemView: View,
        val pbAdapter: ProgressBar = itemView.findViewById(R.id.pbAdapter),
        val btnTryAgain: Button = itemView.findViewById(R.id.btnTryAgain)
    ) : ViewHolder(itemView)

    class EmptyHolder(itemView: View) : ViewHolder(itemView)

    interface ChallengeListener<T> {
        fun onChallengeClicked(challenge: T)
        fun pageLoaded(page: Int)
    }

    companion object {
        const val TYPE_VIEW_CHALLENGE = 1
        const val TYPE_VIEW_LOAD = 2
        const val TYPE_VIEW_EMPTY = 3
    }
}