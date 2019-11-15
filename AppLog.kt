package io.andrewedgar.printedlog

import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat

object AppLog {

    
    //Provides date format for file name
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd kk:mm:ss")

    fun i(tag: String, msg: String) {

        Log.i(tag, msg)
        appendLog( "[${dateFormat.format(System.currentTimeMillis())}]   $tag: $msg")
    }

    fun d(tag: String, msg: String) {

        Log.d(tag, msg)
        appendLog( "[${dateFormat.format(System.currentTimeMillis())}]   $tag: $msg")
    }

    fun e(tag: String, msg: String) {

        Log.e(tag, msg)
        appendLog( "[${dateFormat.format(System.currentTimeMillis())}]   $tag: $msg")
    }


    private fun appendLog(text: String) {

        val appendDateFormat = SimpleDateFormat("yyyy-MM-dd")


        val logDirectory = File("${Environment.getExternalStorageDirectory()}/Logs")

        if (!logDirectory.exists()) {
            logDirectory.mkdir()
        }

        
        //Creates file using date above. Automatically creates new file for each day.
        val logFile =
            File("${Environment.getExternalStorageDirectory()}/Logs/logcat_${appendDateFormat.format(System.currentTimeMillis())}.txt")

        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
