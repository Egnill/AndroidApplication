package com.example.myapplication.manager

import android.content.Context
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.*
import javax.xml.parsers.ParserConfigurationException

class DataStorage(private val context: Context, private val path: String) {

    private val json = Json(JsonConfiguration.Stable)

    fun readJSON(): List<DataIC> {
        var l:List<DataIC> = ArrayList()
        try {
            val rootPath = File(context.getExternalFilesDir(null), "/MyFolder/$path")
            val buf = BufferedReader(FileReader(rootPath))
            val read = buf.readLine()
            if (read != null) l = toObject(read)
            buf.close()
        } catch (ioe: IOException) {
        }
        return l
    }

    fun writeJSON(amount: Int?, category: String?, comment: String?, date: String?, time: String?, variable: String?) {
        try {
            val m = DataIC(amount, category, comment, date, time, variable)
            var list_m: MutableList<DataIC> = ArrayList()
            val root = File(context.getExternalFilesDir(null), "/MyFolder/")
            if (!root.exists()) {
                root.mkdir()
            }
            val file = File(root, path)
            if (!file.exists()) {
                file.createNewFile()
                list_m.add(m)
            } else {
                list_m = readJSON().toMutableList()
                list_m.add(m)
                file.delete()
                file.createNewFile()
            }
            val buf = BufferedWriter(FileWriter(file, true))
            if (!list_m.isNullOrEmpty()) buf.write(toJson(list_m))
            else buf.write("ERROR!")
            buf.close()
        } catch (pce: ParserConfigurationException) {

        }
    }

    private fun toObject(stringValue: String): List<DataIC> {
        return json.parse(DataIC.serializer().list, stringValue)
    }

    private fun toJson(field: List<DataIC>): String {
        return json.stringify(DataIC.serializer().list, field)
    }
}