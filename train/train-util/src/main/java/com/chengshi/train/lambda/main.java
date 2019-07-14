package com.chengshi.train.lambda;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-20 10:17
 */
public class main {
    public static void main(String[] args) {
        person person=new person();
        person.talk(new lanauage() {
            @Override
            public void say() {
                System.out.println("this is english !");
            }
        });

        person.talk(() -> System.out.println("this is english !"));



        person.eat(new Food() {
            @Override
            public void eat(String food) {
                System.out.println("this is "+food);
            }
        },"苹果");

        person.eat(food -> System.out.println("this is "+food),"苹果");

        person.name="香蕉";
        person.eat2(new Food() {
            @Override
            public void eat(String food) {
                System.out.println("This is food 2:"+food);
            }
        });

        person.eat2(food -> System.out.println("This is food 2:"+food));

        person.eat2(new Food() {
            @Override
            public void eat(String food) {
                System.out.println("This is food 2:"+food);
                System.out.println("结束了");
            }
        });

        person.eat2(food -> {
            System.out.println("This is food 2:"+food);
            System.out.println("结束了");
        });
    }



}
