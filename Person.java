package edu;

public class Person {
    private String name;
    private int age;
    private final Gender gender;

    public enum Gender{ //a class with constant feilds
        MALE, FEMALE;
    }
    
    Person(String name,int age,Gender gender){
        this.name=name;
        this.age=age;
        this.gender=gender;
    }

    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
    }

    public Gender getGender() {
        return gender;
    }
    public String toString(){
        return name+",\s"+age+",\s"+gender;
    }
}
