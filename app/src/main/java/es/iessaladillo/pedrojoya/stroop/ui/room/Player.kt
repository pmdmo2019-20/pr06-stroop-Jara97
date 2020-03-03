package es.iessaladillo.pedrojoya.stroop.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "player",
        indices = [
        Index(
            name = "USERS_INDEX_NAME_UNIQUE",
            value = ["name"],
            unique = true
        )
])

class Player(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="avatar")
    val avatar:Int

    ){

}





