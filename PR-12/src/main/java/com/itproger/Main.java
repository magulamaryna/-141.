package com.itproger;

class Main{

    public static void main(String[] args) {
        Transport bmw = new Transport();
        bmw.setValues(200.5f, 2000, "Black", new byte[] { 0, 0, 0 });

        Transport truck = new Transport();
        truck.speed = 130f;
        truck.weight = 5000;
        truck.color = "White";
        truck.coordinates = new byte[] { 0, 100, 0 };

        System.out.println(bmw.detValues());
        System.out.println(truck.detValues());
    }
}