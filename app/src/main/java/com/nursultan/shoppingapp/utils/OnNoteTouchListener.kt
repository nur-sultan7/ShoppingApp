package com.nursultan.shoppingapp.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class OnNoteTouchListener : View.OnTouchListener {
    var xDelta = 0.0f
    var yDelta = 0.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                xDelta = event.rawX - v.x
                yDelta = event.rawY - v.y
            }
            MotionEvent.ACTION_MOVE -> {
                v.x = event.rawX - xDelta
                v.y = event.rawY - yDelta
            }
        }
        return true
    }
}