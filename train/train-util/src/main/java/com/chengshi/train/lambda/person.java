package com.chengshi.train.lambda;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-20 10:15
 */
public class person {
    String name="name";

    public void talk(lanauage lanauage){
        lanauage.say();
    }

    public void eat(Food food,String fruit){
        food.eat(fruit);
    }

    public void eat2(Food food){
        food.eat(name);
    }
}
