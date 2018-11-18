package com.lcw.one.utils.excel.bean;

import com.lcw.one.util.annotation.ExcelField;

public class User4Excel {

    @ExcelField(title = "姓名", column = 0, nullable = false)
    private String name;

    @ExcelField(title = "性别", column = 1)
    private String gender;

    @ExcelField(title = "年龄", column = 2)
    private Integer age;

    public User4Excel() {
    }

    public User4Excel(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User4Excel{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
