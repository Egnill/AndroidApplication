package com.example.myapplication.manager

import kotlinx.serialization.*

@Serializable
data class DataIC(
    var amount: Int? = null,
    var category: String? = "Еда",
    var comment: String? = null,
    var variable: String? = "income"
)