package com.example.myapplication.debts

import java.lang.IllegalArgumentException

enum class DebtType {
    I_MUST,
    THEY_OWN_ME;

    companion object {
        fun fromInt(value: Int): DebtType {
            return when (value) {
                0 -> I_MUST
                1 -> THEY_OWN_ME
                else -> throw IllegalArgumentException()
            }
        }
    }
}