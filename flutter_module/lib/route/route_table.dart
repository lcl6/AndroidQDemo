import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/page/home.dart';
import 'package:flutter_module/page/notify_list_by_key.dart';
import 'package:flutter_module/route/route_info.dart';

class RouteTable {
  static RouteTable? _instance;
  final Map<String, RouterInfo> routerMap = {};

  final List<RouterInfo> routers = [
    RouterInfo(
        path: "/",
        builder: (context, arguments) => HomePage()),
    RouterInfo(
        path: "home",
        builder: (context, arguments) => HomePage()),
    RouterInfo(path: "globelkey", builder: (context, arguments) => GlobelKeyPage()),
  ];

  RouteTable._internal() {
    routers.forEach((e) {
      if (!routerMap.containsKey(e.path)) {
        routerMap[e.path] = e;
      }
    });
  }

  factory RouteTable() {
    RouteTable? router = _instance;
    if (router != null) {
      return router;
    }
    router = RouteTable._internal();
    _instance = router;
    return router;
  }

  RouterInfo? getRouterInfo(name) => routerMap[name];
}
