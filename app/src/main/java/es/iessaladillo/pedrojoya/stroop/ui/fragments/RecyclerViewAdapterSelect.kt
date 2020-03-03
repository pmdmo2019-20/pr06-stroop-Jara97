package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.playerselection_item_fragment.*

class RecyclerViewAdapterSelect : RecyclerView.Adapter<RecyclerViewAdapterSelect.ViewHolder>() {

    private var data: List<Player> = emptyList()
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterSelect.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.playerselection_item_fragment, parent, false)
        return ViewHolder(itemView)
    }

    fun submitList(list:List<Player>) {
       data=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setPreferences(sharedPreferences: SharedPreferences,viewModel: MainActivityViewModel){
        this.sharedPreferences=sharedPreferences
        this.viewModel=viewModel
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterSelect.ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): Player {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        init {


        }

        fun bind(player:Player) {
            lblNameC.text = player.name
            imgAvatarR.setImageResource(player.avatar)
            containerView.setOnClickListener {
                if(sharedPreferences.getInt("avatar",R.drawable.logo)==player.avatar) {
                    sharedPreferences.edit {
                        putInt("avatar", R.drawable.logo)
                        putString("player",containerView.resources.getString(R.string.player_selection_no_player_selected))
                        viewModel.changePlayer(Player(0,containerView.resources.getString(R.string.player_selection_no_player_selected),R.drawable.logo))

                    }
                }
                else{
                    sharedPreferences.edit {
                        putInt("avatar", player.avatar)
                        putString("player",player.name)
                        viewModel.changePlayer(player)
                    }
                }
            }

        }
    }

}