package com.nursultan.shoppingapp.presentation.fragments

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.databinding.FragmentNoteBinding
import com.nursultan.shoppingapp.presentation.MainViewModel
import com.nursultan.shoppingapp.presentation.NewNoteActivity
import com.nursultan.shoppingapp.presentation.ViewModelFactory


class NoteFragment : BaseFragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteBinding is null")

    private val mainViewModel: MainViewModel by activityViewModels {
        ViewModelFactory((context?.applicationContext as ShoppingApp).appDatabase)
    }

    private lateinit var newNoteLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.allNotes.observe(this) {
        }
        onNewNoteResult()
    }

    override fun onClickNew() {
        startActivity(Intent(activity, NewNoteActivity::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onNewNoteResult() {
        newNoteLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            if (it.resultCode == Activity.RESULT_OK) {
                Log.d(TITLE, "title: ${it.data?.getStringExtra(TITLE)}")
                Log.d(DESC, "description: ${it.data?.getStringExtra(DESC)}")
            }
        }
    }

    companion object {
        const val TITLE = "title"
        const val DESC = "description"

        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}