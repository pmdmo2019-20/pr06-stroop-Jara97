package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.playercreation_fragment.*
import kotlinx.android.synthetic.main.ranking_fragment.*


class RankingFragment : Fragment(R.layout.ranking_fragment) {

    private val listAdapterRanking:RecyclerViewAdapterRanking=RecyclerViewAdapterRanking()
    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = RankingFragment()
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.ranking_title)
        }
        listAdapterRanking.apply {
            setPreferences(viewModel)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun setupRecyclerView(){
        lstRanking.run{
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = listAdapterRanking
        }
    }


}