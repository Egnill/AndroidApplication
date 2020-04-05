package com.example.myapplication.manager

import org.xmlpull.v1.XmlPullParser
import java.util.List
import com.example.myapplication.R


class ManagerIncomeCosts {

    private lateinit var listIC: List<DataIC>
    //private var FILENAME: String = "DataStore"

    public fun set (amount: Int, category: String, comment: String, variable: String) {
        listIC.add(DataIC(amount, category, comment, variable))
    }

    public fun get (index: Int): DataIC {
        return listIC[index]
    }

    public fun getAll(): List<DataIC> {
        return listIC
    }

    public fun readXML () {
        //val xpp: XmlPullParser = getResources().getXml(R.xml.DataStore)
    }

    public fun writeXML () {

    }

    private fun parse(xpp: XmlPullParser): Boolean {
        var status = true
        var current: DataIC? = null
        var inEntry = false
        var textValue: String? = ""
        try {
            var eventType = xpp.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if ("element".equals(
                            tagName,
                            ignoreCase = true
                        )
                    ) {
                        inEntry = true
                        current = DataIC()
                    }
                    XmlPullParser.TEXT -> textValue = xpp.text
                    XmlPullParser.END_TAG -> if (inEntry) {
                        if ("element".equals(tagName, ignoreCase = true)) {
                            listIC.add(current)
                            inEntry = false
                        } else if ("amount".equals(tagName, ignoreCase = true)) {
                            current!!.setAmount(textValue!!.toInt())
                        } else if ("category".equals(tagName, ignoreCase = true)) {
                            current!!.setCategory(textValue.toString())
                        } else if ("comment".equals(tagName, ignoreCase = true)) {
                            current!!.setComment(textValue.toString())
                        } else if ("variable".equals(tagName, ignoreCase = true)) {
                            current!!.setVariable(textValue.toString())
                        }
                    }
                    else -> {
                    }
                }
                eventType = xpp.next()
            }
        } catch (e: Exception) {
            status = false
            e.printStackTrace()
        }
        return status
    }
}