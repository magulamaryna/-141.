package Main;

public class Transport {
    private float speed;
    private int weight;
    private String color;
    private byte[] coordinates;

    public Transport(float _speed, int _weight, String _color, byte[] _coordinates) {
        setStack(_speed, _weight, _color, _coordinates);
    }

    public Transport(float _speed, int _weight, String _color) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = new byte[0];
    }

    public void setStack(float speed, int weight, String color, byte[] coordinates) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinates = coordinates;
    }

    public String getSee() {
        String infa = "speed:" + speed + ". Weight: " + weight + ". Color:" + color + "\n";

        if (coordinates.length > 0) {
            String infaCoordinates = "coordinates:\n";
            for (byte el : coordinates)
                infaCoordinates += el + "\n";

            return infa + infaCoordinates;

        }

        return infa;
    }
}