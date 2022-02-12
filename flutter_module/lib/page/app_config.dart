
import 'dart:io' as p show Platform;
import 'dart:ui';
import 'package:flutter/cupertino.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class Constant {

  static Constant get instance => _instance;
  static Constant _instance = Constant._();
  factory Constant() => _instance;

  Constant._();

  String get API => _debug ? "" : "";
  ///服务端后期会合并，临时使用。
  String get DownLoadAPI => _debug ?
                  "" :
                  "";

  String name = 'Module';
  String versionCode = '100';
  bool _debug = false;
  // ///华为的平行视窗
  // bool isMagicWindow = false;
  String defaultRouteName = '';
  String token = '';
  String orgId  = '';
  String applicationId  = '';
  ///中台的userid
  String uid = '';
  String notaryId = '';
  String userId = '';
  String userName = '';
  String realName = '';
  List<String> roleCodes = [];

  set debug(bool b) {
    _debug = b;
    LOG_PRINT = b;
  }

  ///是否是测试环境
  get ENV_TEST => _debug;

  String proxyAddress = '';

  ///是否打印日志
  bool LOG_PRINT = false;

  final GlobalKey<NavigatorState> navigatorKey = GlobalKey();

  ///公证员、助理等角色
  bool hasRole(String role){
    for(var item in roleCodes) {
      if(role == item)return true;
    }
    return false;
  }

  NavigatorState get currentState => navigatorKey.currentState!;
}

///统一图片资源的取用路径
String mipmap(String pngName) => 'assets/images/${pngName}';

final double fontSize36 = ScreenUtil().setSp(36);
final double fontSize32 = ScreenUtil().setSp(32);
final double fontSize30 = ScreenUtil().setSp(30);
final double fontSize28 = ScreenUtil().setSp(28);
final double fontSize26 = ScreenUtil().setSp(26);
final double fontSize24 = ScreenUtil().setSp(24);
final double fontSize23 = ScreenUtil().setSp(23);
final double fontSize20 = ScreenUtil().setSp(20);
final double fontSize18 = ScreenUtil().setSp(18);

class AppString {
  AppString._();

  static const String company_copyright = '法信公证云（厦门）科技有限公司';
  static const String agree_user = '《用户服务协议》';
  static const String agree_privacy = '《隐私声明》';

  // ui
  static const String loading = '加载中...';
  static const String load_more = '上拉加载更多';
  static const String net_request_fail = '网络请求失败';
  static const String reload_again = '重新加载';
  static const String more_text = '更多';
  static const String less_text = '收起';
  static const String empty_data = '暂无数据';

  static const String bookkeeping =  "记账是指付款方定期结算费用的支付方式，包含事先预付或事后结算。";
  static const String more_cancel_order =  "确定要作废卷宗？卷宗作废后需要到web端恢复。";
  static const String charge_tip =  "确定去缴费吗？支付二维码15分钟内有效，在此期间将不可编辑此订单的费用";
  static chargeTimeTips(String time)=> "二维码将在 $time 失效，二维码有效期间不可编辑该订单费用";

  //文案
  static const String review_attention =
      "（一）申请公证的事项及其文书真实、合法；\n\n"
      "（二）公证事项的证明材料真实、合法、充分；\n\n"
      "（三）办证程序符合《公证法》《公证程序规则》及有关办证规则的规定；\n\n"
      "（四）拟出具的公证书内容、表述和格式符合相关规定；\n\n"
      "（五）收取公证费符合收费标准规定；\n\n"
      "（六）重大、复杂的公证事项，已提交公证机构集体讨论。\n\n"
      "前款第（五）项应当包括下列内容：\n\n"
      "（一）公证事项及相应的收费标准、计算办法；\n\n"
      "（二）因法律援助或者其他条件减免的申请与审核；\n\n"
      "（三）公证预收费及公证费用核定；\n\n"
      "（四）公证收费发票。";



  ///新增受理
  static const List<String> sex_type_data = ["男","女"];
}

class AppColor {
  AppColor._();

