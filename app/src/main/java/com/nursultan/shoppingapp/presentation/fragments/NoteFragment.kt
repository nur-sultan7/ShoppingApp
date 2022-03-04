package com.nursultan.shoppingapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.FragmentNoteBinding


class NoteFragment : BaseFragment() {
    private var binding: FragmentNoteBinding? = null
    override fun onClickNew() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoteFragment()
    }

    override fun onDestroyView() {
        binding=null
        super.onDestroyView()
    }
}