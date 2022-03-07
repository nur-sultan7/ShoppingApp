package com.nursultan.shoppingapp.presentation.fragments

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.shoppingapp.ShoppingApp
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.FragmentNoteBinding
import com.nursultan.shoppingapp.presentation.MainViewModel
import com.nursultan.shoppingapp.presentation.NewNoteActivity
import com.nursultan.shoppingapp.presentation.ViewModelFactory
import com.nursultan.shoppingapp.presentation.adapters.NotesListAdapter


class NoteFragment : BaseFragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteBinding is null")

    private val mainViewModel: MainViewModel by activityViewModels {
        ViewModelFactory((context?.applicationContext as ShoppingApp).appDatabase)
    }

    private lateinit var newNoteLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onNewNoteResult()
    }

    override fun onClickNew() {
        newNoteLauncher.launch(Intent(activity, NewNoteActivity::class.java))
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
        setOnClickListeners()
    }

    private fun initViews() = with(binding)
    {
        rcViewNotes.layoutManager = LinearLayoutManager(activity)
        adapter = NotesListAdapter()
        rcViewNotes.adapter = adapter
    }

    private fun setObservers() {
        mainViewModel.allNotes.observe(viewLifecycleOwner)
        {
            adapter.submitList(it)
        }
    }

    private fun setOnClickListeners() {
        adapter.setOnDeleteListener = {
            mainViewModel.deleteNote(it)
        }
    }

    private fun onNewNoteResult() {
        newNoteLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if (it.resultCode == Activity.RESULT_OK) {
                    mainViewModel.insertNote(
                        it.data?.getSerializableExtra(NEW_NOTE) as NoteItemDbModel
                    )
                }
            }
    }

    companion object {
        const val NEW_NOTE = "new note"

        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}