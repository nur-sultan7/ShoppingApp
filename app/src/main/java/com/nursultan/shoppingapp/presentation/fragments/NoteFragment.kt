package com.nursultan.shoppingapp.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        adapter.setOnItemClickListener = {
            newNoteLauncher.launch(
                Intent(activity, NewNoteActivity::class.java).apply {
                    putExtra(ED_NOTE, it)
                }
            )
        }
    }

    private fun onNewNoteResult() {
        newNoteLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {

                when(it.resultCode)
                {
                    EDIT_RESULT_CODE->
                    {
                        mainViewModel.updateNote(
                            (it.data?.getSerializableExtra(ED_NOTE)
                                ?: throw RuntimeException("updated note is null")) as NoteItemDbModel
                        )
                    }
                    UPDATE_RESULT_CODE->
                    {
                        mainViewModel.insertNote(
                            (it.data?.getSerializableExtra(NEW_NOTE)
                                ?: throw RuntimeException("new note is null")) as NoteItemDbModel
                        )
                    }
                }

            }
    }

    companion object {
        const val NEW_NOTE = "new_note"
        const val ED_NOTE = "edit_note"
        const val EDIT_RESULT_CODE = 121
        const val UPDATE_RESULT_CODE = 120

        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}