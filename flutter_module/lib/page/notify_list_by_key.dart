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
import 'bean/index_item_bean.dart';
import 'custom/custom_page.dart';
import 'app_config.dart';

///局部刷新  通过globelKey
class GlobelKeyPage extends StatefulWidget {
  @override
  GlobelKeyPageState createState() {
    return GlobelKeyPageState();
  }
}

///provider 使用
///图片加载====》内存问题
class GlobelKeyPageState extends State<GlobelKeyPage> {
  List<IndexBean> list=[];

  @override
  void initState() {
    super.initState();

    for( int i = 0;i<20;i++){
      list.add(IndexBean(i,"item-------------------------$i"));
    }
    setState(() {});


  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: ListView.separated(
          itemCount: list.length,
          itemBuilder: (BuildContext context, int index) {
            // print("--separated-");
            return GlobelKeyPageItem(
              item: list[index],
              // key: GlobalObjectKey(index),
            );
          },
          separatorBuilder: (BuildContext context, int index) {
            return Container(
              width: double.infinity,
              height: 1,
              color: Colors.black12,
            );
          },
        ),
      ),
    );
  }
}

class GlobelKeyPageItem extends StatefulWidget {
  final IndexBean item;

  @override
  State<StatefulWidget> createState() {
    return GlobelKeyPageItemState();
  }

  // GlobelKeyPageItem({required this.item, required Key key}) : super(key: key);

  GlobelKeyPageItem({required this.item, }) ;
}

class GlobelKeyPageItemState extends State<GlobelKeyPageItem> {
  @override
  Widget build(BuildContext context) {
    // print("--build--${widget.item.index}");
    final  item = widget.item;
    return Container(
      padding: EdgeInsets.all(20),
      child: GestureDetector(
          onTap: () {
            var i = item.index + 1 ;
            widget.item.index=i;;
            setState(() {

            });
          },
          child: Text(
            '点击文字--------------------------${widget.item.index}',
          )),
    );
  }
}
