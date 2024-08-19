package com.zoe.show_model_demo.spline.platform

import android.content.Context
import android.util.Log
import android.view.View
import com.zoe.show_model_demo.PlatformConstants
import com.zoe.show_model_demo.PlatformConstants.Method_Spline_channelPath
import com.zoe.show_model_demo.spline.SplinePlatformView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class SplineViewController(
    private val context: Context,
    messenger: BinaryMessenger,
    val id: Int,
    val params: HashMap<String, Any>
) : PlatformView, MethodChannel.MethodCallHandler {
    private var splineView: SplinePlatformView? = null

    //通信
    private val channel: MethodChannel = MethodChannel(
        messenger, "$Method_Spline_channelPath$id"
    )

    init {
        channel.setMethodCallHandler(this)

        params.entries.forEach {
            Log.i("rex", "CustomView初始化接收入参：${it.key} - ${it.value}")
        }

    }

    override fun getView(): View? {
        //初始化surfaceView
        if (splineView == null) {
            splineView = SplinePlatformView(context)
        }

        return splineView

    }

    override fun dispose() {

    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            //必须先初始化
            PlatformConstants.Method_Spline_init -> {
                splineView?.init()
                result.success(true)
            }

            PlatformConstants.Method_Spline_loadRes -> {
                splineView?.loadResource()
                result.success(true)
            }

            PlatformConstants.Method_Spline_loadUrl -> {
                val url = call.argument<String>(PlatformConstants.ParamName_Url)
                splineView?.loadUrl(url)
                result.success(true)
            }

            PlatformConstants.Method_Spline_loadBuffer -> {
//                splineView?.loadBuffer()
                result.success(true)
            }

        }
    }


}
