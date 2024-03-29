import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/provider_test.dart';
import 'package:flutter_module/page/review_records.dart';
import 'package:flutter_module/page/test_selector.dart';
import 'package:flutter_module/page/video_list.dart';
import 'package:flutter_module/page/video_list/video_list_sec.dart';
import 'package:flutter_module/page/video_list/video_list_selector.dart';
import 'package:flutter_module/page/video_page.dart';
import 'package:flutter_module/page/videolistwidget.dart';
import 'package:flutter_module/util/screen_extension.dart';
import 'package:flutter_module/widget/video/video_player_page.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

import '../app.dart';
import '../main.dart';
import 'cache_img_test.dart';
import 'easy_loading_page.dart';
import 'future_builder.dart';
import 'notify_list_by_key.dart';
import 'notify_list_by_provider.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _homeWidget();
  }
}

class _homeWidget extends State<StatefulWidget> {
  int _counter = 0;

  @override
  void initState() {
    String a = "";

    super.initState();
  }

  @override
  void deactivate() {
    // TODO: implement deactivate
    super.deactivate();
    print("---deactivate---");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SingleChildScrollView(
          child:
          Container(
            child: Column(
              children: [
                GestureDetector(
                  child: Text(
                    "调用原生方法$_counter",
                    style: TextStyle(fontSize: 20),
                  ),
                  onTap: () {
                    NativeFunc.openNavetiveOpenView();
                  },
                ),
                // Text(
                //   "测试20号字体",
                //   style: TextStyle(fontSize: 20.sp),
                // ),
                // Text(
                //   "测试18号字体",
                //   style: TextStyle(fontSize: 18.sp),
                // ),
                // Text(
                //   "测试17号字体",
                //   style: TextStyle(fontSize: 17.sp),
                // ),
                // Text(
                //   "测试16号字体",
                //   style: TextStyle(fontSize: 16.sp),
                // ),
                // Text(
                //   "测试15号字体",
                //   style: TextStyle(fontSize: 15.sp),
                // ),
                // Text(
                //   "测试14号字体",
                //   style: TextStyle(fontSize: 14.sp),
                // ),
                // Text(
                //   "测试13号字体",
                //   style: TextStyle(fontSize: 13.sp),
                // ),
                // Text(
                //   "测试12号字体",
                //   style: TextStyle(fontSize: 18.sp),
                // ),
                // Text(
                //   "热修复22222",
                //   style: TextStyle(fontSize: 20.sp),
                // ),
                _text(
                  child: GestureDetector(
                    child: Text('点击跳转视频列表'),
                    onTap: () {
                      //VideoListWidget
                      Navigator.of(context)
                          .push(MaterialPageRoute(builder: (context) {
                        return VideoPage();
                      }));
                    },
                  ),
                ),
                _text(
                  child: GestureDetector(
                    child: Text('点击跳转视频chewie'),
                    onTap: () {
                      //VideoListWidget
                      Navigator.of(context)
                          .push(MaterialPageRoute(builder: (context) {
                        return VideoList();
                      }));
                    },
                  ),
                ),
                _text(
                  child: GestureDetector(
                    child: Text('点击跳转富文本'),
                    onTap: () {
                      //VideoListWidget
                      Navigator.of(context)
                          .push(MaterialPageRoute(builder: (context) {
                        return ReviewRecords();
                      }));
                    },
                  ),
                ),
                // _text(
                //     child: GestureDetector(
                //   child: Text('provider测试'),
                //   onTap: () {
                //     //VideoListWidget
                //     Navigator.of(context)
                //         .push(MaterialPageRoute(builder: (context) {
                //       return ProviderTestDetail();
                //     }));
                //   },
                // )),
                _text(
                    child: GestureDetector(
                      child: Text('provider列表测试'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return NotifyProviderPage();
                        }));
                      },
                    )),

                _text(
                    child: GestureDetector(
                      child: Text('GlobelKey列表测试'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return GlobelKeyPage();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('测试图片缓存和内存'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return CacheImage();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('测试FutureBuilder'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return FutureBuilderPage();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('EasyLoading'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return EasyLoadingPage();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('测试播放器'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return VideoPlayerPage();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('跳转视频列表--consumer'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return VideoListSec();
                        }));
                      },
                    )),
                _text(
                    child: GestureDetector(
                      child: Text('跳转视频列表---selector'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return VideoListSelector();
                        }));
                      },
                    )),

                _text(
                    child: GestureDetector(
                      child: Text('测试selector---1111'),
                      onTap: () {
                        //VideoListWidget
                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (context) {
                          return TestSelector();
                        }));
                      },
                    )),
                SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      color: Colors.red,
                      width: 240.w,
                      child: Text("我的宽度是240w"),
                    ),
                    Container(
                      color: Colors.blue,
                      width: 240.w,
                      child: Text("我的宽度是240w"),
                    )
                  ],
                ),
              ],
            ),
          ),
        ));

    // Row(
    //   children: [
    //     Container(
    //       color: Colors.red,
    //       width: 240.w,
    //       child: Text("我的宽度是240w"),
    //     ),
    //     Container(
    //       color: Colors.blue,
    //       width: 240.w,
    //       child: Text("我的宽度是240w"),
    //     )
    //   ],
    // ),
    floatingActionButton: FloatingActionButton(
    onPressed: _incrementCounter,
    tooltip: 'Increment',
    child: const Icon(Icons.
    add
    )
    ,
    );
  }

  Widget _text({Widget? child}) {
    return Column(
      children: [
        SizedBox(
          height: 10,
        ),
        Container(
          padding: EdgeInsets.all(10),
          decoration: BoxDecoration(
            //设置四周边框
              border: new Border.all(width: 1, color: Colors.red),
              color: Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(15))),
          // padding: EdgeInsets.all(15.w),
          child: child,
        )
      ],
    );
  }

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });
  }
}
