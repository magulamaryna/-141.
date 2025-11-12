package com.itproger;

class Main {

    public Main() {}

    public static void main(String[] args) {
        DB db = new DB();

        final int num =10;
        //num = 11;

        Person obj1 = new Person();
        Person obj2 = new Person();
        Person obj3 = new Person();
        Person.GetCount();
        //Person.count = 10;
        //System.out.println();

        add(5, 7, 6);
        add(5.5f, 7.3f);
        add("Hi", "World");
    }

    public void add(int a, int b) {
        int res = a + b;
        System.out.println("Res:" + res);
    }
    public static void add (float a, float b) {
        float res = a + b;
        System.out.println("Res:" + res);
    }
    public static void add (String a, String b) {
        String res = a + b;
        System.out.println("Res:" + res);
    }
    public static void add (int a, int b, int y) {
        int res = a + b + y;
        System.out.println("Res:" + res);
    }
}