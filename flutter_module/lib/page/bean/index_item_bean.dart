import 'package:flutter/cupertino.dart';

class IndexBean with ChangeNotifier{
  int index;
  String name;

  IndexBean(this.index, this.name);


  void addIndex(){
    index++;
    notifyListeners();
  }

}