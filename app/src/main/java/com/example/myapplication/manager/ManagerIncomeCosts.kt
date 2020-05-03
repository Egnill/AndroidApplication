package com.example.myapplication.manager

import android.content.Context
import android.os.Environment
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.*
import javax.xml.parsers.ParserConfigurationException


@Suppress("DEPRECATED_IDENTITY_EQUALS", "UNREACHABLE_CODE")
class ManagerIncomeCosts {

    private var m: DataIC? = null
    private val json = Json(JsonConfiguration.Stable)

    fun set(amount: Int?, category: String?, comment: String?, variable: String?) {
        m = DataIC(amount, category, comment, variable)
    }

    fun readJSON(): List<DataIC> {
        try {
            val rootPath: String = Environment.getExternalStorageState().toString() + "/MyFolder/"
            val file = File(rootPath, "dataStore.json")
            val buf = BufferedReader(FileReader(file))
            val read = buf.readLine()
            m = toObject(read)
            buf.close()
        } catch (ioe: IOException) {
            //System.err.println(ioe.getMessage())
        }
        return listOf(m!!)
    }

    fun writeJSON(context: Context) {
        try {
            val root = File(context.getExternalFilesDir(null), "/MyFolder/")
            if (!root.exists()) {
                root.mkdir()
            }
            val file = File(root, "dataStore.json")
            if (!file.exists()) {
                file.createNewFile()
            }
            val buf = BufferedWriter(FileWriter(file, true))
            if (m != null) buf.write(toJson(m!!))
            else buf.write("ERROR!")
            buf.close()
        } catch (pce: ParserConfigurationException) {

        }
    }

    private fun toObject(stringValue: String): DataIC {
        return json.parse(DataIC.serializer(), stringValue)
    }

    private fun toJson(field: DataIC): String {
        return json.stringify(DataIC.serializer(), field)
    }
}