import 'package:flutter/material.dart';

class PictureBean with ChangeNotifier{
  late String pic;

  bool playVideo=false;



  PictureBean(this.pic,this.playVideo);

  void updateStatus(bool play){
    playVideo=play;
    notifyListeners();
  }


  void updatePic(String pic){
    this.pic=pic;
    notifyListeners();
  }



}