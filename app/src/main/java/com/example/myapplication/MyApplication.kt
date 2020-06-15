package com.example.myapplication

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.myapplication.manager.DataStorage

val Activity.dataStorageAdd: DataStorage get() = (application as MyApplication).dataStorageAdd
val Fragment.dataStorageAdd: DataStorage get() = activity!!.dataStorageAdd

val Activity.dataStorageDebts: DataStorage get() = (application as MyApplication).dataStorageDebts
val Fragment.dataStorageDebts: DataStorage get() = activity!!.dataStorageDebts

class MyApplication : Application() {
    val dataStorageAdd: DataStorage by lazy { DataStorage(this, "dataStore.json") }
    val dataStorageDebts: DataStorage by lazy { DataStorage(this, "dataDebts.json") }
}