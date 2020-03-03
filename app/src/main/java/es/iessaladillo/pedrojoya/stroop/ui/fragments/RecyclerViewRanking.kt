package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sumary_fragment_item.*


class RecyclerViewAdapterRanking : RecyclerView.Adapter<RecyclerViewAdapterRanking.ViewHolder>() {

    private var data: List<Player> = listOf(Player(33,"we",33))
    private lateinit var viewModel: MainActivityViewModel



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterRanking.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.sumary_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    fun submitList(list:List<Player>) {
        data=list
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


    fun getItem(position:Int): Player {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        init {


        }

        fun bind(player: Player) {
            lblNameR.text=player.name
        }
    }

}