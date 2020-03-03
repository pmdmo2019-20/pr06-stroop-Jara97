package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.playercreation_fragment.*
import kotlinx.android.synthetic.main.playercreation_item_fragment.*
import kotlinx.android.synthetic.main.playerselection_item_fragment.*

class RecyclerViewAdapterCreation : RecyclerView.Adapter<RecyclerViewAdapterCreation.ViewHolder>() {

    private var data: List<Int> = avatars
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterCreation.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.playercreation_item_fragment, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun setPreferences(sharedPreferences: SharedPreferences, viewModel: MainActivityViewModel){
        this.sharedPreferences=sharedPreferences
        this.viewModel=viewModel
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterCreation.ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun getItem(position:Int): Int {
        return data[position]
    }


    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        init {



        }

        fun bind(player: Int) {
            imgAvatarC.setImageResource(player)
            containerView.setOnClickListener {
               viewModel.changeImage(player)
            }

        }
    }

}