package com.example.androidqdemo.sf.day01;

import org.junit.Test;

/**
 * Created by  on 2021/9/3.
 */

public class Test3 {

    /**
     * 替换空格
     */
    @Test
    public void test() {

        String s = "We are happy.";
//        String replace = s.replace(" ", "%20");
        String replace =  replaceSpace(s);
        System.out.println(replace);


    }

    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;
    }


}
