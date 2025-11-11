public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        Car bmw = new Car(200.5f, 2000.0f, "red", new byte[]{0, 0, 0});
        Truck truck = new Truck(130.5f, 5000.0f, "White", true);
        truck.setLoaded(false);
        truck.getValues();
    }
}