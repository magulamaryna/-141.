package Main;

public class Car extends Transport{

    public Car(float _speed, int _weight, String _color, byte[] _coordinates){
        super(_speed, _weight, _color, _coordinates);
        System.out.print(super.getSee());
    }
}
