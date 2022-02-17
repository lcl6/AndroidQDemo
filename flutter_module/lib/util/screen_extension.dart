import 'package:flutter_module/util/screen_adapter.dart';

extension SizeExtension on int {
  double get rpx => Adapt.setRpx(this.toDouble());
  double get px => Adapt.setPx(this.toDouble());
}