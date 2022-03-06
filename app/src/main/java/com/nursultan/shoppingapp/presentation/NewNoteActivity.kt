package com.nursultan.shoppingapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.databinding.ActivityNewNoteBinding
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment

class NewNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNewNoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBarSetting()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_note_save -> {
                setResult()
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

    private fun setResult() {
        val intent = Intent().apply {
            putExtra(NoteFragment.TITLE, binding.edTitle.text.toString())
            putExtra(NoteFragment.DESC, binding.edDescription.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}