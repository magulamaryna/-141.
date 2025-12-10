package Main;

public class Car extends Transport{

    public Car(float _speed, int _weight, String _color, byte[] _coordinates){
        super(_speed, _weight, _color, _coordinates);
        System.out.print(super.getSee());
    }

    @Override
    public void moveDid() {
        System.out.println("Did is moving");
    }

    @Override
    public void sropDid() {
        System.out.println("Did stop");
    }
}
