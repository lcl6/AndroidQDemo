import 'package:flutter/material.dart';

class PictureBean with ChangeNotifier{
  late String pic;

  bool playVideo=false;


  PictureBean(this.pic);

  void updateStatus(bool play){
    playVideo=play;
    notifyListeners();
  }




}