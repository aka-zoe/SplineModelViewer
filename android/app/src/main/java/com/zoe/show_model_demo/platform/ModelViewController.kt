package com.zoe.show_model_demo.platform

import android.content.Context
import android.util.Log
import android.view.View
import com.zoe.show_model_demo.PlatformConstants
import com.zoe.show_model_demo.PlatformConstants.Method_channelPath
import com.zoe.show_model_demo.view.ModelSurfaceView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class ModelViewController(
    private val context: Context,
    messenger: BinaryMessenger,
    val id: Int,
    val params: HashMap<String, Any>
) : PlatformView, MethodChannel.MethodCallHandler {
    private var modelSurfaceView: ModelSurfaceView? = null

    //通信
    private val channel: MethodChannel = MethodChannel(
        messenger, "$Method_channelPath$id"
    )

    init {
        channel.setMethodCallHandler(this)

        params.entries.forEach {
            Log.i("rex", "CustomView初始化接收入参：${it.key} - ${it.value}")
        }

    }

    override fun getView(): View? {
        //初始化surfaceView
        if (modelSurfaceView == null) {
            modelSurfaceView = ModelSurfaceView(context)
            modelSurfaceView?.onResume()
        }

        return modelSurfaceView

    }

    override fun dispose() {
        modelSurfaceView?.onPause()
        modelSurfaceView?.onDestroy()
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            //必须先初始化
            PlatformConstants.Method_init -> {
                val hasArgument = call.hasArgument("rgbaArray")

                if (hasArgument) {
                    modelSurfaceView?.setSurfaceView(rgbaArr = call.argument("rgbaArray"))
                } else {
                    modelSurfaceView?.setSurfaceView()
                }

                result.success(true)
            }

            //加载glb格式模型
            PlatformConstants.Method_loadGlb -> {
                loadGlbOrGltf(call, result)
            }
            //加载gltf格式模型
            PlatformConstants.Method_loadGltf -> {
                loadGlbOrGltf(call, result)
            }

            //创建间接光源并将其添加到场景中
            PlatformConstants.Method_loadIndirectLight -> {
                val ibl = call.argument<String>(PlatformConstants.ParamName_Ibl)
                if (ibl != null) {
                    modelSurfaceView?.loadIndirectLight(context, ibl)
                }
                result.success(true)
            }

            //创建天空框并将其添加到场景中
            PlatformConstants.Method_loadEnvironment -> {
                val ibl = call.argument<String>(PlatformConstants.ParamName_Ibl)
                if (ibl != null) {
                    modelSurfaceView?.loadEnvironment(context, ibl)
                }
                result.success(true)
            }

        }
    }

    private fun loadGlbOrGltf(call: MethodCall, result: MethodChannel.Result) {
        try {
            if (call.arguments != null) {
                val modelDirName = call.argument<String>(PlatformConstants.ParamName_DirName)
                val modelName = call.argument<String>(PlatformConstants.ParamName_ModelName)
                //加载glb格式模型
                if (call.method == PlatformConstants.Method_loadGlb) {
                    if (modelDirName != null && modelName == null) {
                        modelSurfaceView?.loadGlb(context, modelDirName)
                    } else if (modelDirName != null && modelName != null) {
                        modelSurfaceView?.loadGlb(context, modelDirName, modelName)
                    }
                    //加载gltf格式模型
                } else if (call.method == PlatformConstants.Method_loadGltf) {
                    if (modelDirName != null && modelName == null) {
                        modelSurfaceView?.loadGltf(context, modelDirName)
                    } else if (modelDirName != null && modelName != null) {
                        modelSurfaceView?.loadGltf(context, modelDirName, modelName)
                    }
                }
                result.success(true)
            }
        } catch (e: Exception) {
            result.success(false)
        }

    }

}
