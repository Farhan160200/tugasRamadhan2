package com.example.tugasramadhanday2and3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import java.util.jar.Attributes

@Dao
interface NameDao {
    @Query("SELECT * FROM name_table ORDER BY name ASC")
    fun getAlphabetizedNames(): Flow<List<Name>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nama: Name)

    @Query("DELETE FROM name_table")
    suspend fun deleteAll()
}