package com.example.tugasramadhanday2and3

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NameRepository(private val wordDao: NameDao) {

    val allNames: Flow<List<Name>> = wordDao.getAlphabetizedNames()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(name: Name) {
        wordDao.insert(name)
    }
}