import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/cache_img_test.dart';
import 'package:flutter_module/page/video_list/picture_module.dart';
import 'package:provider/provider.dart';

class VideoListSelector extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return VideoListSelectorState();
  }
}

class VideoListSelectorState extends State {
  List<PictureBean> list = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    for (int i = 0; i < 20; i++) {
      list.add(PictureBean(
          "https://alifei05.cfp.cn/creative/vcg/veer/1600water/veer-340977164.jpg",
          false));
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
          itemCount: list.length,
          itemBuilder: (cxt, position) {
            return _itemWidget(position);
          }),
    );
  }

  Widget _itemWidget(int position) {
    final item = list[position];
    print("-----_itemWidget-----");
    return ChangeNotifierProvider(
        create: (BuildContext context) {
          print("-----create-----");
          return PictureBean(item.pic, item.playVideo);
        },
        child: Column(
          children: [
            Selector(
              selector: (BuildContext context, PictureBean value) {
                return value;
              },
              builder: (BuildContext cxt, PictureBean value, Widget? child) {
                print("-----builder-----");
                return Column(
                  children: [
                    GestureDetector(
                      onTap: () {
                        print("---value--${value.playVideo}");
                        item.updateStatus(true);
                        cxt.read<PictureBean>().updateStatus(true);
                      },
                      child: _playWidget(value),
                    ),
                  ],
                );
              },
              shouldRebuild: (PictureBean pre, PictureBean next) {
                print("---pre--${pre.playVideo}----next--${next.playVideo}");
                return pre.playVideo == next.playVideo;
              },
            ),
            Selector(
                builder: (BuildContext context, String data, Widget? child) {
              return GestureDetector(
                onTap: (){
                  context.read<PictureBean>().updatePic("https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg");
                  item.updatePic("https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg");
                },
                child: Container(
                  color: Colors.white,
                  child: Container( margin: EdgeInsets.all(10),child: Text(data),)
                  ,
                ),
              );
            }, selector: (BuildContext context, PictureBean bean) {
              return bean.pic;
            })
          ],
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
