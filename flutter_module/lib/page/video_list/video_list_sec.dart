import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/cache_img_test.dart';
import 'package:flutter_module/page/video_list/picture_module.dart';
import 'package:provider/provider.dart';

class VideoListSec extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return VideoListSecState();
  }
}

class VideoListSecState extends State {
  List<PictureBean> list = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    for (int i = 0; i < 20; i++) {
      list.add(PictureBean(
          "https://alifei05.cfp.cn/creative/vcg/veer/1600water/veer-340977164.jpg",false));
    }
   // setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: list.length,
        itemBuilder: (cxt, position) {
          return _itemWidget(position);
        });
  }

  Widget _itemWidget(int position) {
    final item = list[position];
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (context) => PictureBean(item.pic,item.playVideo)),
        ],
        child: Consumer<PictureBean>(
          builder: (cxt, value, child) {
            print("--Consumer--${value.playVideo}");
            return GestureDetector(
              onTap: () {
                cxt.read<PictureBean>().updateStatus(true);
                item.updateStatus(true);
              },
              child: _playWidget(value),
            );
          },
        ));
  }

  Widget _playWidget(PictureBean value) {
    // print("_playWidget--${value.playVideo}");
    if (value.playVideo)
      return Container(
        height: 200,
        color: Colors.blue,
      );
    return Container(
      child: CachedNetworkImage(
        imageUrl: value.pic,
        // "https://alifei05.cfp.cn/creative/vcg/veer/1600water/veer-340977164.jpg",
        placeholder: (context, url) => CircularProgressIndicator(),
        errorWidget: (context, url, error) => Icon(Icons.error),
      ),
    );
  }
}
