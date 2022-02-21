import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/config/appconfig.dart';
import 'package:flutter_module/page/home.dart';
import 'package:flutter_module/route/route_factory.dart';
import 'package:flutter_module/util/screen_adapter.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:package_info/package_info.dart';

import 'env.dart';

void runMain(bool debug, String defaultRouteName) {
  runApp(MyApp());
  // //以下两行 设置android状态栏为透明的沉浸。
  // // 写在组件渲染之后，是为了在渲染后进行set赋值，覆盖状态栏，写在渲染之前MaterialApp组件会覆盖掉这个值。
  // SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle(
  //   statusBarColor: Colors.transparent,
  // );
  // SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
}

class MyApp extends StatefulWidget {
  @override
  State<MyApp> createState() {
    return _MyHomePageState();
  }
}

class _MyHomePageState extends State<MyApp> {
  static const MethodChannel _channel =
      MethodChannel("com.example.flutter_module");
  static Map? _temp;

  @override
  void initState() {
    super.initState();

    _channel.setMethodCallHandler(_handleMethod);
    PackageInfo.fromPlatform().then((v) {
      Constant().name = v.appName;
      Constant().versionName = v.version;
      print(
          "packageInfo : appName=${v.appName}, version=${v.version}, code = ${v.buildNumber}");
      // setState(() {});
    });
  }

  /// 原生调用flutter
  Future<dynamic> _handleMethod(MethodCall call) async {
    print("flutter-参数-_handleMethod-${call.arguments}");
    switch (call.method) {
      case "startRoute":
        if (_temp != null) {
          Future.delayed(Duration(milliseconds: 10)).then((value) {
            if (_temp != null) _pushName(_temp!);
            _temp = null;
          });
        }
        break;
      case "test":
        print("test success");
        break;
      default:
        throw new UnsupportedError("Unrecognized Event");
    }
  }

  @override
  Widget build(BuildContext context) {
    // Adapt.initialize(context,standardWidth:480);
   return ScreenUtilInit( designSize: Size(480, 960), minTextAdapt: true,
     splitScreenMode: true, builder: () {
       return  MaterialApp(
         initialRoute: Env.homeRoute,
         onGenerateRoute: RouterFactory().createRoute,
         navigatorKey: Constant().navigatorKey,
         title: "flutter",
         theme: ThemeData(
           primaryColor: Colors.white,
           scaffoldBackgroundColor: Color(0xfff8f9fb),
         ),
         builder: (context,widget){
           ScreenUtil.setContext(context);
           return MediaQuery(
             //Setting font does not change with system font size
             data: MediaQuery.of(context).copyWith(textScaleFactor: 1.0),
             child: widget!,
           );
         },
       );
     },);
  }


  _pushName(Map map) {
    String route = map["_#_route_#_"];
    bool result = map["_#_result_#_"];

    print("flutter-参数-route-${route}");
    Future f = Constant()
        .currentState
        .pushNamedAndRemoveUntil(route, (route) => false, arguments: map);
    if (result) {
      f.then((value) => _channel.invokeMethod("forResult", value));
    }
  }
}

///调用原生方法
class NativeFunc {
  static Future<dynamic> openNavetiveOpenView() {
    return _MyHomePageState._channel
        .invokeMethod("open_view", {'url': 'www.baidu'});
  }
}
