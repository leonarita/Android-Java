package com.example.app24;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application
{
    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<Person>();
        people.add(new Person("Chuck Norris", "0928039482039"));
        people.add(new Person("John Rambo", "092803980923"));
        people.add(new Person("Nelson Mandela", "0928340298340"));
    }
}