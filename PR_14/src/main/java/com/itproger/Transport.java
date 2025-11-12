package com.itproger;

public class Transport {

    private float speed;
    private int weight;
    private String color;
    private byte[] coordinates;

    public Transport() {}

    public Transport(float _speed, int _weight, String _color, byte[] _coordinates) {
        // System.out.println("Object is created");
        setValues( _speed,  _weight,  _color,  _coordinates);
        System.out.println(detValues());
    }

    public Transport(float speed, int weight, String color) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinates = new byte[0];
    }

    public void setValues(float _speed, int _weight, String _color, byte[] _coordinates) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = _coordinates;
    }

    public String detValues() {
        String info = "Object speed: " + speed + ". Weight: " + weight + ". Color: " + color + "\n";

        if(coordinates.length > 0) {
            String infoCoordinates = "Coordinates:\n";
            for (byte el : coordinates)
                infoCoordinates += el + "\n";

            return info + infoCoordinates;
        }

        return info;
    }

}
