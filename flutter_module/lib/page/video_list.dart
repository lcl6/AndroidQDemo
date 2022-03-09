import 'package:chewie/chewie.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/bean/video_bean.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:video_player/video_player.dart';

import 'control/my_material_controls.dart';

class VideoList extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return VideoListState();
  }
}

class VideoListState extends State<VideoList> {
  //点击item的 角标
  int clickPosition = 0;

  //列表滑动的距离的初始值
  double initPosition = 0;

  //向下滑动的距离
  double upHight = 0;
  List<VideoBean> videolist = [];

  @override
  void initState() {
    super.initState();
    getApiData();
  }

  getApiData() {
    for (int i = 0; i < 100; i++) {
      VideoBean bean = new VideoBean();
      videolist.add(bean);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Video Player'),
      ),
      body: getListView(context),
    );
  }

  //获取列表
  getListView(BuildContext context) {
    return ListView.builder(
      itemBuilder: (context, i) {
        return GestureDetector(
          onTap: () {
            itemClick(i);
          },
          child: buildItems(videolist[i]),
        );
      },
      itemCount: videolist.length,
    );
  }

  buildItems(
    VideoBean bean,
  ) {
    var item;
    if (bean.isSeeVideo) {
      item = Container(
        height: 300.w,
        child: ChewiePage(
          videoPlayerController:
              VideoPlayerController.asset("assets/videos/wyy2.mp4"),
          looping: false,
        ),
      );
    } else {
      item = Container(
        height: MediaQuery.of(context).size.width * 9 / 16,
        width: MediaQuery.of(context).size.width,
        margin: EdgeInsets.all(10),
        color: Colors.orange,
        child: Center(
          child: Text(
            'data',
            style: TextStyle(color: Colors.black, fontSize: 17),
          ),
        ),
      );
    }
    return item;
  }

  itemClick(int position) {
    setState(() {
      //获取当前列表滚动的距离
      clickPosition = position;
    });
    // print('$itemHight');
    for (int j = 0; j < videolist.length; j++) {
      VideoBean videoBean = videolist[j];
      setState(() {
        videoBean.isSeeVideo = false;
      });
    }
    VideoBean bean = videolist[position];
    setState(() {
      bean.isSeeVideo = true;
    });
  }
}

//视频的item页面
class ChewiePage extends StatefulWidget {
  final VideoPlayerController videoPlayerController;
  final bool looping;

  ChewiePage({
    required this.videoPlayerController,
    required this.looping,
  });

  @override
  _ChewiePageState createState() => _ChewiePageState();
}

class _ChewiePageState extends State<ChewiePage> {
  late ChewieController _chewieController;

  @override
  void initState() {
    super.initState();
    //创建Chewie 的控制器
    _chewieController = ChewieController(
      videoPlayerController: widget.videoPlayerController,
      aspectRatio: 16 / 9,
      fullScreenByDefault: true,
      showOptions: false,
      customControls: MyMaterialControls(),
      autoInitialize: false,
      looping: widget.looping,
      errorBuilder: (context, errorMessage) {
        return Center(
          child: Text(
            errorMessage,
            style: TextStyle(color: Colors.white),
          ),
        );
      },
    );

    print('控制器设置');
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(1.0),
      child: Chewie(
        controller: _chewieController,
      ),
    );
  }

  @override
  void dispose() {
    //为了满足全屏时候 控制器不被直接销毁 判断只有不是全屏的时候 才允许控制器被销毁
    if (_chewieController != null && !_chewieController.isFullScreen) {
      widget.videoPlayerController.dispose();
      _chewieController.dispose();
      print('控制器销毁');
    }
    super.dispose();
  }
}
