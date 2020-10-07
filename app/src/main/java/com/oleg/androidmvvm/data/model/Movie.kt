package com.oleg.androidmvvm.data.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.oleg.androidmvvm.model.IntegerListTypeConverter

@Entity(tableName = "movie")
@TypeConverters(IntegerListTypeConverter::class)
data class Movie(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,

    @SerializedName("video")
    @Expose
    var video: Boolean? = false,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = "",

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = "",

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = "",

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = "",

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = false,

    @SerializedName("overview")
    @Expose
    var overview: String? = "",

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = "",

    var watched: Boolean = true,

    var checked: Boolean = false
)
