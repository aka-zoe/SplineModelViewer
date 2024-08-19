import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:show_model_demo/home_page.dart';

void main() async {
  await ScreenUtil.ensureScreenSize();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: const Size(1080, 1920),
      builder: (context, child) {
        return MaterialApp(
          theme: ThemeData(
            tabBarTheme: const TabBarTheme(dividerColor: Colors.transparent),
            useMaterial3: true,
          ),
          home: const HomePage(),
        );
      },
    );
  }
}
