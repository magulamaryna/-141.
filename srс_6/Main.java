public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        Car bmw = new Car(200.5f, 2000.0f, "red", new byte[]{0, 0, 0});
        bmw.engine.setValues(false, 200);
        bmw.engine.info();

        Truck truck = new Truck(130.5f, 5000.0f, "White", true);
        truck.engine.setValues(true, 1);
        truck.engine.info();

        Car flyCar = new Car(200.5f, 2000, "Black", new byte[]{0, 0, 0}) {
            @Override
            public String getValues() {
                return "Fly Car";
            }
            @Override
            public void moveObject() {
                System.out.println("Object is flying");
            }
        };
        flyCar.moveObject();
    }
}