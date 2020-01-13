package com.train.singlethread;

/**
 * @author tjshan
 * @date 2020/1/10 13:54
 */
public class UserThread extends Thread{

    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    @Override
    public void run() {
        System.out.println(myname+"Begin……");
        while (true){
            gate.pass(myname,myaddress);
        }

    }
}
