import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';

import 'app_config.dart';
import 'custom/custom_page.dart';

class CacheImage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return CacheImageState();
  }
}
///图片加载====》内存问题
class CacheImageState extends State<CacheImage> {
  List<String> list =[];
  @override
  void initState() {
    super.initState();


    for(int i=0;i < 100;i++){
      if(i % 2 ==0){
        list.add('https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg');
      }else{
        list.add('https://scpic.chinaz.net/files/pic/pic9/202201/apic38001.jpg');
      }

    }
    setState(() {
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: ListView.builder(itemBuilder:(BuildContext context, int index) {

            return Container(child:
              Image.network(list[index]),
            // CachedNetworkImage(
            //   imageUrl:list[index] ,
            //   placeholder: (context, url) => CircularProgressIndicator(),
            //   errorWidget: (context, url, error) => Icon(Icons.error),
            // ,
            );
          },shrinkWrap: true,itemCount: list.length,)
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
