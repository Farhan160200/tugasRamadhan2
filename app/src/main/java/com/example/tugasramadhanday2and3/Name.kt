package com.example.tugasramadhanday2and3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "name_table")
data class Name (
    @PrimaryKey @ColumnInfo (name = "name") val nama: String)
