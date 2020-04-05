package com.example.myapplication.manager

class DataIC {
    private var _amount: Int = 0
    private var _category: String = ""
    private var _comment: String = ""
    private var _variable: String = ""

    constructor (amount: Int, category: String, comment: String, variable: String) {
        _amount = amount
        _category = category
        _comment = comment
        _variable = variable
    }

    constructor ()

    //Getters and Setters
    public fun getAmount (): Int {
        return _amount
    }
    public fun getCategory (): String {
        return _category
    }
    public fun getComment (): String {
        return _comment
    }
    public fun getVariable (): String {
        return _variable
    }

    public fun setAmount (amount: Int) {
        _amount = amount
    }
    public fun setCategory (category: String) {
        _category = category
    }
    public fun setComment (comment: String) {
        _comment = comment
    }
    public fun setVariable (variable: String) {
        _variable = variable
    }
}