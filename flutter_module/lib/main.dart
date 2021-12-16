import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/config/appconfig.dart';
import 'package:flutter_module/page/home.dart';
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
      default:
        throw new UnsupportedError("Unrecognized Event");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child:
        HomePage()
      ),
    );
  }
}

