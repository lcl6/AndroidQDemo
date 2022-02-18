import 'package:flutter/cupertino.dart';

typedef RouterWidgetBuilder = Widget Function(
    BuildContext context, dynamic arguments);

class RouterInfo {
  ///路径
  final String path;

  ///构建器
  final RouterWidgetBuilder builder;
  RouterInfo(
      {required this.path, required this.builder,});
}