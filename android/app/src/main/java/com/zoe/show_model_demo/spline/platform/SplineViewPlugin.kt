package com.zoe.show_model_demo.spline.platform

import com.zoe.show_model_demo.PlatformConstants
import io.flutter.embedding.engine.plugins.FlutterPlugin

class SplineViewPlugin : FlutterPlugin {
    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {

        //将 Android 控件进行注册，提供 flutter 层使用
        binding.platformViewRegistry.registerViewFactory(
            PlatformConstants.Platform_Spline_viewTypeId,
            SplineViewFactory(binding.binaryMessenger)
        )

    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    }

}
