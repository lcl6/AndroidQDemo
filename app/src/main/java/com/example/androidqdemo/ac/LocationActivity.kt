package com.example.androidqdemo.ac

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import butterknife.ButterKnife
import com.example.androidqdemo.R
import java.util.*


/**
 * 沉浸状态栏
 * Created by liancl on 2020/6/17 0017.
 */
class LocationActivity : AppCompatActivity() {


    var  locationManager: LocationManager? = null;
    var  locationProvider : String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        ButterKnife.bind(this)
        getLocation();
    }

    private fun getLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION) , 301);
            } else {
                //1.获取位置管理器
                var locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                //2.获取位置提供器，GPS或是NetWork
                //2.获取位置提供器，GPS或是NetWork
                val providers = locationManager.getProviders(true)
                when {
                    providers.contains(LocationManager.GPS_PROVIDER) -> {
                        //如果是GPS
                        locationProvider = LocationManager.GPS_PROVIDER;
                        Log.v("TAG", "定位方式GPS");
                    }
                    providers.contains(LocationManager.NETWORK_PROVIDER) -> {
                        //如果是Network
                        locationProvider = LocationManager.NETWORK_PROVIDER;
                        Log.v("TAG", "定位方式Network");
                    }
                    else -> {
                        Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
                        return
                    }
                }

////                //3.获取上次的位置，一般第一次运行，此值为null
//                var location :Location= locationManager.getLastKnownLocation(locationProvider);
//                if (location!=null){
////                    Toast.makeText(this, location.longitude + ""+
////                            location.latitude + "",Toast.LENGTH_SHORT).show();
//                    Log.v("TAG", "获取上次的位置-经纬度："+location.longitude +"   "+location.latitude);
//                    getAddress(location);
//
//                }else{
//                    //监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
//                    locationManager.requestLocationUpdates(locationProvider, 3000, 1.0F,locationListener);
//                }

                locationManager.requestLocationUpdates(locationProvider, 1000, 0f,object :LocationListener{
                    override fun onLocationChanged(location: Location?) {

                        if (location != null) {
                            //如果位置发生变化，重新显示地理位置经纬度
//                            Toast.makeText(TestLocationActivity.this, location.getLongitude() + " " +
//                                    location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                            Log.v("TAG", "监视地理位置变化-经纬度："+location.longitude +"   "+location.latitude);

                            getAddress(location);
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String?) {
                    }

                    override fun onProviderDisabled(provider: String?) {
                    }

                });
            }
        } else {
            //1.获取位置管理器
            var locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            //2.获取位置提供器，GPS或是NetWork
            //2.获取位置提供器，GPS或是NetWork
            val providers = locationManager.getProviders(true)
            when {
                providers.contains(LocationManager.GPS_PROVIDER) -> {
                    //如果是GPS
                    locationProvider = LocationManager.GPS_PROVIDER;
                    Log.v("TAG", "定位方式GPS");
                }
                providers.contains(LocationManager.NETWORK_PROVIDER) -> {
                    //如果是Network
                    locationProvider = LocationManager.NETWORK_PROVIDER;
                    Log.v("TAG", "定位方式Network");
                }
                else -> {
                    Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
                    return
                }
            }
//             var  location :Location= locationManager.getLastKnownLocation(locationProvider);
//            if (location!=null){
////                Toast.makeText(this, (location.getLongitude() + " " +
////                        location.getLatitude() + ""), Toast.LENGTH_SHORT).show();
//                Log.v("TAG", "获取上次的位置-经纬度："+location.longitude +"   "+location.latitude);
//                getAddress(location);
//
//            }else{
//                //监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
//                locationManager.requestLocationUpdates(locationProvider, 3000, 1f,locationListener);
//            }


            locationManager.requestLocationUpdates(locationProvider, 1000, 0f,object :LocationListener{
                override fun onLocationChanged(location: Location?) {

                    if (location != null) {
                        //如果位置发生变化，重新显示地理位置经纬度
//                            Toast.makeText(TestLocationActivity.this, location.getLongitude() + " " +
//                                    location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                        Log.v("TAG", "监视地理位置变化-经纬度："+location.getLongitude()+"   "+location.getLatitude());

                        getAddress(location);
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

                override fun onProviderEnabled(provider: String?) {
                }

                override fun onProviderDisabled(provider: String?) {
                }

            });
        }



    }


    object locationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (location != null) {
                //如果位置发生变化，重新显示地理位置经纬度
//                            Toast.makeText(TestLocationActivity.this, location.getLongitude() + " " +
//                                    location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                Log.v("TAG", "监视地理位置变化-经纬度："+location.getLongitude()+"   "+location.getLatitude());
            }
        }


        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {

        }

    }

    private fun getAddress(location: Location): List<Address>? {

       var result : List<Address>? = null;
        try {
            if (location != null) {
                var gc:Geocoder =  Geocoder(this, Locale.getDefault());
                result = gc.getFromLocation(location.latitude,
                        location.longitude, 1);
                Toast.makeText(this, "获取地址信息：$result", Toast.LENGTH_LONG).show();
                Log.v("TAG", "获取地址信息：$result");
            }
        } catch ( e:Exception) {
            e.printStackTrace();
        }
        return result;
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager?.removeUpdates(locationListener);
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==301){
            if(grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "申请权限", Toast.LENGTH_LONG).show();
                val value: Any = try {
                    val providers = locationManager?.getProviders(true)
                    if (providers != null) {
                        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                            //如果是Network
                            locationProvider = LocationManager.NETWORK_PROVIDER;

                        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                            //如果是GPS
                            locationProvider = LocationManager.GPS_PROVIDER;
                        }
                    }
                    val location = locationManager?.getLastKnownLocation(locationProvider)
                    if (location != null) {
//                        Toast.makeText(this, location.longitude + " " +
//                                location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                        Log.v("TAG", "获取上次的位置-经纬度：" + location.longitude + "   " + location.latitude);
                    } else {
                        // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
                        locationManager?.requestLocationUpdates(locationProvider, 0, 0f, locationListener)!!;
                    }

                } catch ( e:SecurityException) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "缺少权限", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, LocationActivity::class.java)
            context.startActivity(starter)
        }
    }
}