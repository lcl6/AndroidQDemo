import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/config/appconfig.dart';
import 'package:flutter_module/page/home.dart';
import 'package:flutter_module/util/screen_adapter.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:package_info/package_info.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Deo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

///调用原生方法
class NativeFunc{
  static Future<dynamic> openNavetiveOpenView(){
   return _MyHomePageState._channel.invokeMethod("open_view", {'url':'www.baidu'});
  }

}


class _MyHomePageState extends State<MyHomePage> {
  static const MethodChannel _channel = MethodChannel("com.example.flutter_module");



  @override
  void initState() {
    super.initState();
    _channel.setMethodCallHandler(_handleMethod);
    PackageInfo.fromPlatform().then((v) {
      Constant().name = v.appName;
      Constant().versionName = v.version;
      print("packageInfo : appName=${v.appName}, version=${v.version}, code = ${v.buildNumber}");
      setState(() {});
    });
  }
  /// 原生调用flutter
  Future<dynamic>  _handleMethod(MethodCall call) async{
    print(call.arguments);





    switch (call.method) {
      case "test":
        print("test success");
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

    // ScreenUtil.init(
    //     BoxConstraints(
    //         maxWidth: MediaQuery.of(context).size.width,
    //         maxHeight: MediaQuery.of(context).size.height),
    //     designSize: Size(480, 960),
    //     orientation: Orientation.portrait);

    // return ScreenUtilInit(designSize:Size(480,960),builder:mainWidget);


    WidgetsBinding.instance!.addPostFrameCallback((timeStamp) {
      Adapt.initialize(context,standardWidth:480);
    });
    return  mainWidget();

  }
  Widget mainWidget(){

    // var defaultSize = ScreenUtil.defaultSize;
    //
    // print("--flutter---width---${defaultSize.width}----height----${defaultSize.height}" );

    ///pixelRatio  这个变小了  原本是2  现在是 0.8999999761581421
    ///flutter---pixelRatio---3.0----screenWidth----360.0----screnHeight----700.0----scaleWidth----0.75----scaleHeight----0.7291666666666666   原生宽度 360
    /// --flutter---pixelRatio---1.125----screenWidth----960.0----screnHeight----1706.6666666666667----scaleWidth----2.0----scaleHeight----1.777777777777778   原生宽度 960
    /// --------------------------------------------------------------
    /// 如果是横屏
    /// --flutter---pixelRatio---3.0----screenWidth----640.0----screnHeight----700.0----scaleWidth----1.3333333333333333----scaleHeight----0.7291666666666666
    ///
    // double? pixelRatio = ScreenUtil().pixelRatio;// 类似原生的desity 受原生适配框架的影响 所以flutter 需要取消原生适配
    // var screenWidth = ScreenUtil().screenWidth;//屏幕的宽 dp
    // var screnHeight = ScreenUtil().screenHeight;
    // var scaleHeight = ScreenUtil().scaleHeight;//  屏幕高/UI高
    // var scaleWidth = ScreenUtil().scaleWidth;
    //
    // print("--flutter---pixelRatio---${pixelRatio}----screenWidth----${screenWidth}----screnHeight----${screnHeight}----scaleWidth----${scaleWidth}----scaleHeight----${scaleHeight}" );
    Adapt.initialize(context,standardWidth:480);

    return   Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
          child:  rountWidget("home"),
      ),
    );
  }

  Widget rountWidget(String route){

    switch(route){
      case "home":
        return HomePage();
    }
    return Container(color: Colors.white,);
  }

}

