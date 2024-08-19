import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:show_model_demo/model_view/model_view.dart';
import 'package:show_model_demo/spline_view/spline_view.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State createState() {
    return _HomePageState();
  }
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          children: [
            Container(
              height: ScreenUtil().screenHeight -
                  (ScreenUtil().bottomBarHeight + ScreenUtil().statusBarHeight),
              child: SplineView(),
            )
          ],
        ),
      ),
    );
  }
}
