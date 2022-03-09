package com.nursultan.shoppingapp.presentation

import android.content.Intent
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityNewNoteBinding
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment
import com.nursultan.shoppingapp.utils.CloseAnimationListener
import com.nursultan.shoppingapp.utils.HtmlManager
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNewNoteBinding.inflate(layoutInflater)
    }
    private var edNote: NoteItemDbModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBarSetting()
        getNote()
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
            R.id.new_note_bold -> {
                setSelectedTextBold()
            }
            R.id.new_note_color_picker ->
            {
                openColorPicker()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarSetting() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setActivityResult() {
        val resultCode: Int
        val intent = Intent().apply {
            resultCode = if (edNote != null) {
                putExtra(NoteFragment.ED_NOTE, updateNote())
                NoteFragment.EDIT_RESULT_CODE
            } else {
                putExtra(NoteFragment.NEW_NOTE, createNewNote())
                NoteFragment.UPDATE_RESULT_CODE
            }
        }
        setResult(resultCode, intent)
        finish()
    }

    private fun createNewNote() = NoteItemDbModel(
        title = binding.edTitle.text.toString(),
        content = HtmlManager.toHtml(binding.edDescription.text),
        time = getCurrentTime(),
        category = ""
    )

    private fun updateNote(): NoteItemDbModel? = with(binding)
    {
        return edNote?.copy(
            title = edTitle.text.toString(),
            content = HtmlManager.toHtml(edDescription.text)
        )
    }

    private fun getCurrentTime(): String {
        val currentTime = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())
        return currentTime.format(Calendar.getInstance().time)
    }

    private fun getNote() {
        val note = intent.getSerializableExtra(NoteFragment.ED_NOTE)
        edNote = note?.let { it as NoteItemDbModel }
        edNote?.let {
            with(binding)
            {
                edTitle.setText(it.title)
                edDescription.setText(HtmlManager.getFromHtml(it.content))
            }
        }
    }

    private fun setSelectedTextBold() = with(binding)
    {
        val firstIndex = edDescription.selectionStart
        val lastIndex = edDescription.selectionEnd
        val styles = edDescription.text.getSpans(firstIndex, lastIndex, StyleSpan::class.java)
        if (styles.isNotEmpty()) {
            edDescription.text.removeSpan(styles[0])
        } else {
            edDescription.text.setSpan(
                StyleSpan(Typeface.BOLD),
                firstIndex,
                lastIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        edDescription.text.trim()
        edDescription.setSelection(firstIndex)
    }

    private fun openColorPicker() {
        binding.colorPicker.visibility = View.VISIBLE
        val openAnim = AnimationUtils.loadAnimation(this, R.anim.open_color_picker)
        binding.colorPicker.startAnimation(openAnim)
    }
    private fun closeColorPicker()
    {
        val closeAnim = AnimationUtils.loadAnimation(this, R.anim.close_color_picker)
        closeAnim.setAnimationListener(object : CloseAnimationListener()
        {
            override fun onAnimationEnd(animation: Animation?) {
                binding.colorPicker.visibility=View.GONE
            }
        })
        binding.colorPicker.startAnimation(closeAnim)
    }
}