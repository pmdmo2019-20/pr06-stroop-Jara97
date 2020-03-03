package es.iessaladillo.pedrojoya.stroop.ui.fragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.fragments.AssistantFragment
import es.iessaladillo.pedrojoya.stroop.ui.fragments.AssistantFragmentContent

class FragmentAdapter(parent:AssistantFragment):FragmentStateAdapter(parent){


    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> AssistantFragmentContent.newInstance().also {it.page=0  }
            1 -> AssistantFragmentContent.newInstance().also {it.page=1  }
            2 -> AssistantFragmentContent.newInstance().also {it.page=2  }
            3 -> AssistantFragmentContent.newInstance().also {it.page=3  }
            4 -> AssistantFragmentContent.newInstance().also {it.page=4  }
            5 -> AssistantFragmentContent.newInstance().also {it.page=5  }
            6 -> AssistantFragmentContent.newInstance().also {it.page=6  }
            7 -> AssistantFragmentContent.newInstance().also {it.page=7  }

            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }

    override fun getItemCount(): Int = 8

}