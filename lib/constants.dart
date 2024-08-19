class Constants {
  //初始化
  static const String Method_init = "init";

  //加载glb格式模型
  static const String Method_loadGlb = "loadGlb";

  //加载gltf格式模型
  static const String Method_loadGltf = "loadGltf";

  //创建间接光源并将其添加到场景中
  static const String Method_loadIndirectLight = "loadIndirectLight";

  //创建天空框并将其添加到场景中
  static const String Method_loadEnvironment = "loadEnvironment";

  //viewTypeId 与flutter层统一
  static const String Platform_viewTypeId = "com.zoe.show_model_demo.platform.ModelSurfaceView";
  static const String Platform_Spline_viewTypeId =
      "com.zoe.show_model_demo.spline.SplinePlatformView";

  //通信channel地址
  static const String Method_channelPath = "com.zoe.show_model_demo.platform/modelViewChannel";
  static const String Method_Spline_channelPath =
      "com.zoe.show_model_demo.platform/splineViewChannel";

  //以下是参数名称
  static const String ParamName_DirName = "modelDirName";
  static const String ParamName_ModelName = "modelName";
  static const String ParamName_Ibl = "ibl";
  static const String ParamName_Url = "url";

  static const String Method_Spline_init = "spline_init";
  static const String Method_Spline_loadRes = "spline_loadRes";
  static const String Method_Spline_loadUrl = "spline_loadUrl";
  static const String Method_Spline_loadBuffer = "spline_loadBuffer";
}
