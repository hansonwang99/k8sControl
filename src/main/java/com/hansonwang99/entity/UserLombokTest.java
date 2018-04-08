package com.hansonwang99.entity;

/**
 * Created by Administrator on 2018/4/8.
 */
public class UserLombokTest {

    public static void main( String[] args ) {
        UserLombok userLombok = new UserLombok("hansonwang99");
        userLombok.setAge(18);
        String[] array = new String[]{"apple","juice"};
        userLombok.setTags( array );
        userLombok.setScore( 99.0 );
        System.out.println(userLombok);
    }
}
