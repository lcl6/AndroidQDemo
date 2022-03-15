package com.example.androidqkt

import com.example.androidqkt.bean.Animal
import com.example.androidqkt.bean.Dog
import org.junit.Test


/**
 *Created by liancl on 2020/11/11 0011.
 */

public class Test {
    @Test
    fun aesTest(){
//        var animals: List<Animal> = ArrayList()
//        val dogs = ArrayList<Dog>()
//        animals = dogs


        val list = listOf("1223", "2222", "333", "444", "555")
        val flatMap = list.flatMap {
            listOf(it.plus("----test"))
        }

        print("返回---$flatMap");


    }

}