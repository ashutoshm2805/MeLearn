package me.lr;

/**
 * Created by Administrator on 11/22/2017.
 */

public class MeModel {

    public String name;
    public String age;


    public MeModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
