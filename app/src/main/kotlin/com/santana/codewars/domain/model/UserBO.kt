package com.santana.codewars.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.santana.codewars.domain.typeconverter.ListRankConverter
import com.santana.codewars.domain.typeconverter.RankConverter
import com.santana.codewars.domain.typeconverter.TotalChallengeConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
class UserBO(
    @PrimaryKey
    val username: String,
    val name: String,
    val honor: Int,
    val clan: String,
    val leaderboardPosition: Int?,
    val skills: List<String>?,
    val updateAt: Long,
    val rankOverall: RankBO,
    val rankLanguages: List<RankBO>,
    val challenges: TotalChallengesBO
): Parcelable {

    fun getBestLanguage(): RankBO? {
        return rankLanguages.sortedByDescending { sel -> sel.rank }.getOrNull(0)
    }
}

