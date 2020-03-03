package es.iessaladillo.pedrojoya.stroop.ui.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(entities = [Player::class,Match::class], version = 1)
abstract class Database1 : RoomDatabase() {

    abstract val dao: Dao

    companion object {

        @Volatile
        private var INSTANCE: Database1? = null

        fun getInstance(context: Context): Database1 {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, Database1::class.java, "app_database")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}