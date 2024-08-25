import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:show_model_demo/constants.dart';

class SplineView extends StatefulWidget {
  const SplineView({super.key});

  @override
  State createState() {
    return _SplineViewState();
  }
}

class _SplineViewState extends State<SplineView> {
  MethodChannel? methodChannel;

  @override
  Widget build(BuildContext context) {
    //iOS使用UiKitView，Android使用AndroidView
    return AndroidView(
      viewType: Constants.Platform_Spline_viewTypeId,
      onPlatformViewCreated: (viewTypeId) {
        methodChannel = MethodChannel("${Constants.Method_Spline_channelPath}$viewTypeId");
        //初始化
        methodChannel?.invokeMethod(Constants.Method_Spline_init).then((value) {
          //加载模型
          methodChannel?.invokeMethod(Constants.Method_Spline_loadRes);
        });
      },
      creationParams: const <String, dynamic>{'initParams': '--'},
      creationParamsCodec: const StandardMessageCodec(),
    );
  }
}
