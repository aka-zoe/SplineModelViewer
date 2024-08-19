package com.zoe.show_model_demo.spline

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.zoe.show_model_demo.R
import design.spline.runtime.SplineView
import java.nio.Buffer

class SplinePlatformView(context: Context) : ConstraintLayout(context) {


    private var splineView: SplineView? = null


    fun init() {
        removeAllViews()
        splineView = SplineView(context)
        addView(splineView)
    }

    fun loadResource() {
        splineView?.loadResource(R.raw.rocket_copy)
    }

    fun loadUrl(url: String?) {
        splineView?.loadUrl(url ?: "")
    }

    fun loadBuffer(buffer: Buffer) {
        splineView?.loadBuffer(buffer)
    }

}
