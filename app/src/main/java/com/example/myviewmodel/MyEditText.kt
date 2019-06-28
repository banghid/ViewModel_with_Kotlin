package com.example.myviewmodel

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class MyEditText : AppCompatEditText {

    private lateinit var mClearButtonImage: Drawable


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukkan Nama Anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null)!!

        setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (compoundDrawablesRelative[2] != null) {
                    var clearButtonStart: Float
                    var clearButtonEnd: Float
                    var isClearButtonClicked: Boolean = false

                    if (getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = mClearButtonImage.intrinsicWidth + paddingStart.toFloat()

                        if (m.getX() < clearButtonEnd) {
                            isClearButtonClicked = true
                        }
                    } else {
                        clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()

                        if (m.getX() > clearButtonStart) {
                            isClearButtonClicked = true
                        }
                    }

                    if (isClearButtonClicked) {
                        if (m.action == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage =
                                ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null)!!
                            showClearButton()
                            return true
                        } else if (m.action == MotionEvent.ACTION_UP) {
                            mClearButtonImage =
                                ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null)!!
                            if (text != null) {
                                text = null
                            }
                            hideClearButton()
                            return true
                        } else {
                            return false
                        }
                    } else {
                        return false
                    }
                }
                return false
            }
        })

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.toString().isEmpty()) {
                    showClearButton()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

    fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
    }
}