  static const hitTextColor = Color(0xFFcccccc);
  static const hitColor = Color(0xFFbbbbbb);
  static const textColor33 = Color(0xFF333333);
  static const defaultColor = Color(0xff2A81FF);
  static const greenColor = Color(0xff01D171);
  static const searchHintColor = Color(0xff828282);
  static const textColor = Color(0xff404040);
  static const Color_feac39 = Color(0xffFEAC39);
  static const Color_ABB1BC = Color(0xffABB1BC);
  static const ColorRed = Color(0xffFF0000);
  static const ColorRedF9 = Color(0xfff94f4f);
  static const ColorYellow = Color(0xffFFB144);

  static const BlackColor21 = Color(0xFF212121);
  static const BlackColor33 = Color(0xFF333333);
  static const BlackColor38 = Color(0xFF383232);

  static const GrayColor71 = Color(0xFF717171);
  static const GrayColor9FB = Color(0xFFF7F9FB);
  static const GrayColorF3 = Color(0xFFF3F3F3);
  static const GrayColor98 = Color(0xFF989898);
  static const GrayColor9b = Color(0xFF9b9b9b);
  static const GrayColor50 = Color(0xFF505050);
  static const GrayColor8F = Color(0xFF8f8f8f);
  static const GrayColorC7 = Color(0xFFc7c7c7);
  static const GrayColorCD5 = Color(0xFFc5cad5);
  static const GrayColor7B = Color(0xFF7B7B7B);

  static const GrayColorE4 = Color(0xFFe4e4e4);
  static const GrayColorE5 = Color(0xFFE5E5E5);
  static const GrayColorE3 = Color(0xFFE3E3E3);
  static const GrayColorE9 = Color(0xFFE9E9E9);
  static const GrayColorBB = Color(0xFFBBBBBB);
  static const GrayColorDA = Color(0xFFDADADA);
  static const GrayColorDB = Color(0xFFDBDBDB);
  static const GrayColorCB = Color(0xFFCBCBCB);
  static const GrayColorFBC = Color(0xFFFBFBFC);


  static const GrayColor60 = Color(0xFF606060);
  static const GrayColorF105 = Color(0xFFF1F0F5);
  static const GrayColorF5 = Color(0xFFf5f5f5);
  static const GrayColorF9 = Color(0xFFF9F9F9);
  static const GrayColorD4 = Color(0xFFD4D4D4);
  static const GrayColor8D = Color(0xFF8D8D8D);
  static const GrayColorAEB8C6 = Color(0xFFAEB8C6);
  static const graycolorf7f9fb = Color(0xFFF7F9FB);

  //分割线，统一使用颜色
  static const LineColor = GrayColorE9;
}


class AppConfig{

  ///结案方式: 正常结案0,终止公证1,不予办理2,作废-1,	不予出具执行证书 3
  static const String closeCaseType_normal = "0";
  static const String closeCaseType_terminal = "1";
  static const String closeCaseType_stop = "2";
  static const String closeCaseType_cancel = "-1";
  static const String closeCaseType_no_doc = "3";

  ///订单流程状态：受理状态
  static const int flowStatus_receive = 1;

  ///出证审查状态：待审查
  static const int make_notary_check_review = 1;

  ///国内民事
  static const int notary_type_china = 1;
  ///涉外民事
  static const int notary_type_foreign_civil = 3;
  ///涉外经济
  static const int notary_type_foreign_economy = 4;
  ///涉台
  static const int notary_type_taiwan = 7;
  ///涉港
  static const int notary_type_hongKong = 5;
  ///涉澳
  static const int notary_type_macao = 6;


  ///档案类别 档案类型,默认“公证管家-纸质档案”
  /// static const String archives_category_paper = "NOTARY_PAPER";
  ///  ///档案类别 档案类型,默认“label=公证管家-电子档案附纸质材料”
  static const String archives_category_paper = "NOTARY_ELECTRONICS_MATERIAL";

  ///涉外才出现,默认旅游，可修改
  static const String purpose_travel = "旅游";

  ///使用地 默认中国
  static const String use_place_china = "中国";

