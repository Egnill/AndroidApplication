package com.example.myapplication

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.myapplication.manager.DataStorage

val Activity.dataStorage: DataStorage get() = (application as MyApplication).dataStorage
val Fragment.dataStorage: DataStorage get() = activity!!.dataStorage

class MyApplication : Application() {
    val dataStorage: DataStorage by lazy { DataStorage(this, "dataStore.json") }
}