package com.louie.study.dp.behavioral.mediator.example1;
import java.util.Date;

public class ChatRoom {
    public static void showMessage(User user, String message){
        System.out.println(new Date().toString()
            + " [" + user.getName() +"] : " + message);
    }
}
