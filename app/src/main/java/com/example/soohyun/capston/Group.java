package com.example.soohyun.capston;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JaeBeome on 2017. 5. 25..
 */

public class Group {
    String name;
    List<Users> u_list;

    Group(){

    }

    Group(String name){
        this.name = name;
        u_list = new ArrayList<Users>();
    }

    public void adduser(Users user){
        u_list.add(user);
    }

    public void removeuser(Users user){
        u_list.remove(user);
    }
}
