package Main;

import Main.dIDi.bulbulator;
import com.sun.tools.javac.Main;

class Mein {
    public void Main() {
    }

    public static void main(String[] args) {
        bulbulator dIDi = new bulbulator();
        
        p orbit1 = new p();
        p orbit2 = new p();
        p orbit3 = new p();
        p orbit4 = new p();
        p.getCoutrer();
        p.counter = 10;

        add(1000, 7);
        add(900, 90,3);
        add(983.3f, 9.7f);
        add("Hi", " Mother");
    }

    public static void add(int a, int b) {
        int reg = a - b;
        System.out.println("REG:" + reg);
    }

    public static void add(float a, float b) {
        float reg = a + b;
        System.out.println("REG:" + reg);
    }
    public static void add(String a, String b) {
        String reg = a + b;
        System.out.println("REG:" + reg);
    }
    public static void add(int a, int b, int c) {
        int reg = a + b + c;
        System.out.println("REG:" + reg);
    }
}

