package com.sticky.notes.app

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException

class StickyNote {

    fun getStickNotes(context: Context): String {

        val fileEvents = File(context.filesDir.path + "/stickyNotes.txt")
        val text = StringBuilder()
        try {
            val bufferedReader = BufferedReader(FileReader(fileEvents))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return text.toString()
    }

    fun setStickNotes(textToBeSaved: String, context: Context) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream =
                context.applicationContext.openFileOutput("stickyNotes.txt", Context.MODE_PRIVATE)
            fileOutputStream.write(textToBeSaved.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
