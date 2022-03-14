import 'package:flutter/cupertino.dart';

class PicProvider with ChangeNotifier{

  var pic="";
  bool show=false;
  var title="";

  void changePic(String pic){
    this.pic=pic;
    notifyListeners();
  }

  void changeTitle(String title){
    this.title=title;
    notifyListeners();
  }

}