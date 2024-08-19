package com.zoe.show_model_demo

object PlatformConstants {

    //初始化
    const val Method_init = "init"

    //加载glb格式模型
    const val Method_loadGlb = "loadGlb"

    //加载gltf格式模型
    const val Method_loadGltf = "loadGltf"

    //创建间接光源并将其添加到场景中
    const val Method_loadIndirectLight = "loadIndirectLight"

    //创建天空框并将其添加到场景中
    const val Method_loadEnvironment = "loadEnvironment"

    //viewTypeId 与flutter层统一
    const val Platform_viewTypeId = "com.zoe.show_model_demo.platform.ModelSurfaceView"
    const val Platform_Spline_viewTypeId = "com.zoe.show_model_demo.spline.SplinePlatformView"

    //通信channel地址
    const val Method_channelPath = "com.zoe.show_model_demo.platform/modelViewChannel"
    const val Method_Spline_channelPath = "com.zoe.show_model_demo.platform/splineViewChannel"

    //以下是参数名称
    const val ParamName_DirName = "modelDirName"
    const val ParamName_ModelName = "modelName"
    const val ParamName_Ibl = "ibl"
    const val ParamName_Url = "url"


    const val Method_Spline_init = "spline_init"
    const val Method_Spline_loadRes = "spline_loadRes"
    const val Method_Spline_loadUrl = "spline_loadUrl"
    const val Method_Spline_loadBuffer = "spline_loadBuffer"
}
