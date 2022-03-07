package com.nursultan.shoppingapp.presentation

import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityNewNoteBinding
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNewNoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBarSetting()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_note_save -> {
                setActivityResult()
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarSetting() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setActivityResult() {
        val intent = Intent().apply {
            putExtra(NoteFragment.NEW_NOTE, createNewNote())
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun createNewNote() = NoteItemDbModel(
        title = binding.edTitle.text.toString(),
        content = binding.edDescription.text.toString(),
        time = getCurrentTime(),
        category = ""
    )

    private fun getCurrentTime(): String {
        val currentTime = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return currentTime.format(Calendar.getInstance().time)
    }
}