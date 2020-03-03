package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Match
import kotlinx.android.synthetic.main.sumary_fragment.*

class SumaryFragment : Fragment(R.layout.sumary_fragment) {

    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = SumaryFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_result_title)
        }
        viewModel.run {
            val match: Match =getMatch()
            imgSumPlayer.setImageResource(onChangePlayer.value!!.avatar)
            lblTittleSum.text=onChangePlayer.value!!.name
            lblCorrectSumNum.text=(match.points/10).toString()
            lblIncorrectSumNum.text=((match.words-match.points/10)-1).toString()
            lblPointsSumNum.text=match.points.toString()
        }
    }




}