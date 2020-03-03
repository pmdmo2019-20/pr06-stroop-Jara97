package es.iessaladillo.pedrojoya.stroop.ui.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert
    fun inserPlayer(player: Player)

    @Update
    fun updatePlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Query("select * from player") fun queryAllPlayers():LiveData<List<Player>>

    @Query("select * from player") fun queryAllPlayers2():List<Player>

    @Query("select * from player where id=:userId") fun queryPlayer(userId:Long):Player?

    @Transaction
    @Query("SELECT * FROM player")
    fun queryAllPlayersWithGames(): List<UserWithGames>

    @Insert
    fun inserMatch(match: Match)

}