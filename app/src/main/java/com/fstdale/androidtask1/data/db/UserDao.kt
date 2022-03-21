package com.fstdale.androidtask1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fstdale.androidtask1.data.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User)

    @Query("select * from user")
    fun getUsers(): LiveData<List<User>>

    @Query("select * from user where uid = :uid")
    fun getUserById(uid: String): List<User>
}