package com.example.androidqdemo;

import android.util.Log;

import com.example.androidqkt.bean.Animal;
import com.example.androidqkt.bean.Dog;

import org.junit.Test;

import java.io.Console;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);

        /*上界通配符 < ? extends E>   在类型参数中使用 extends 表示这个泛型中的参数必须是 Animal 或者 Animal 的子类，*/
//        List<? extends Animal> animals = new ArrayList<>();
//        List<Dog> dogs = new ArrayList<>();
//        Dog dog = new Dog();
//        dogs.add(dog);
//        //? extends Animal  可以看成？ 不能添加子类
////        animals.add(dog);
//        animals = dogs;
//
//        //取出的值确定
////        Animal animal = animals.get(0);
//        System.out.println(animals.toString());
//
//       /*   下界通配符 < ? super E>   在类型参数中使用 super 表示这个泛型中的参数必须是 E 或者 E 的父类。*/
//        List<? super Animal> animals2 = new ArrayList<>();
//        //可以添加子类  ? super Animal  可以看成 Animal  可以添加子类
//        animals2.add(new Dog());
//        //取出的值不确定
////        Object object = animals2.get(0);
//        System.out.println(animals2.toString());

//
//        try {
//            Class<?> aClass = Class.forName("com.example.androidqdemo.Person");
//            Object o = aClass.newInstance();
//            //获取当前类的方法 包括public 和非public
//            Field nameField = aClass.getDeclaredField("name");
//            nameField.setAccessible(true);
//            String name = (String) nameField.get(o);
//            System.out.println("反射结果是："+name);
//
//            //-----------------获取public方法 包括父类的 -------------------------
//            Field nameA = aClass.getField("nameA");
//            nameA.setAccessible(true);
//            String aniNameA = (String) nameA.get(o);
//            System.out.println("反射结果aniNameA："+aniNameA);
//            //-----------------获取public方法 包括父类的 -------------------------
////            Field nameB = aClass.getField("nameB");
////            nameA.setAccessible(true);
////            String aninameB = (String) nameB.get(o);
////            System.out.println("反射结果aninameB："+aninameB);//反射报错
//
//            //-----------------获取public方法 包括父类的 -------------------------
//            Field abbField = aClass.getDeclaredField("abb");
//            abbField.setAccessible(true);
//            int abb = (int) abbField.get(o);
//
//            Field ageField = aClass.getDeclaredField("age");
//            ageField.setAccessible(true);
//
//            ageField.set(o,abb);
//            //参数类型
//            Method addAgMethod = aClass.getDeclaredMethod("addAge", int.class);
//            addAgMethod.setAccessible(true);
//            int addage = (int) addAgMethod.invoke(o,50);
//            System.out.println("addAge:"+addage);
//
//
//            //-----------------------创建一个注解  把他赋值给person 的------------------------------
//            Field[] declaredFields = aClass.getDeclaredFields();
//            for (Field declaredField : declaredFields) {
//                boolean annotationPresent = declaredField.isAnnotationPresent(MyAnotion.class);
//                if(annotationPresent){
//                    MyAnotion annotation = declaredField.getAnnotation(MyAnotion.class);
//                    String value = annotation.value();
//                    declaredField.setAccessible(true);
//                    declaredField.set(o,value);
//                    String adnnotion = (String) declaredField.get(o);
//                    System.out.println("adnnotion:"+adnnotion);
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("反射报错");
//            e.printStackTrace();
//        }


        /*--------算法测试--------------*/




    }

}