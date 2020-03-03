package es.iessaladillo.pedrojoya.stroop.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.Event
import es.iessaladillo.pedrojoya.stroop.ui.room.Database1
import es.iessaladillo.pedrojoya.stroop.ui.room.LocalRepository
import es.iessaladillo.pedrojoya.stroop.ui.room.Match
import es.iessaladillo.pedrojoya.stroop.ui.room.Player

class MainActivityViewModel : ViewModel() {

    private lateinit var  repository: LocalRepository
    private lateinit var sharedPreferences: SharedPreferences

     private lateinit var match: Match


    private val _fragment: MutableLiveData<Int> = MutableLiveData(1)
    val fragment: LiveData<Int>
        get() = _fragment

    private val _tutorial: MutableLiveData<Boolean> = MutableLiveData()
    val tutorial: LiveData<Boolean>
        get() = _tutorial

    private val _onChangePlayer: MutableLiveData<Player> = MutableLiveData()
    val onChangePlayer: LiveData<Player>
        get() = _onChangePlayer

    private val _imageCreation: MutableLiveData<Int> = MutableLiveData(R.drawable.logo)
    val imageCreation: LiveData<Int>
        get() = _imageCreation


    fun setMatch(match: Match){
        this.match=match
    }

    fun getMatch():Match{
        return match
    }

    fun changeImage(id:Int){
        _imageCreation.value=id
    }

    fun changePlayer(value:Player){
        _onChangePlayer.value=value
    }


    fun setFragment(fragment: Int) {
        _fragment.value=fragment
    }

    fun setTutorial(tutorial: Boolean) {
        _tutorial.value=tutorial
    }

    fun setBD(context: Context,sharedPreferences: SharedPreferences){
        repository=LocalRepository(Database1.getInstance(context).dao)
        this.sharedPreferences=sharedPreferences
        _onChangePlayer.value=Player(0,sharedPreferences.getString("player","No player selected")!!,sharedPreferences.getInt("avatar",R.drawable.logo))
    }

    fun getPlayers():LiveData<List<Player>>{
        return repository.queryAllPlayers()
    }

    fun getPlayer(id:Long):Player?{
        return repository.queryPlayer(id)
    }

    fun deletePlayer(player:Player){
        Thread { repository.deletePlayer(player)}.start()
        _onChangePlayer.value=Player(0,"No player selected",R.drawable.logo)
        sharedPreferences.edit{
            putInt("avatar", R.drawable.logo)
            putString("player","No player selected")
        }
        setFragment(1)
    }

    fun updatePlayer(player: Player){
        Thread{repository.updatePlayer(player)}.start()
        _onChangePlayer.value=player
    }

    fun insertPlayer(player:Player){
        Thread {
            try {
                repository.inserPlayer(player)
            } catch (e: Exception) {
                //que aparezca el error por pantalla
            }
        }.start()
    }

    fun insertMatch(match: Match){
        Thread {
            try {
                repository.insertMatch(match)
            } catch (e: Exception) {
                //que aparezca el error por pantalla
            }}.start()
    }




}