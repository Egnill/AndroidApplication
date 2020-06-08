package com.example.myapplication.manager

import kotlinx.serialization.*

@Serializable
data class DataIC(
    var amount: Int?,
    var category: String?,
    var comment: String?,
    var date: String?,
    var time: String?,
    var variable: String?
)