package com.example.myapplication.manager

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.*
import javax.xml.parsers.*
import javax.xml.transform.*
import javax.xml.transform.dom.*
import javax.xml.transform.stream.*


class ManagerIncomeCosts {

    //private var listIC: List<DataIC>
    private var m: DataIC? = null
    private var FILENAME: String = "C:\\Users\\alex_\\AndroidStudioProjects\\MyApplication2\\app\\src\\main\\res\\assets\\DataStore"

    private var amount: String? = null
    private var category: String? = null
    private var comment: String? = null
    private var variable: String? = null
    //private var rolev: ArrayList<String>? = null

    fun set(amount: Int?, category: String?, comment: String?, variable: String?) {
        m = DataIC(amount, category, comment, variable)
    }

    fun readXML(): List<DataIC> {
        val rolev = ArrayList<String>()
        val dom: Document
        // Make an  instance of the DocumentBuilderFactory
        val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        try {
            // use the factory to take an instance of the document builder
            val db: DocumentBuilder = dbf.newDocumentBuilder()
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(FILENAME)
            val doc: Element = dom.documentElement
            amount = getTextValue(amount, doc, "amount")
            if (amount != null) {
                if (amount != null) rolev.add(amount!!)
            }
            category = getTextValue(category, doc, "category")
            if (category != null) {
                if (category != null) rolev.add(category!!)
            }
            comment = getTextValue(comment, doc, "comment")
            if (comment != null) {
                if (comment != null) rolev.add(comment!!)
            }
            variable = getTextValue(variable, doc, "variable")
            if (variable != null) {
                if (variable != null) rolev.add(variable!!)
            }
            //return true
        } catch (pce: ParserConfigurationException) {
            //System.out.println(pce.getMessage())
        } catch (se: SAXException) {
            //System.out.println(se.getMessage())
        } catch (ioe: IOException) {
            //System.err.println(ioe.getMessage())
        }

        return listOf(DataIC(rolev[0].toInt(), rolev[1], rolev[2], rolev[3]))
    }

    fun writeXML() {
        val dom: Document
        var e: Element?

        /*amount = listIC[listIC.size - 1].amount.toString()
        category = listIC[listIC.size - 1].category
        comment = listIC[listIC.size - 1].comment
        variable = listIC[listIC.size - 1].variable*/

        amount = m!!.amount.toString()
        category = m!!.category
        comment = m!!.comment
        variable = m!!.variable

        // instance of a DocumentBuilderFactory
        val dbf =
            DocumentBuilderFactory.newInstance()
        try {
            // use factory to get an instance of document builder
            val db = dbf.newDocumentBuilder()
            // create instance of DOM
            dom = db.newDocument()

            // create the root element
            val rootEle: Element? = dom.createElement("Data_IncomeCosts")

            // create data elements and place them under root
            e = dom.createElement("amount")
            e.appendChild(dom.createTextNode(amount))
            rootEle?.appendChild(e)
            e = dom.createElement("category")
            e.appendChild(dom.createTextNode(category))
            rootEle?.appendChild(e)
            e = dom.createElement("comment")
            e.appendChild(dom.createTextNode(comment))
            rootEle?.appendChild(e)
            e = dom.createElement("variable")
            e.appendChild(dom.createTextNode(variable))
            rootEle?.appendChild(e)
            dom.appendChild(rootEle)
            try {
                val tr: Transformer = TransformerFactory.newInstance().newTransformer()
                tr.setOutputProperty(OutputKeys.INDENT, "yes")
                tr.setOutputProperty(OutputKeys.METHOD, "xml")
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Data_Conf.dtd")
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")

                // send DOM to file
                tr.transform(
                    DOMSource(dom),
                    StreamResult(FileOutputStream(FILENAME))
                )
            } catch (te: TransformerException) {
                //System.out.println(te.getMessage())
            } catch (ioe: IOException) {
                println(ioe.message)
            }
        } catch (pce: ParserConfigurationException) {
            println("UsersXML: Error trying to instantiate DocumentBuilder $pce")
        }
    }

    private fun getTextValue(
        def: String?,
        doc: Element,
        tag: String
    ): String? {
        var value = def
        val nl: NodeList = doc.getElementsByTagName(tag)
        if (nl.length > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).firstChild.nodeValue
        }
        return value
    }
}