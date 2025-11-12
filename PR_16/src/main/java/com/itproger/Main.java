package com.itproger;

class Main {

    public Main() {}

    public static void main(String[] args) {
        Car bmw = new Car(200.5f, 2000, "Black", new byte[] { 0, 0, 0 });

        Truck truck = new Truck(130f, 5000, "White", true);
        truck.moveObject();
        truck.setValues(130f, 5000, "White", new byte[] { 0, 100, 0 }, true);
        System.out.println(truck.getValues());
    }
}
