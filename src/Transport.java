public class Transport {
    public float speed;
    public float weight;
    public String color;
    public byte[] coordinates;

    public void setValues(float _speed, float _weight, String _color, byte[] _coordinates) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = _coordinates;
    }

    public String getValues() {
        String info = "Object speed: " + speed + " Weight: " + weight + " Color: " + color + "\n";
        String infoCoordinates = "Coordinates:\n";
        for (byte el : coordinates) {
            infoCoordinates += el + "\n";
        }
        return info + infoCoordinates;
    }
}