import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../app_config.dart';


///默认灰色背景，分割线，列表控件
Widget getCustomPage(
    {PreferredSizeWidget? appBar,
    Color? bgColor = AppColor.GrayColor9FB,//const Color(0xFFF7F9FB),//F5F5F5
      List<Widget>? children,
      Widget? body,
      bool hasLine = false,
    bool? resizeToAvoidBottomInset = false,ScrollController? controller}) {
  return CustomPage(
    appBar: appBar,
    backgroundColor: bgColor,
    resizeToAvoidBottomInset: resizeToAvoidBottomInset,
    body: Container(
      // color: bgColor,
      height: double.infinity,
      child: body ?? SingleChildScrollView(
        controller: controller,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            if(children != null)...children,
          ],
        ),
      ),
    ),
  );
}

///设置基础背景：状态栏黑色字体
///onWillPop不为空，则使用
///如果body有值，则使用，否则使用children并带一条分割线
class CustomPage extends StatelessWidget {
  final WillPopCallback? onWillPop;
  final PreferredSizeWidget? appBar;
  final Widget? body;
  final Widget? floatingActionButton;
  final Widget? bottomNavigationBar;
  final List<Widget>? children;
  final bool? resizeToAvoidBottomInset;
  final SystemUiOverlayStyle? style;
  final Color? backgroundColor;

  const CustomPage(
      {Key? key,
      this.onWillPop,
      this.appBar,
      this.body,
      this.children,
      this.floatingActionButton,
      this.bottomNavigationBar,
      this.style,
      this.backgroundColor,
      this.resizeToAvoidBottomInset = false})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return _container();
  }

  Widget _container() {
    //设置状态栏 文字颜色
    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: style ?? SystemUiOverlayStyle.dark,
      child: Scaffold(
        appBar: appBar,
        body: WillPopScope(
          child: body ?? _column(),
          onWillPop: onWillPop,
        ),
        backgroundColor: backgroundColor,
        //输入框抵住键盘 内容不随键盘滚动
        resizeToAvoidBottomInset: resizeToAvoidBottomInset,
        floatingActionButton: floatingActionButton,
        bottomNavigationBar: bottomNavigationBar,
      ),
    );
  }

  Widget _column() {
    if(children == null)return Container();
    return Stack(
      children: [
        ///三点运算符：遍历取值
        ...children!,

        ///默认在顶部
      ],
    );
  }
}
