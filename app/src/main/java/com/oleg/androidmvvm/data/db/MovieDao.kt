package com.oleg.androidmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.oleg.androidmvvm.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie)

    @Query("select * from movie")
    fun getAll(): LiveData<List<Movie>>

    @Query("delete from movie where watched = :watched")
    fun deleteMovies(watched: Boolean)

    @Update
    fun updateMovie(movie: Movie)

    @Query("DELETE FROM movie WHERE id = :id")
    fun delete(id: Int?)
}