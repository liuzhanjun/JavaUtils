package com.yun.hai.threadtest;

public class A {


    public void Test() {
        B b = new B();
        MyThread myTHread = new MyThread();
        try {
            myTHread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myTHread.putB(b);

        Thread2 thread2 = new Thread2();

        thread2.putB(b);

        thread2.start();

        myTHread.start();
    }

    public static class Thread2 extends Thread {
        private B b;

        public void putB(B b) {
            this.b = b;

        }

        @Override
        public void run() {
            b.p2();
        }
    }


    public class B {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        synchronized public void p1() {
            setName("p1");
            System.out.println(name);

            for (int i = 0; i < 5; i++) {

                System.out.println("线程1====" + Thread.currentThread().getId() + name);
            }


        }

        synchronized public void p2() {
            setName("p2");
            System.out.println(name);

            for (int i = 0; i < 5; i++) {


                System.out.println("线程2=====" + Thread.currentThread().getId() +name);
            }

        }
    }

    public static class MyThread extends Thread {
        private B b;

        public void putB(B b) {
            this.b = b;


        }

        @Override
        public void run() {
            b.p1();


        }
    }

}

