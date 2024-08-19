package com.zoe.show_model_demo

import com.zoe.show_model_demo.platform.ModelViewPlugin
import com.zoe.show_model_demo.spline.platform.SplineViewPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        //添加3D模型渲染插件
        flutterEngine.plugins.add(ModelViewPlugin())
        //添加Spline模型插件
        flutterEngine.plugins.add(SplineViewPlugin())
    }
}
