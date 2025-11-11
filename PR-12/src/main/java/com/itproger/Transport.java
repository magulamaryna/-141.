package com.itproger;

public class Transport {

    public float speed;
    public int weight;
    public String color;
    public byte[] coordinates;

    public void setValues(float _speed, int _weight, String _color, byte[] _coordinates) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = _coordinates;
    }

    public String detValues() {
        String info = "Object speed: " + speed + ". Weight: " + weight + ". Color: " + color + "\n";

        String infoCoordinates = "Coordinates:\n";
        for (byte el : coordinates)
            infoCoordinates += el + "\n";

        return info + infoCoordinates;
    }
}
