package com.example.fragmentpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentA : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (inflater != null){
            inflater.inflate(R.layout.fragment_a, container, false)
        }
        else{
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}