import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_adapter/flexible_stateless_widget.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';

import 'app_config.dart';
import 'custom/custom_page.dart';


class ProviderTestDetail extends StatefulWidget{

  @override
  ProviderTestState createState() {
   return ProviderTestState();
  }
}

///todo 无context 导航
///provider 使用
///图片加载====》内存问题
class ProviderTestState extends State<ProviderTestDetail> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: new WillPopScope(
        onWillPop: () {
          print("flutter--WillPopScope");
          return new Future.value(false);
        },
        child: Scaffold(
          body: Container(
            child: Row(
              children: [],
            ),
          ),
        ),
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
