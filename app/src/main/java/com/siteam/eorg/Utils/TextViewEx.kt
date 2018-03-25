package com.siteam.eorg.Utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import java.util.jar.Attributes

/**
 * Created by Igor on 20.02.2018.
 */
class TextViewEx: TextView {
    lateinit var tf: Typeface
    constructor(context: Context) : super(context){
        tf = setTypeface(context)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        tf = setTypeface(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        tf = setTypeface(context)
    }

    fun setTypeface(context: Context): Typeface {
        return Typeface.createFromAsset(context.assets, "fonts/Roboto-Regular.ttf")
    }
}