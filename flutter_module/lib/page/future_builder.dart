import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:provider/provider.dart';

import 'app_config.dart';
import 'bean/girl_bean.dart';
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
  List<GirlBean> list = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _initData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Container(
          child: ListView.builder(
            itemBuilder: (BuildContext context, int index) {
              return GirlItem(item: list[index]);
            },
            shrinkWrap: true,
            itemCount: list.length,
          ),
        ),
      ),
    );
  }

  _initData() {
    for (int i = 0; i < 100; i++) {
      if (i % 2 == 0) {
        list.add(GirlBean(
            'https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg'));
      } else {
        list.add(GirlBean(
            'https://scpic.chinaz.net/files/pic/pic9/202201/apic38001.jpg'));
      }
    }
    setState(() {});
  }

  @override
  void dispose() {
    super.dispose();
  }
}

class GirlItem extends StatefulWidget {
  final GirlBean item;

  GirlItem({required this.item});

  @override
  State<StatefulWidget> createState() {
    return GirlItemState();
  }
}

class GirlItemState extends State<GirlItem> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        widget.item.url =
            'https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg';
        setState(() {});
      },
      child: Container(
          width: double.infinity,
          child: FutureBuilder(
            future: _getImgUrl(widget.item.url),
            builder: (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
              switch (snapshot.connectionState) {
                case ConnectionState.none:
                  print("还没有开始网络请求");
                  return Text("还没有开始网络请求");
                case ConnectionState.active:
                  print("active");
                  return Text("active");
                case ConnectionState.waiting:
                  print("waiting");
                  return Text("waiting");
                case ConnectionState.done:
                  print("done---${snapshot.data}");
                  return Image.network(
                    snapshot.data,
                  );
              }
            },
          )),
    );
  }

  Future<String> _getImgUrl(String url) async {
    return Future.delayed(Duration(milliseconds: 100), () {
      return url;
    });
  }
}
