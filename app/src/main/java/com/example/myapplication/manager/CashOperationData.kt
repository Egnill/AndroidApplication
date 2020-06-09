package com.example.myapplication.manager

import kotlinx.serialization.*

@Serializable
data class CashOperationData(
    var amount: Int?,
    var category: String?,
    var comment: String?,
    var date: String?,
    var time: String?,
    var variable: String?
)