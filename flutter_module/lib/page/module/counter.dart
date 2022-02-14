import 'package:flutter/cupertino.dart';

import 'item_model.dart';

class Counter with ChangeNotifier{
  int _count;

  List<ItemModel> list=[];



  Counter(this._count);


  void addList(){
    for( int i=0;i<10;i++){
      var itemModel = ItemModel(i);
      list.add(itemModel);
    }

    notifyListeners();
  }

  void addIndex(int index){
    list[index].index++;
    notifyListeners();
  }
  void add(){
    _count++;
    notifyListeners();
  }
  get count=>_count;

}