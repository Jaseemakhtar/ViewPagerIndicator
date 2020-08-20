package com.example.viewpagerindicatordemo

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class MyFragment : Fragment() {
    companion object {
        fun newInstance(message: String): MyFragment {
            val fragment = MyFragment()
            val bundle = Bundle(1)
            bundle.putString(EXTRA_MESSAGE, message)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textFragmentTitle.text = arguments?.getString(EXTRA_MESSAGE)
    }
}