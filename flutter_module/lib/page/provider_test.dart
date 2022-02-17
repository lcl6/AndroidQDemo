import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_adapter/flexible_stateless_widget.dart';
import 'package:flutter_module/page/module/counter.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:provider/provider.dart';

import 'app_config.dart';
import 'custom/custom_page.dart';
import 'app_config.dart';

class ProviderTestDetail extends StatefulWidget {
  @override
  ProviderTestState createState() {
    return ProviderTestState();
  }
}
///provider 使用
///图片加载====》内存问题
class ProviderTestState extends State<ProviderTestDetail> {

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      builder: (cxt,_){
        _initData(cxt);
        return new WillPopScope(
          onWillPop: () {
            print("flutter--WillPopScope");
            return new Future.value(true);
          },
          child: Scaffold(
            body: Container(
              child: Consumer<Counter>(builder: (context,value, Widget? child){

                return  ListView.separated(
                    itemCount:value.list.length,
                    itemBuilder: (BuildContext context, int index) {
                      return

                        Container(
                          padding: EdgeInsets.all(20),
                          child: GestureDetector(
                              onTap: () {
                                value.addIndex(index);
                              },
                              child:
                              Consumer<Counter>(builder: (BuildContext context, value, Widget? child) {
                                return Text('点击文字-----${value.list[index].index}');
                              },)

                          ),
                        );
                    }, separatorBuilder: (BuildContext context, int index) {
                      return Container(width:double.infinity,height: 1,color: Colors.black12,);
                },);
                ;
              }),
            ),
          ),
        );
      },
      providers: [ChangeNotifierProvider(create: (_) => Counter(0))],
    );
  }

  void _initData(BuildContext cxt) {
    var count = cxt.read<Counter>();
    count.addList();
  }
}
