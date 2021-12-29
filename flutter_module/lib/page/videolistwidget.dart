import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class VideoListWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return VideoListState();
  }
}

class VideoListState extends State {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: _listWidget(),
      ),
    );
  }
}

Widget _listWidget() {
  return ListView.builder(
      itemCount: 20,
      itemBuilder: (BuildContext context, int index){

    return _itemWidget(index);
  });
}

Widget _itemWidget(int index) {
  return Container(child: Text("item----$index"),);
}
