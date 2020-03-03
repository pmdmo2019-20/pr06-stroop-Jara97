package es.iessaladillo.pedrojoya.stroop.ui.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

class LocalRepository(private val dao: Dao){

    fun inserPlayer(player: Player){
        dao.inserPlayer(player)
    }


    fun updatePlayer(player: Player){
        dao.updatePlayer(player)
    }


    fun deletePlayer(player: Player){
        dao.deletePlayer(player)
    }

     fun queryAllPlayers(): LiveData<List<Player>>{
         return dao.queryAllPlayers()
     }

    fun queryAllPlayers2():List<Player>{
        return dao.queryAllPlayers2()
    }

    fun queryPlayer(userId:Long): Player?{
        return dao.queryPlayer(userId)
    }

    fun queryAllPlayersWithGames(): LiveData<List<UserWithGames>>{
        return dao.queryAllPlayersWithGames()
    }

    fun insertMatch(match: Match){
        dao.inserMatch(match)
    }


}