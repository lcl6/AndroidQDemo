
import 'package:flutter/cupertino.dart';

class ItemModel with ChangeNotifier{
  int index;
  ItemModel(this.index);

  void setIndex(int i){
    index= i;
    notifyListeners();

  }



}