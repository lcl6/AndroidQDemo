import 'package:flutter/cupertino.dart';

class Constant {
  static Constant get instance => _instance;
  static Constant _instance = Constant._();
  factory Constant() => _instance;
  Constant._();

  String name = 'Module';
  String versionName = '1.0.0';

  final GlobalKey<NavigatorState> navigatorKey = GlobalKey();

  NavigatorState get currentState => navigatorKey.currentState!;
}