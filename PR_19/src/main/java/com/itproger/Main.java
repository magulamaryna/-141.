package com.itproger;

class Main {

    public Main() {
    }

    public static void main(String[] args) {
        Car bmv = new Car();
        bmv.lightInfo();
        bmv.setLight(true);
        bmv.lightInfo();
    }
}
