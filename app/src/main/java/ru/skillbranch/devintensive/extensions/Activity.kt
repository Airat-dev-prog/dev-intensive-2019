package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

fun Activity.hideKeyboard(){
    val imm:InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.et_message.windowToken, 0)
}
fun Activity.isKeyboardOpen():Boolean{
    var r : Rect = Rect()
    //this.findViewById<ViewGroup>(android.R.id.content).rootView.getWindowVisibleDisplayFrame(rect)
    window.decorView.rootView.getWindowVisibleDisplayFrame(r)
    return window.decorView.rootView.height > r.height()
}