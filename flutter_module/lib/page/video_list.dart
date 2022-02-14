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
  //滚动控制器
  ScrollController _scrollController =
      new ScrollController(initialScrollOffset: 0);

  //itemHight  向上滑动的距离
  double itemHight = 0;

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
    //设置滚动监听  为了实现滑动到一定位置的时候视频关闭
    _scrollController.addListener(() {
      //_scrollController.position.pixels - initPosition   列表从当前位置向上滑动的距离
      //itemHight  视频应该向上滑动的距离  才能消失
      //initPosition - _scrollController.position.pixels   列表从当前位置向下滑动的距离
      //upHight    视频应该向下滑动的距离  才能消失
      if (itemHight > 0) {
        if (_scrollController.position.pixels - initPosition > itemHight ||
            initPosition - _scrollController.position.pixels > upHight) {
          print('控件该隐藏了');
          //获取点击的视频 然后隐藏   并且itemHight =0
          VideoBean bean = videolist[clickPosition];
          setState(() {
            bean.isSeeVideo = false;
            itemHight = 0;
          });
        }
      }
    });
    getApiData();
  }

  getApiData() {
    for (int i = 0; i < 6; i++) {
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
      controller: _scrollController,
      itemBuilder: (context, i) {
        GlobalKey firstKey = GlobalKey();
        GlobalKey secondKey = GlobalKey();
        return GestureDetector(
          onTap: () {
            itemClick(secondKey, i);
          },
          child: buildItems(videolist[i], firstKey, secondKey),
        );
      },
      itemCount: videolist.length,
    );
  }

  buildItems(
    VideoBean bean,
    GlobalKey firstKey,
    GlobalKey secondKey,
  ) {
    var item;
    if (bean.isSeeVideo) {
      item = Container(
        height: 300.w,
        key: firstKey,
        child: ChewiePage(
          videoPlayerController:
              VideoPlayerController.asset("assets/videos/wyy2.mp4"),
          key: firstKey,
          looping: false,
        ),
      );
    } else {
      item = Container(
        key: secondKey,
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

  itemClick(GlobalKey secondKey, int position) {
    RenderBox?  renderBox = secondKey.currentContext?.findRenderObject() as RenderBox?;
    var offset = renderBox!.localToGlobal(Offset(0.0, renderBox.size.height));

    setState(() {
      //获取当前列表滚动的距离
      itemHight = offset.dy;
      clickPosition = position;
    });
    print('$itemHight');
    for (int j = 0; j < videolist.length; j++) {
      VideoBean videoBean = videolist[j];
      setState(() {
        videoBean.isSeeVideo = false;
      });
    }
    //  bus.sendBroadcast('ChewieListItem');
    VideoBean bean = videolist[position];
    setState(() {
      bean.isSeeVideo = true;
      initPosition = _scrollController.position.pixels;
      //屏幕的高度-视频所处的高度 +视频的高度
      upHight = MediaQuery.of(context).size.height -
          itemHight +
          MediaQuery.of(context).size.width * 9 / 16;
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
    required Key key,
  }) : super(key: key);

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
      showOptions: false,
      customControls: MyMaterialControls(),
      autoInitialize: true,
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
