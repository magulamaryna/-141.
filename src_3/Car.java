public class Car extends Transport {

    public Car(float _speed, float _weight, String _color, byte[] _coordinates) {
        setValues(_speed, _weight, _color, _coordinates);
        System.out.println(getValues());
    }
}
