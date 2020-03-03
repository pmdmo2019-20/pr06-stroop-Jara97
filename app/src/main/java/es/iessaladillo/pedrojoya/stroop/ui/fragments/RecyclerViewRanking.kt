package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Match
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import es.iessaladillo.pedrojoya.stroop.ui.room.UserWithGames
import es.iessaladillo.pedrojoya.stroop.ui.room.UserWithGames2
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sumary_fragment_item.*


class RecyclerViewAdapterRanking : RecyclerView.Adapter<RecyclerViewAdapterRanking.ViewHolder>() {

    private var data: MutableList<UserWithGames2> = mutableListOf()
    private var data2: List<UserWithGames> = listOf()
    private lateinit var viewModel: MainActivityViewModel
    private var onItemClick:((Match,Player)->Unit)?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterRanking.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.sumary_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    fun setOnItemClick(listener:((Match,Player)->Unit)){
        onItemClick=listener
    }

    fun submitList(list:List<UserWithGames>) {
        data2=list
        for (i in data2){
           i.gameList.forEach {y->
               data.add(UserWithGames2(i.player,y))
           }
        }
        data.sortByDescending { x->x.gameList!!.points }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setPreferences(viewModel: MainActivityViewModel){
        this.viewModel=viewModel
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): UserWithGames2 {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        init {

        }

        fun bind(playerWithGames: UserWithGames2) {
            if(playerWithGames.gameList!=null){
                imgAvatarR.setImageResource(playerWithGames.player.avatar)
                lblNameR.text=playerWithGames.player.name
                lblGameModeR.text="Game mode:"+playerWithGames.gameList.gamemode
                lblTimeR.text="Minutes: "+playerWithGames.gameList.time/60000
                lblWordsR.text="Words: "+playerWithGames.gameList!!.words.toString()
                lblCorrectR.text="Correct: "+(playerWithGames.gameList!!.points/10).toString()
                lblPointsRNum.text=playerWithGames.gameList!!.points.toString()
                containerView.setOnClickListener{
                    onItemClick?.invoke(playerWithGames.gameList,playerWithGames.player)
                }
            }

        }
    }

}