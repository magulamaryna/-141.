public abstract class Transport {
    protected float speed;
    protected float weight;
    protected String color;
    protected byte[] coordinates;

    public Transport() {}

    public  Transport(float _speed, float _weight, String _color, byte[] _coordinates) {
       // System.out.println("Object is created");
        setValues(_speed, _weight, _color, _coordinates);
        System.out.println(getValues());
    }

    public  Transport(float speed, float weight, String color) {
        this.speed = speed;
        this.weight = weight;
        this.color = color;
        this.coordinates = new byte[0];

    }

    public void setValues(float _speed, float _weight, String _color, byte[] _coordinates) {
        speed = _speed;
        weight = _weight;
        color = _color;
        coordinates = _coordinates;
    }

    public String getValues() {
        String info = "Object speed: " + speed + " Weight: " + weight + " Color: " + color + "\n";
        if (coordinates.length > 0) {
            String infoCoordinates = "Coordinates:\n";
            for (byte el : coordinates) {
                infoCoordinates += el + "\n";
            }
            return info + infoCoordinates;
        }
        return info;
    }

    public abstract void moveObject();
    public abstract void stopObject();

}