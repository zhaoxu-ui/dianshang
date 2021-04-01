package com.bjsxt.test;

public class TestCode {
    public static void main(String[] args) {
        String admin = "liyu9,a-sunhao,w_fanlichao,w_guofeng,w_laiying,w_zhaobaorui,w_sunmingjie,zhaomin3,w_jichujiagouyun";
        String user = "liyu9";
        //int i = admin.indexOf(user);
        //System.out.println(i);
        String substring = admin.substring(admin.indexOf(user) + 4);
        System.out.println(substring.toString());
    }
}
