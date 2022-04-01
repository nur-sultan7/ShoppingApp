package com.nursultan.shoppingapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.iterator
import androidx.preference.PreferenceManager
import com.nursultan.shoppingapp.R
import com.nursultan.shoppingapp.data.database.model.NoteItemDbModel
import com.nursultan.shoppingapp.databinding.ActivityNewNoteBinding
import com.nursultan.shoppingapp.presentation.fragments.NoteFragment
import com.nursultan.shoppingapp.utils.*

class NewNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNewNoteBinding.inflate(layoutInflater)
    }
    private var edNote: NoteItemDbModel? = null
    private val defPref by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getSelectedTheme())
        setContentView(binding.root)
        setActionBarSetting()
        setNote()
        setOnTouchListeners()
        setOnClickListeners()
        setEditTextActionMode()
        setEditTextSizes()
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
            R.id.new_note_color_picker -> {
                if (binding.colorPicker.isShown)
                    closeColorPicker()
                else
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
        time = TimeManager.getCurrentTime(),
        category = ""
    )

    private fun updateNote(): NoteItemDbModel? = with(binding)
    {
        return edNote?.copy(
            title = edTitle.text.toString(),
            content = HtmlManager.toHtml(edDescription.text)
        )
    }

    private fun setNote() {
        val note = intent.getSerializableExtra(NoteFragment.ED_NOTE)
        edNote = note?.let { it as NoteItemDbModel }
        edNote?.let {
            with(binding)
            {
                edTitle.setText(it.title)
                edDescription.setText(HtmlManager.getFromHtml(it.content).trim())
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
        edDescription.setSelection(firstIndex)
    }

    private fun openColorPicker() = with(binding) {
        colorPicker.visibility = View.VISIBLE
        val openAnim = AnimationUtils.loadAnimation(
            this@NewNoteActivity,
            R.anim.open_color_picker
        )
        colorPicker.startAnimation(openAnim)
    }

    private fun closeColorPicker() {
        val closeAnim = AnimationUtils.loadAnimation(this, R.anim.close_color_picker)
        closeAnim.setAnimationListener(object : CloseAnimationListener() {
            override fun onAnimationEnd(animation: Animation?) {
                binding.colorPicker.visibility = View.GONE
            }
        })
        binding.colorPicker.startAnimation(closeAnim)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListeners() {
        binding.colorPicker.setOnTouchListener(OnNoteTouchListener())
    }

    private fun setOnClickListeners() {
        binding.colorPicker.forEach { row ->
            row as TableRow
            for (view in row) {
                view.setOnClickListener {
                    setSelectedTextColor((view.background as ColorDrawable).color)
                }
            }
        }
    }

    private fun setSelectedTextColor(colorId: Int) = with(binding) {
        val firstIndex = edDescription.selectionStart
        val lastIndex = edDescription.selectionEnd
        val styles =
            edDescription.text.getSpans(
                firstIndex,
                lastIndex,
                ForegroundColorSpan::class.java
            )
        if (styles.isNotEmpty())
            edDescription.text.removeSpan(styles[0])
        edDescription.text.setSpan(
            ForegroundColorSpan(colorId),
            firstIndex,
            lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        edDescription.setSelection(firstIndex)
    }

    private fun setEditTextActionMode() {
        binding.edDescription.customSelectionActionModeCallback = EditTextActionMode
    }

    private fun setEditTextSizes() = with(binding) {
        edTitle.setTextSize(
            defPref.getString(
                getString(R.string.note_title_text_size_key),
                null
            )
        )
        edDescription.setTextSize(
            defPref.getString(
                getString(R.string.note_content_text_size_key),
                null
            )
        )
    }

    private fun EditText.setTextSize(size: String?) {
        size?.let {
            this.textSize = it.toFloat()
        }
    }

    private fun getSelectedTheme(): Int {
        return if (defPref.getString("theme_style_preference", null) == "Green")
            R.style.Theme_ShoppingAppGreen
        else
            R.style.Theme_ShoppingAppBlack
    }
}