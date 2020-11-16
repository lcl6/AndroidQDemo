package com.example.androidqdemo;

import android.util.Log;

import com.example.androidqkt.bean.Animal;
import com.example.androidqkt.bean.Dog;

import org.junit.Test;

import java.io.Console;
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
        List<? extends Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        Dog dog = new Dog();
        dogs.add(dog);
        //? extends Animal  可以看成？ 不能添加子类
//        animals.add(dog);
        animals = dogs;

        //取出的值确定
//        Animal animal = animals.get(0);
        System.out.println(animals.toString());

       /*   下界通配符 < ? super E>   在类型参数中使用 super 表示这个泛型中的参数必须是 E 或者 E 的父类。*/
        List<? super Animal> animals2 = new ArrayList<>();
        //可以添加子类  ? super Animal  可以看成 Animal  可以添加子类
        animals2.add(new Dog());
        //取出的值不确定
//        Object object = animals2.get(0);
        System.out.println(animals2.toString());
    }

}