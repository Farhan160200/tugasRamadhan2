package com.example.tugasramadhanday2and3

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NamesApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NameRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NameRepository(database.nameDao()) }

}