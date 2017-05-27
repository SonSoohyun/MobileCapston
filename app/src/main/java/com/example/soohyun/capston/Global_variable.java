package com.example.soohyun.capston;

import android.app.Application;

/**
 * Created by JaeBeome on 2017. 5. 25..
 */

public class Global_variable extends Application {

    private Users current_usr;

    public void setGlobal_usr(Users usr){
        this.current_usr = usr;
    }

    public Users getGlobal_usr(){
        return current_usr;
    }

}
