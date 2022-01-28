package com.appsforlife.contactlensmanagement.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemDao
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemDbModel
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemDao
import com.appsforlife.contactlensmanagement.data.noteitemdata.NoteItemDbModel

@Database(
    entities = [LensItemDbModel::class, NoteItemDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun lensItemDao(): LensItemDao
    abstract fun noteItemDao(): NoteItemDao

    companion object {

        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "app.db"

        fun getInstance(context: Context): AppDataBase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}






