import 'package:flutter/cupertino.dart';
import 'package:provider/provider.dart';

class GirlModel with ChangeNotifier{

  List<String> _list=[];

  GirlModel(this._list);

  List<String> get()=>_list;

  void setData(List<String> list){
    _list=list;
    notifyListeners();
  }


  void updateItem(int index){
    _list[index]='https://pic.netbian.com/uploads/allimg/220104/232651-16413100110114.jpg';
    notifyListeners();
  }




}