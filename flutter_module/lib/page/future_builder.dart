import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:provider/provider.dart';

import 'app_config.dart';
import 'custom/custom_page.dart';
import 'module/girl_model.dart';

class FutureBuilderPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return FutureBuilderPageState();
  }
}

///图片加载====》内存问题
class FutureBuilderPageState extends State<FutureBuilderPage> {
  List<String> list = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: MultiProvider(
            providers: [
              ChangeNotifierProvider(
                create: (_) => GirlModel(list),
              )
            ],
            builder: (cxt, child) {
              print("-----执行了builder");
              _initData(cxt);
              return Container(
                child: ListView.builder(
                  itemBuilder: (BuildContext context, int index) {
                    return GestureDetector(
                      onTap: () {
                        var girl = context.read<GirlModel>();
                        girl.updateItem(index);
                      },
                      child: Consumer<GirlModel>(builder: (cxt, value, child) {
                        print("执行了---Consumer");
                        return Image.network(value.get()[index]);
                      },),
                      // child: Container(
                      //     child: FutureBuilder(
                      //   future: _getImgUrl(value, index),
                      //   builder: (BuildContext context,
                      //       AsyncSnapshot<dynamic> snapshot) {
                      //     switch (snapshot.connectionState) {
                      //       case ConnectionState.none:
                      //         print("还没有开始网络请求");
                      //         return Text("还没有开始网络请求");
                      //       case ConnectionState.active:
                      //         print("active");
                      //         return Text("active");
                      //       case ConnectionState.waiting:
                      //         print("waiting");
                      //         return Text("waiting");
                      //       case ConnectionState.done:
                      //         print("done---${snapshot.data}");
                      //         return Image.network(snapshot.data);
                      //     }
                      //   },
                      // )),
                    );
                  },
                  shrinkWrap: true,
                  itemCount: cxt
                      .watch<GirlModel>()
                      .get()
                      .length,
                ),
              );
            }),
      ),
    );
  }

  Future<String> _getImgUrl(GirlModel value, int index) async {
    return Future.delayed(Duration(milliseconds: 100), () {
      return value.get()[index];
    });
  }

  _initData(BuildContext context) {
    var girl = context.read<GirlModel>();
    for (int i = 0; i < 100; i++) {
      if (i % 2 == 0) {
        list.add(
            'https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg');
      } else {
        list.add(
            'https://scpic.chinaz.net/files/pic/pic9/202201/apic38001.jpg');
      }
    }
    girl.setData(list);
    print("-----执行了_initDatar");
  }

  @override
  void dispose() {
    super.dispose();
  }


}
