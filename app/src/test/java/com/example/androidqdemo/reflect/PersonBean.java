package com.example.androidqdemo.reflect;

/**
 * Created by  on 2022/2/10.
 */

public class PersonBean {

    private String name;
    public final String aotSharedLibraryName;


    public PersonBean(String name, String aotSharedLibraryName) {
        this.name = name;
        this.aotSharedLibraryName = aotSharedLibraryName;
    }


 private String deal(int a,int b){
     return a+b+10+"";
 }

    @Override
    public String toString() {
        return "PersonBean{" +
                "name='" + name + '\'' +
                ", aotSharedLibraryName='" + aotSharedLibraryName + '\'' +
                '}';
    }
}
