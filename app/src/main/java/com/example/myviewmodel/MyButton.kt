package com.example.myviewmodel

import android.R.attr.textColor
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.view.Gravity


class MyButton : AppCompatButton {

    private lateinit var enabledBackground: Drawable
    private lateinit var disabledBackground: Drawable
    private var textColors = 0


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isEnabled) {
            background = enabledBackground
        } else background = disabledBackground
        setTextColor(textColor)
        textSize = 12f
        gravity = Gravity.CENTER
        if (isEnabled) {
            text = "Submit"
        } else text = "Isi Dulu"
    }

    fun init() {
        textColors = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground = resources.getDrawable(R.drawable.bg_button, null)
        disabledBackground = resources.getDrawable(R.drawable.bg_button_disable, null)
    }

}