package com.zoe.show_model_demo.spline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.zoe.show_model_demo.R
import design.spline.runtime.SplineView

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
        val testRoot = findViewById<ConstraintLayout>(R.id.testRoot)
        val platformView = SplinePlatformView(this)
        testRoot.addView(platformView)
        platformView.init()
    }
}
