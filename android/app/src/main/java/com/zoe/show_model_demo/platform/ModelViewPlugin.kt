package com.zoe.show_model_demo.platform

import com.zoe.show_model_demo.PlatformConstants
import io.flutter.embedding.engine.plugins.FlutterPlugin

class ModelViewPlugin : FlutterPlugin {
    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {

        //将 Android 控件进行注册，提供 flutter 层使用
        binding.platformViewRegistry.registerViewFactory(
            PlatformConstants.Platform_viewTypeId,
            ModelViewFactory(binding.binaryMessenger)
        )

    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    }

}
