package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.fragmentAdapter.FragmentAdapter
import kotlinx.android.synthetic.main.assistant_fragment.*

class AssistantFragment : Fragment(R.layout.assistant_fragment) {


    companion object {
        fun newInstance() = AssistantFragment()

    }

    private fun setupTabLayoutMediator() {
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.assistant_title)
        }
        viewPager.adapter = FragmentAdapter(this)
        setupTabLayoutMediator()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
    }

}