  ///紧急程度，普通0
  static const int urgent_type_normal = 0;
  ///受理方式，现场办理1
  static const int accept_type_now = 1;

  ///公证员： gzy
  static const String role_notary = "gzy";
  ///公证员助理： gzyzl
  static const String role_notary_assistant = "gzyzl";
  ///承办人： gzy,gzyzl
  static const String role_notary_sponsor = "gzy,gzyzl";

  ///新增受理-基本信息
  static const String key_base_info = "base_info";

  ///新增受理-基本信息-业务来源，APP对应的code为APP
  static const String order_source_app = "APP";
  ///业务来源 办证系统，app的业务来源 还是要是要BZ，而不是APP，服务端记录来源是app的订单需要另外新增字段做单独标记。
  static const String order_source_bz= "BZ";
  static const String order_source_bz_name= "办证3.0";

  ///卷宗保存时，是否有副本，无副本传1
  static const int business_type_main = 1;


  ///订单是类案，即订单号开头是LA
  static const String order_no_la = "LA";

  ///app文件存储路径 文件夹名称
  static const String app_file = "gzManager";
  ///app文件存储路径下 存储pdf的文件夹
  static const String app_file_pdf = "pdf";
  static const String app_file_file = "file";

  ///性别，男1，女2
  static const int sex_man = 1;
  static const int sex_women = 2;

  ///卷宗当事人，类型1个人2单位
  static const int notary_person_party = 1;
  static const int notary_person_company = 2;
  ///卷宗当事人，单位证件类型（传126）
  static const int notary_person_company_card_type = 126;
  ///证件类型为 公民身份证
  static const int cert_type_id_card = 111;
  ///证件类型为 国内护照/普通护照
  static const int cert_type_passport = 414;

  ///模板输入类型，1输入框，2文本域，3数字输入框，4日期选择框，5选择器，6单选器，7复选框，8图片，9时间选择器
  static const String input_type_input = "1";
  static const String input_type_input2 = "2";
  static const String input_type_num = "3";
  static const String input_type_date = "4";
  static const String input_type_pick = "5";
  static const String input_type_single = "6";
  static const String input_type_multi = "7";
  static const String input_type_picture = "8";
  static const String input_type_time = "9";

  ///查询事项模板传‘18’，其他模板 ‘0,2’
  static const String template_matter = "18";
  static const String template_other = "0,2";

  ///证据材料，文件类型：image’，pdf：‘pdf’，word：‘word’，语音：‘mp3’，视频：‘mp4’，其他：‘zip’
  static const String doc_type_image = "image";
  static const String doc_type_pdf = "pdf";
  static const String doc_type_word = "word";
  static const String doc_type_mp3 = "mp3";
  static const String doc_type_mp4 = "mp4";
  static const String doc_type_zip = "zip";

  ///上传文件的后缀：.flv,.mp3,.mp4,.jpeg,.pdf,.jpg,.bmp,.png,.wav,.flac,.flac,.avi,.rmvb,.webm,.mov,"M4A","awb",
  static const List<String> doc_type_list = [
    "flv","mp4","avi","mov","rmvb","webm",
    "jpeg","bmp","png","jpg",
    "mp3","wav","flac","M4A","awb",
    "pdf",
  ];
  static const List<String> doc_type_video = [
    "flv","mp4","avi","mov","rmvb","webm"
  ];
  static const List<String> doc_type_picture = [
    "jpeg","bmp","png","jpg"
  ];
  static const List<String> doc_type_audio = [
    "mp3","wav","flac","M4A","awb",
  ];

  static const String DEAL_NUM = "2";
}

class Platform {
   static bool isAndroid() {
     try {
       return p.Platform.isAndroid;
     } catch(e) {
       return false;
     }
   }
   static bool isIOS() {
     try {
       return p.Platform.isIOS;
     } catch(e) {
       return false;
     }
   }
   static bool isWeb() {
     try {
       return !p.Platform.isAndroid;
     } catch(e) {
       return true;
     }
   }

   ///是否是混合开发模式
   static bool isMixedNature() {
      return window.defaultRouteName != '/';
   }


}