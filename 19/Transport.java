package Main;

public abstract class Transport {
    protected float speed;
    protected int weight;
    protected String color;
    protected byte[] coordinates;
    public E_s e_e = new E_s();

    class E_s{
        private boolean isGo;
        private int km;

        public void setE_s(boolean isGo, int km){
            this.isGo = isGo;
            this.km = km;
        }

        public void isGo(boolean isGo){
            this.isGo = isGo;
        }

        public void ific() {
            if(isGo)
                System.out.println("Engine start");
            else
                System.out.println("Engine kaput na " + km + "km");
        }


    }

    public Transport(){}

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
    public abstract void moveDid();
    public abstract void sropDid();
}