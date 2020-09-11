package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: chenweilong
 * @Date: 2020/8/27
 * @Description:
 **/
public class Test {

    public static void main(String[] args) {

        ArrayList list2 = new ArrayList();
        System.out.println(Collections.emptyList().size());

        /*ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer e:
             list) {
            if(e == 2)continue;
            System.out.println(e);
        }*/

        /*list.forEach(item->{
            System.out.println(item);
            if(item == 1){
                return;
            }
        });*/
        System.out.println("999");



        /*List<DemoVos> list = new ArrayList<>();

        List<DemoVo> demoVos1 = new ArrayList<>();
        List<DemoVo> demoVos2 = new ArrayList<>();

        DemoVo demoVo1 = DemoVo.builder()
                .id(1)
                .username("aaa")
                .password("aaa")
                .build();
        DemoVo demoVo2 = DemoVo.builder()
                .id(2)
                .username("bbb")
                .password("bbb")
                .build();

        demoVos1.add(demoVo1);
        demoVos1.add(demoVo2);
        demoVos2.add(demoVo1);
        demoVos2.add(demoVo2);

        DemoVos build1 = DemoVos.builder()
                .tagName("我是777")
                .obj(demoVos1)
                .build();

        DemoVos build2 = DemoVos.builder()
                .tagName("我是888")
                .obj(demoVos2)
                .build();

        list.add(build1);
        list.add(build2);

        System.out.println(list);*/


    }

    public static Integer testE (){
        try {
            throw new NullPointerException();
        }catch (NullPointerException e){
            System.out.println("捕获NullPointerException");
            return 1;
        }
    }

}
