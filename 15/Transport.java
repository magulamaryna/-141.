package Main;

public class Transport {
    protected float speed;
    protected int weight;
    protected String color;
    protected byte[] coordinates;

    public Transport(float _speed, int _weight, String _color, byte[] _coordinates) {
        setStack(_speed, _weight, _color, _coordinates);
    }

    public Transport(float _speed, int _weight, String _color) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = new byte[0];
    }

    public void setStack(float _speed, int _weight, String _color, byte[] _coordinates) {
        this.speed = _speed;
        this.weight = _weight;
        this.color = _color;
        this.coordinates = _coordinates;
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