package com.yaka.logme

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

object FontHelper {
    private val CUSTOM_FONT = "fonts/avenir-black.ttf"
    private var customTypeface: Typeface? = null
    /*
   * Set custom typeface on a View, or all Views in a given ViewGroup
   */
    fun setCustomTypeface(v: View) {
        // load the font and store as a static member
        if (customTypeface == null)
        {
            customTypeface = Typeface.createFromAsset(v.getContext().getAssets(), CUSTOM_FONT)
        }
        if (v is TextView)
        {
            (v as TextView).setTypeface(customTypeface)
        }
        else if (v is EditText)
        {
            (v as EditText).setTypeface(customTypeface)
        }
        else if (v is Button)
        {
            (v as Button).setTypeface(customTypeface)
        }
        else if (v is ViewGroup)
        {
            val vg = v as ViewGroup
            for (i in 0 until vg.getChildCount())
            {
                val child = vg.getChildAt(i)
                setCustomTypeface(child)
            }
        }
    }
}