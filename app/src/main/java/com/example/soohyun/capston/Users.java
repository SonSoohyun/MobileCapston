package com.example.soohyun.capston;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaeBeome on 2017. 5. 25..
 */

public class Users {
    String name, password;

    List<String> g_list;
    List<Record> r_list;

    Users(String name, String password){
        this.name = name;
        this.password = password;
        g_list = new ArrayList<String>();
        r_list = new ArrayList<Record>();
    }

    Users() {

    }

    public String getname(){
        return name;
    }

    public String getpwd(){
        return password;
    }

    public void addgroup(String group){
        g_list.add(group);
    }


    public void removegroup(String group){
        g_list.remove(group);
    }

    public void addrecord(Record record){
        r_list.add(record);
    }

    public void removerecord(Record record){
        r_list.remove(record);
    }

}
