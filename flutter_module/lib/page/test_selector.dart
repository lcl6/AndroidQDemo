import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'bean/pic_list_selector.dart';

class TestSelector extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestSelectorState();
  }
}

class TestSelectorState extends State<StatefulWidget> {
  var picProvide = PicProvider();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    picProvide.pic =
        "https://alifei05.cfp.cn/creative/vcg/veer/1600water/veer-340977164.jpg";
    picProvide.title = "天天向上";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("测试selector"),
      ),
      body: ChangeNotifierProvider(
        create: (context) => picProvide,
        child: Column(
          children: [
            GestureDetector(
              child: Column(
                children: [
                  Selector(builder:
                      (BuildContext context, String data, Widget? child) {
                    return GestureDetector(
                      onTap: () {
                        context.read<PicProvider>().changePic(
                            "https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg");
                      },
                      child: CachedNetworkImage(
                        imageUrl: data,
                        // "https://alifei05.cfp.cn/creative/vcg/veer/1600water/veer-340977164.jpg",
                        placeholder: (context, url) =>
                            CircularProgressIndicator(),
                        errorWidget: (context, url, error) => Icon(Icons.error),
                      ),
                    );
                  }, selector: (BuildContext context, PicProvider provider) {
                    return context.read<PicProvider>().pic;
                  }),
                  Selector(
                    builder:
                        (BuildContext context, String data, Widget? child) {
                      return GestureDetector(
                        child: Container(width:double.infinity,color:Colors.blue,child:Container(margin:EdgeInsets.all(20),child:  Text("当前的标题是：$data"),)) ,
                        onTap: () {
                          context.read<PicProvider>().changeTitle("好好学习");
                        },
                      );
                    },
                    selector: (BuildContext context, PicProvider provider) {
                      return context.read<PicProvider>().title;
                    },
                    shouldRebuild: (String pre, String next) {
                      print("--pre---${pre}-----next---${next}");
                      return pre != next;
                    },
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
