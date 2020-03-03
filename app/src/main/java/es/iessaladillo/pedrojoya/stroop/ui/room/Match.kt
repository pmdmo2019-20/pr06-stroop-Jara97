package es.iessaladillo.pedrojoya.stroop.ui.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import java.util.*

@Entity(tableName = "match",
        foreignKeys = [
        ForeignKey(
            entity = Player::class,
            parentColumns = ["id"],
            childColumns = ["playerId"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
])
class Match(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo(name="playerId")
    val playerId: Long,
    @ColumnInfo(name="points")
    val points:Long,
    @ColumnInfo(name="words")
    val words:Long,
    @ColumnInfo(name="gameMode")
    val gamemode:Boolean,
    @ColumnInfo(name="date")
    val date: String





)

// Clase POJO que representa la relación
data class UserWithGames(
    @Embedded
    val player: Player,
    @Relation(
        parentColumn = "id",
        entityColumn = "playerId"
    )
    val gameList: List<Match>
)
