
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/route/route_table.dart';

class RouterFactory {
  final RouteTable tables = RouteTable();
  static RouterFactory? _instance;

  factory RouterFactory() {
    RouterFactory? instance = _instance;
    if (instance != null) {
      return instance;
    }
    instance = RouterFactory._internal();
    _instance = instance;
    return instance;
  }

  RouterFactory._internal();

  Route<dynamic> createRoute(RouteSettings settings) {
    final routeName = settings.name;
    final Map args =
    settings.arguments == null ? {} : (settings.arguments as Map);
    final info = tables.getRouterInfo(routeName);
    return route(
        builder: (context) => info!.builder.call(context, args),
       );
  }


  /// 默认的路由创建
  Route<T> route<T>({required WidgetBuilder builder}) {
    return MaterialPageRoute(builder: (context) {
      Widget result = builder.call(context);
      return result;
    });
  }


  Widget _errorRouteWidget(String? routeName) {
    return Container(
      color: Colors.white,
      child: Center(
        child: Text("Route for ${routeName ?? "null"} is not defined"),
      ),
    );
  }
}