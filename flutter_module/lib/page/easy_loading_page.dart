import 'package:flutter/material.dart';

class EasyLoadingPage extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      body: Container(
        child: Column(
          children: [
            _text(
              child: GestureDetector(
                child: Text('EasyLoading加载中'),
                onTap: (){
                },
              ),
            ),
            _text(
              child: GestureDetector(
                child: Text('EasyLoading加载中'),
                onTap: (){
                },
              ),
            ),
            _text(
              child: GestureDetector(
                child: Text('EasyLoading弹窗'),
                onTap: (){
                },
              ),
            ),
            _text(
              child: GestureDetector(
                child: Text('EasyLoading 取消'),
                onTap: (){
                },
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _text({Widget? child}) {
    return Column(
      children: [
        SizedBox(
          height: 10,
        ),
        Container(
          padding: EdgeInsets.all(10),
          decoration: BoxDecoration(
              //设置四周边框
              border: new Border.all(width: 1, color: Colors.red),
              color: Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(15))),
          // padding: EdgeInsets.all(15.w),
          child: child,
        )
      ],
    );
  }
}
