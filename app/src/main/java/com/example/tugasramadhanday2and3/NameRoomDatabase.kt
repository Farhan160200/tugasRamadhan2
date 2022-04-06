package com.example.tugasramadhanday2and3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Name::class), version = 1, exportSchema = false)
public abstract class NameRoomDatabase: RoomDatabase() {

    abstract fun nameDao(): NameDao

    private class NameDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var nameDao = database.nameDao()

                    nameDao.deleteAll()

                    var name = Name("Rafly")
                    nameDao.insert(name)
                    name = Name("Raihan")
                    nameDao.insert(name)

                    name = Name("TODO!")
                    nameDao.insert(name)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NameRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NameRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NameRoomDatabase::class.java,
                    "word_database"
                ).addCallback(NameDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

}