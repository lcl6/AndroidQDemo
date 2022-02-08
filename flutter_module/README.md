
## <!-- 代码直通车 -->
### 1.首页 FirstFragment
###     1.消息 MsgListActivity
###     2.扫一扫 OnlyScanActivity
###         1.扫码登录页 flutter PcLoginPagePad (https://uat-portal.fxnotary.com/login?redirect=%2Fhome)
####            1.OA签到状态  SignStatus
####            2.卷宗编码  acceptdetail
####            3.PC登录 pclogin
####            4.查看更多新闻  flutter newslist
####            5.通用受理 flutter  accept
###     1.通用受理 AcceptListPad
####        1.受理详情 AcceptDetailPad
#####           1.非接触受理 NoFaceDisposePad
#####           2.翻译信息  TranslateInfoPad
#####           3.基本信息  BaseInfoPad
#####           4当事人信息  ClientInfoPad
#####           5当事人信息  PartyInfo
#####           登记失信 RegisterDishonestyWidget
###		    拍照上传证据材料 UploadPictureProof
>>>						    当事人办证记录 ClientVerificationRecordList
>>>						    当事人证据材料 ClientEvidenceMaterialList
>>>					    当事人核验记录 ClientCertificateRecordList
>>>					公证事项  NotaryMattersPad
>>>					    借款合同、执行证书-->有必填项
>>>					证据材料	  EvidMaterialsPad
>>>					收费信息  CostInfoPad
>>>						去开票 ApplyInvoicePagePad
>>>					文书笔录 WritRecordPad
>>>					公证书"  NotarizationPad
>>>					    上报审批 v2.0.3 第一个界面（原来的出证审查）. MoreForReview  第二个界面（原来的上报审批）2.MoreForApprove 按钮移动到公证书界面
>>>					        逻辑 1.提交出证审查 (公证书没生成 无法提交) ---》2. 签发---》3.上报审批
>>>						        1.核稿人是 承办公证员 或者 承办人都会到上报审批的
>>>						        2.makeNotaryCheck==1&承办公证员==当前用户  ===》上报审批
>>>						        3.makeNotaryCheck==0  承办公证员（默认签发人） 承办人  公证员助理  =====》提交出证审查
>>>		                        4. makeNotaryCheck==3&&核稿人==当前用户  =====》提交签发
>>>		                       当前用户是拟稿人   承办人是签发人   核稿人是提交出证审查时选的核稿人
>>>					    编辑核稿 NotaritionEdit
>>>					    核稿记录 ReviewRecords
>>>					流程进度  FlowSchedulePad
>>>		2.新增受理 AddAcceptFlowPad
>>>				1.基本信息 AddBaseInfo
>>>						1.使用地 SelectList
>>>				2.当事人信息 PartyInfoPad
>>>				    新增受理-当事人 PartyPersonPad
>>>			3.公证事项 AddNotaryMatter
>>>					添加公证事项 NotarizationMatterMultiPage
>>>					公证事项编辑 EditNotaryMatter  --MatterVarEditWidget
>>>						选择模板 SelectedMatterTemplatePage
>>>			    4.证据材料 AddEvidMaterials
>>>				5.收费信息 AddChargeInfo
>>>				     新增编辑收费 ExpenseForAddPage
>>>				     开票界面 ApplyInvoicePage
>>>				     发票详情 ApplyInvoiceRecordDetail
>>>				6.公证书 AddNotaryDocPagePad
>>>				7.文书笔录 AddDocumentRecord
>>>		右上角    MorePopupMenuPad
>>>			不予办理  MoreForNoApprovePad
>>>				终止公证  MoreForTerminalPad
>>>				提交出证审查 MoreForSubmitReviewPad
>>>				出证审查   MoreForReviewPad
>>>				上报审批 MoreForApprove
>>>				卷宗备注 AcceptDetailRemark
>>>				卷宗文书 FileDocumentList
>>>	           公证审批  flutter approve
>>>		        减免审批 flutter  reduceapproval
>>>		        退费审批  flutter   refundapproval
>>>		        不予办理 noapproval
>>>		        终止申办 terminalapproval
>>>     业务办理 SecondFragment
>>>     工具 ToolFragment
>>>		1.身份证 AuthActivity TOOL_OCR_BANK_CARD IdcardUploadFragment
>>>			1.信息确认 InfoConfirmFragment
>>>			2.结果页 ResultFragment
>>>			识别 ScanActivity
>>>		1.身份证 其他证件 InfoConfirmFragment
>>>		2.临时身份证  IdCardTempInfoFragment
>>>			1.现场采集页 UserHeaderFragment
>>>			2.j结果页 ResultFragment
>>>		2.护照 TOOL_OCR_PASSPORT ImageUploadFragment
>>>			1.信息确认 InfoConfirmFragment
>>>    3.驾驶证 TOOL_OCR_DRIVER_LICENSE ImageUploadFragment
>>>    		识别  TakeIdentificationPhotoActivity
>>>    4.回乡证 TOOL_OCR_HOME_RETURN ImageUploadFragment
>>>    5.银行卡 TOOL_OCR_BANK_CARD  BankInfoFragment
>>>    		1.拍照填写 ScanPortraitActivity
>>>    6.核验记录 IdentifyListActivity
>>>    		1.核验记录详情 IdentifyDetailActivity
>>>    		2.核验搜索记录 IdentifySearchListActivity
>>>    	4.我的 MineFragment
>>>    	1.账号与安全 PersonalInfoActivity
>>>    	2.关于 AboutAppActivity
>>>    	3.设置 SettingsActivity2
>>>    公共组件
>>>    1.弹窗 AppTool
>>>    2.提示 ToastUtil
>>>    	基础地址 Constant

## <!-- 账号信息 -->
账号：
公证员：
zj0239
a@1111111

panruicao
FaXin@123456
助理：
hongpl
FaXin@123456

cqq
FaXin@123456
<!-- 账号信息 -->

## <!-- 接口文档地址 -->
    http://218.98.113.88:18300/web/#/56?page_id=782
<!-- 接口文档地址 -->

##  <!-- 管家后台地址 -->
    https://uat-portal.fxnotary.com/login?redirect=%2Fapp
<!-- 管家后台地址  -->
