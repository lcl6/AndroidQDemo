import 'dart:ui';

import 'app.dart';
import 'env.dart';

@pragma('vm:entry-point')
void main() {
  print("env-> debug:${Env.debug} \n"
      "initRoute:${Env.initRoute}");
  // run(Env.debug, Env.initRoute);
  // print("flutter---main_release");
  runMain(Env.debug,Env.initRoute);
}

@pragma('vm:entry-point')
void main_debug() {
  print("flutter---main_debug");
  runMain(true, window.defaultRouteName);
}

@pragma('vm:entry-point')
void multi_main() {
  print("flutter---main_release");
  //run(false, "/");
  runMain(false, '/');
}

@pragma('vm:entry-point')
void multi_main_debug() {
  print("flutter---main_debug");
  //run(true, "/");
  runMain(true, '/');
}

