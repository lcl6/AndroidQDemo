import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import '../app_config.dart';

///横线
class Line extends StatelessWidget {
  final EdgeInsetsGeometry? margin;
  final Color? color;
  final double height;
  final double? width;

  const Line({Key? key, this.margin, this.color, this.height = 0.5, this.width})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: width ?? double.infinity,
      margin: margin ?? EdgeInsets.all(0),
      height: height,
      color: color == null ? AppColor.LineColor : color,
    );
  }
}

///高度为1灰线
class GrayLineW1 extends StatelessWidget {
  const GrayLineW1({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Line(
      height: 0.5,
      color: AppColor.LineColor,//AppColor.GrayColor9FB,
    );
  }
}

///高度为15灰线
class GrayLineW15 extends StatelessWidget {
  const GrayLineW15({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Line(
      height: 15.h,
      color: AppColor.GrayColor9FB,
    );
  }
}


///横线：背景白色，向左距离15
class LineBg extends StatelessWidget{
  final EdgeInsetsGeometry? margin;
  final Color? color;
  final Color? bgColor;
  final double height;
  final double? width;

  const LineBg({Key? key, this.margin = const EdgeInsets.only(left: 15),this.bgColor, this.color, this.height = 0.5, this.width})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: bgColor?? Colors.white,
      width: width ?? double.infinity,
      child: Container(
        margin: margin,
        height: height,
        color: color == null ? AppColor.LineColor : color,//Color(0xffdfe3e5)
      ),
    );
  }
}

///竖线
class HeightLine extends StatelessWidget {
  final EdgeInsetsGeometry? margin;
  final double width;
  final double? height;
  final Color? color;

  const HeightLine({Key? key, this.margin, this.color, this.width = 0.5, this.height})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: width,
      margin: margin ?? EdgeInsets.all(0),
      height: height ?? double.infinity,
      color: color ?? Color(0xffdfe3e5),
    );
  }
}

///top为true，是从上到下的渐变线，false是从下到上的渐变色
class GradientLine extends StatelessWidget {
  final bool top;

  const GradientLine({Key? key, this.top = true}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 10,
      decoration: BoxDecoration(
        gradient: top
            //阴影从上top到下bottom，颜色从深11000000到浅
            ? LinearGradient(
                colors: [Color(0x11000000), Color(0x00000000)],
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter)
            : LinearGradient(
                colors: [Color(0x00000000), Color(0x11000000)],
                begin: Alignment.bottomCenter,
                end: Alignment.topCenter),
      ),
    );
  }
}
