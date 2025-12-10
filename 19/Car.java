package Main;

public class Car extends Transport implements IHala {

    private boolean hale;

    public Car(){
        hale = false;
    }

    public Car(float _speed, int _weight, String _color, byte[] _coordinates){
        super(_speed, _weight, _color, _coordinates);
        hale = false;
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

    @Override
    public void steMohamed(boolean data) {
        this.hale = data;

    }

    @Override
    public void MohamedLite() {
        if(hale)
            System.out.println("Hale it`s real");
        else
            System.out.println("Hale it`s lie");
    }
}
