package com.sticky.notes.app

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sticky.notes.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var stickyNote: StickyNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        stickyNote = StickyNote()

        binding.etText.setText(stickyNote.getStickNotes(this))

        binding.btnSave.setOnClickListener {
            stickyNote.setStickNotes(binding.etText.text.toString(), this)
            updateStickyNotesWidget()
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateStickyNotesWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName, R.layout.sticky_app_widget)
        val thisStickyNotesWidget = ComponentName(this, StickyNotesWidget::class.java)
        remoteViews.setTextViewText(R.id.appwidget_text, binding.etText.text.toString())
        appWidgetManager.updateAppWidget(thisStickyNotesWidget, remoteViews)
    }

}
