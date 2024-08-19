import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:show_model_demo/constants.dart';

class ModelView extends StatefulWidget {
  const ModelView({super.key});

  @override
  State createState() {
    return _ModelViewState();
  }
}

class _ModelViewState extends State<ModelView> {
  MethodChannel? methodChannel;

  @override
  Widget build(BuildContext context) {
    return AndroidView(
      viewType: Constants.Platform_viewTypeId,
      onPlatformViewCreated: (viewTypeId) {
        methodChannel = MethodChannel("${Constants.Method_channelPath}$viewTypeId");
        methodChannel?.invokeMethod(Constants.Method_init).then((value) {
          methodChannel?.invokeMethod(Constants.Method_loadGlb, {
            Constants.ParamName_DirName: "light",
            Constants.ParamName_ModelName: "light"
          });
          methodChannel?.invokeMethod(Constants.Method_loadIndirectLight,
              {Constants.ParamName_Ibl: "venetian_crossroads_2k"});
        });
      },
      creationParams: const <String, dynamic>{'initParams': 'hello world'},
      creationParamsCodec: const StandardMessageCodec(),
    );
  }
}
