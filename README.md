###添加

####笔记整理 
##### 换肤  1.标记换肤控件   2.重写 InflaterLayout d的oncreatView  找到属性 替换   
            qes1:  ac何时调用调用layutInflaterFactory 的oncreatview    rep:ac的 oncreatview   ...--->layoutinflater.inflate 
            qes2:  怎么做到无需重启ac            rep  自定义属性 替换 更新
            qes3: 清单enable 作用  标记
##### 事件分发
            1.  https://img-blog.csdnimg.cn/2019012400325845.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dlZHVvXzgz,size_16,color_FFFFFF,t_70          
            实例：1.触摸非键盘部分收起键盘   2.触摸Scrollview  没有回调 ontouchEvent
   
   
##### view 测量
            1. 父控件  1.Exactly  ---->  子控件 {  
                                                    1. 有确切值 ----->   Exactly
                                                    2.  match ----->   Exactly
                                                    3. wrap_content----->  Al_MOST
                                                    4. unspecified----->  unspecified
                                                 } 
                                                 
                                                 
            1. 父控件  1.At_MOst  ---->  子控件 {  
                                                    1. 有确切值 ----->   Exactly
                                                    2.  match ----->   Al_MOST
                                                    3. wrap_content----->  Al_MOST
                                                    4. unspecified----->  unspecified
                                                }                                      
                                                
            1. 父控件  1.unspecified  ---->  子控件 {  
                                                        1. 有确切值 ----->   Exactly
                                                        2.  match ----->   unspecified
                                                        3. wrap_content----->  unspecified
                                                        4. unspecified----->  unspecified
                                                     }
                                                     
##### view bitmap 压缩   https://blog.csdn.net/weixin_30763455/article/details/96181152
            
          1.质量压缩  bm.compress   改变图片的位深 宽高不变 对png 无效 
                 1.1、位深度指的是存储每个像素所用的位数，主要用于存储
                 1.2、色深指的是每一个像素点用多少bit存储颜色，属于图片自身的一种属性
          2.采样率压缩  option.insampleSize =n 宽高变为 1/n
          3.缩放压缩   matrix 缩放尺寸  同上       
        I 有损压缩 ： 保持颜色的逐渐变化，删除图像中颜色的突然变化 (JPEG  )               
        II 无损压缩 ： 相同的颜色信息只需保存一次。 无损压缩的方法通过删除一些重复数据，也能在位图持久化存储的过程中减少要在磁盘上保存的图片大小（.png ）
#####   插件化
          1.将整个app拆分成很多个模块，每个模块都是一个独立的apk 打包的时候 将宿主和插件apk分开打包 插件通过动态下发到宿主apk
            1.1 利用一个代理ac 承载所有要跳转的ac  将要跳转的ac生命周期和代理ac绑定
               1.1.1 （跳转插件阶段） 解析出插件的第一个ac 传入代理ac构造  绑定上下文  绘制  
               1.1.2 （跳转其他页阶段）代理ac 得到类名 构造  传入上下文 绑定  绘制    
            1.2 广播  
                  
        组件化（tinker）
          1.将整个app拆分成很多个模块，每个模块都是一个独立组件（moudle）开发中 这些组件可以相互依赖或者独立调试，发布的时候将组件组成一个统一的apk 
        热修复
          1.基线包  gradlew assemble  、gradlew assembleDebug、gradlew assembleRelease     
          2.在android  data 包名  files   patch  放修复包  
          3.加载修复包后会重启 建议app一开始从服务端下载
          4.桌面图标不支持修改
          5.只支持 armv7  和arm 的so 更新  v8 不支持  所以so升级最好发版本包升级
          
        增量更新()
           增量更新是通过对比新旧两个版本的APK生成的字节码文件和生成APK过程的其他文件进行对比，生成更新后的差分包，然后将差分包合并到旧版本中去，生成新版本的APK。
           
####git 部分指令
    <--删除远程端文件夹--->
    //查看确认改文件夹
    git rm -r  -n --cached target  删除target文件夹
    //删除
    git rm -r --cached target  删除target文件夹

    git rm -r -n --cached android/tinker/tinker-android-anno-support/build
    git rm -r  --cached android/tinker/tinker-android-anno-support/build
    <--删除远程端文件夹--->

### --dart-define=debug=false --dart-define=initRoute=home