package com.example.androidqkt.bean

/**
 *Created by liancl on 2020/11/11 0011.
 */

class Dog : Animal() {
    override  var name : String="狗"
    override fun toString(): String {
        return "Dog(name='$name')"
    }

}