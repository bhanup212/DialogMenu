package com.dialog.options

interface OptionClickListener {
    fun onClick(text: String, position: Int)
    fun onNegativeButtonClick()
}