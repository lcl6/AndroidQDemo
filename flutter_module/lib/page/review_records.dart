import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_html/flutter_html.dart';

import 'app_config.dart';
import 'custom/custom_page.dart';

class ReviewRecords extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeLeft, //全屏时旋转方向，左边
    ]);
    return ReviewRecordsState();
  }
}

class ReviewRecordsState extends State<StatefulWidget> {
  @override
  Widget build(BuildContext context) {
    return

      Scaffold(body:   Container(
        child: Row(
          children: [
            Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding : EdgeInsets.all(10.w),
                      child:Text(
                        "初稿",
                        style: TextStyle(color: AppColor.defaultColor),
                      ),),
                    Expanded(
                      child: Scrollbar(
                        child: SingleChildScrollView(
                          child: Html(
                            data:
                            '<SPAN TITLE=\"i=0\">公      证      书<br>（2021）闽厦云证字第227号<br> <br>申请人：张静，女，1988年7月29日出生，公民身份号码：350721198807294924。</SPAN><INS STYLE=\"background:#E6FFE6;\" TITLE=\"i=86\">1321</INS><SPAN TITLE=\"i=90\"><br>公证事项：声明<br>申请人于2021年12月23日登入本处在线公证平台“公证云”（下称“公证云”）的申办入口，向本处申请办理商标转让声明公证。<br>本公证员于2021年12月23日登入本处“公证云”业务受理后台，对申请人为申办上述事项而提交的相关数据文件及证据材料，进行校验、察看，确认：<br>1、申请人于2020年6月9日通过了“公证云”的实名认证，获得了本处签发的属其专有的电子签名认证证书（序列号：71282D77DCF2CD4631CDECBE7666B796）。<br>2、申请人于2021年12月23日在线阅读并确认了本处办理声明公证的相关《告知》，自助完成了其商标转让声明内容的确认，“公证云”根据申请人确认的内容，自动生成了《声明书》，申请人进行了在线签署，签署日期为2021年12月23日。<br>经本公证员核验，上述《声明书》上申请人的签名所记载的电子签名认证证书与申请人专属的电子签名认证证书一致，电子签名及所签署的《声明书》未有改动；在提交申办时，申请人的账号核验信息未有异常。<br>兹证明：<br>1、申请人张静于2021年12月23日在本处“公证云”平台，使用电子签名方式签署了《声明书》，该《声明书》电子文档现存于本处，《声明书》（电子文档）上申请人的电子签名符合《中华人民共和国电子签名法》第十三条的规定，为可靠的电子签名； <br>2、申请人张静的声明行为符合《中华人民共和国民法典》第一百四十三条的规定；<br>3、前面的《声明书》系申请人张静使用电子签名签署的《声明书》的打印件，二者文本内容相符。<br> <br>中华人民共和国福建省厦门市云尚公证处<br>查验电话：0592-5839596（直线），0592-5889990（总机）<br>[footnoteRef:0][footnoteRef:0][footnoteRef:0]<br>公证员<br>2021年12月23日<br></SPAN>',
                          ),
                        ),
                      ),
                    )
                  ],
                )),
            Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      padding : EdgeInsets.all(10.w),
                      child:Text(
                        "校对稿",
                        style: TextStyle(color: AppColor.defaultColor),
                      ),),
                    Expanded(
                      child: Scrollbar(
                        child: SingleChildScrollView(
                          child: Html(
                            data:
                            '<SPAN TITLE=\"i=0\">公      证      书<br>（2021）闽厦云证字第227号<br> <br>申请人：张静，女，1988年7月29日出生，公民身份号码：350721198807294924。</SPAN><INS STYLE=\"background:#E6FFE6;\" TITLE=\"i=86\">1321</INS><SPAN TITLE=\"i=90\"><br>公证事项：声明<br>申请人于2021年12月23日登入本处在线公证平台“公证云”（下称“公证云”）的申办入口，向本处申请办理商标转让声明公证。<br>本公证员于2021年12月23日登入本处“公证云”业务受理后台，对申请人为申办上述事项而提交的相关数据文件及证据材料，进行校验、察看，确认：<br>1、申请人于2020年6月9日通过了“公证云”的实名认证，获得了本处签发的属其专有的电子签名认证证书（序列号：71282D77DCF2CD4631CDECBE7666B796）。<br>2、申请人于2021年12月23日在线阅读并确认了本处办理声明公证的相关《告知》，自助完成了其商标转让声明内容的确认，“公证云”根据申请人确认的内容，自动生成了《声明书》，申请人进行了在线签署，签署日期为2021年12月23日。<br>经本公证员核验，上述《声明书》上申请人的签名所记载的电子签名认证证书与申请人专属的电子签名认证证书一致，电子签名及所签署的《声明书》未有改动；在提交申办时，申请人的账号核验信息未有异常。<br>兹证明：<br>1、申请人张静于2021年12月23日在本处“公证云”平台，使用电子签名方式签署了《声明书》，该《声明书》电子文档现存于本处，《声明书》（电子文档）上申请人的电子签名符合《中华人民共和国电子签名法》第十三条的规定，为可靠的电子签名； <br>2、申请人张静的声明行为符合《中华人民共和国民法典》第一百四十三条的规定；<br>3、前面的《声明书》系申请人张静使用电子签名签署的《声明书》的打印件，二者文本内容相符。<br> <br>中华人民共和国福建省厦门市云尚公证处<br>查验电话：0592-5839596（直线），0592-5889990（总机）<br>[footnoteRef:0][footnoteRef:0][footnoteRef:0]<br>公证员<br>2021年12月23日<br></SPAN>',
                          ),
                        ),
                      ),
                    ),
                  ],
                )),
          ],
        ),
      ) ,)
   ;
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitDown, //全屏时旋转方向，左边
    ]);
  }
}
