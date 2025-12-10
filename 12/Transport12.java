package Main;

public class Transport12 {
    public float speed;
    public int weight;
    public String color;
    public byte[] coordinates;

    public void setStack(float _speed, int _weight, String _color, byte[] _coordinates){
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = _coordinates;
    }
    public String getSee() {
        String infa = "speed:" + speed + ". Weight: " + weight + ". Color:" + color + "\n";

        String infaCoordinates = "coordinates:\n";
        for(byte el : coordinates)
            infaCoordinates += el + ",";

        return infa+infaCoordinates;

    }
}