import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../main.dart';

class HomePage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return _homeWidget();
  }
}

class _homeWidget extends State<StatefulWidget>{
  int _counter = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(body: Center(child:

        GestureDetector(child:  Text("调用原生方法$_counter"),onTap: (){
          NativeFunc.openNavetiveOpenView();
        },)),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }


  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });
  }
}

