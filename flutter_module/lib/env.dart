/// Dart环境参数
/// 环境参数需要通过 --dart-define 进行生效
/// 对于单独启动flutter 项目，可以通过命令行进行配置
/// 运行flutter
///```
/// flutter run --dart-define=debug=false --dart-define=initRoute=test
///```
/// 打包 apk
///```
/// flutter build apk --dart-define=debug=false --dart-define=initRoute=test
///```
/// 打包 aar
///```
/// flutter build aar --dart-define=debug=false --dart-define=initRoute=test
///```
/// 对于android studio 运行，我们在运行的地方配置
/// 配置 Run/Debug Configurations
/// 例如
/// Dart entrypoint
/// F:\workspace\gz_manager_main\module\lib\main.dart
///
/// Additional run args:
/// --dart-define=debug=false --dart-define=initRoute=test
/// 经过测试 从flutter 2.8.1开始 如果是flutter 以module的形式导入到android 项目，在打release的时候@pragma('vm:entry-point')不会被保留，仅仅留下main,分析是
/// 代码压缩，把没有被引用的方法给优化掉了。暂时没有找到避免被优化的版本。目前临时的解决方案是在android module目录下配置gradle.properties
///```
/// # 将key=value 的形式转为Base64，多个使用逗号拼接  其中ZGVidWc9dHJ1ZQ==代表  debug=false aW5pdFJvdXRlPXRlc3Q= 代表 initRoute=test
/// dart-defines=ZGVidWc9dHJ1ZQ==,aW5pdFJvdXRlPXRlc3Q=
///```
class Env {
  /// 是否是debug环境
  static const bool debug = bool.fromEnvironment("debug", defaultValue: false);

  /// 初始化路由，可以在[RouteTable] 查看可支持的路由
  static const String initRoute =
      String.fromEnvironment("initRoute", defaultValue: "/");

  /// 是否加载用户信息
  static const bool loadUser =
      bool.fromEnvironment("loadUser", defaultValue: false);
}
