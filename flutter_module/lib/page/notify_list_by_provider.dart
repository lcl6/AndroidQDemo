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
class NotifyProviderPage extends StatefulWidget {
  @override
  NotifyProviderPageState createState() {
    return NotifyProviderPageState();
  }
}

///provider 使用
///图片加载====》内存问题
class NotifyProviderPageState extends State<NotifyProviderPage> {
  List<IndexBean> list = [];

  @override
  void initState() {
    super.initState();

    for (int i = 0; i < 20; i++) {
      list.add(IndexBean(i, "item-------------------------$i"));
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
            return NotifyProviderPageItem(
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

class NotifyProviderPageItem extends StatefulWidget {
  final IndexBean item;

  @override
  State<StatefulWidget> createState() {
    return NotifyProviderPageItemState();
  }

  // GlobelKeyPageItem({required this.item, required Key key}) : super(key: key);

  NotifyProviderPageItem({
    required this.item,
  });
}

class NotifyProviderPageItemState extends State<NotifyProviderPageItem> {
  @override
  Widget build(BuildContext context) {
    print("--build--${widget.item.index}");
    final IndexBean item = widget.item;

    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) => IndexBean(item.index, item.name),
        )
      ],
      builder: (cxt, child) {
        return Consumer<IndexBean>(builder: (cxt, value, child) {
          // print("--Consumer--${widget.item.index}");
          print("--Consumer--${value.index}");
          return Container(
            color: Colors.black54,
            child: GestureDetector(
                onTap: () {
                  cxt.read<IndexBean>().addIndex();
                },
                child: Container(
                  padding: EdgeInsets.all(20),
                  child: Text(
                    '点击文字--------------------------${value.index}',
                  ),
                )),
          );
        });
      },
    );
  }
}
