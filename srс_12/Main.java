public class Main {

    public static void main(String[] args) {

        Transport bmw = new Transport();
        bmw.setValues(200.5f, 2000.0f, "red", new byte[]{0, 0, 0});


        Transport truck = new Transport();
        truck.speed = 100.5f;
        truck.weight = 5000.0f;
        truck.color = "black";
        truck.coordinates = new byte[]{0, 100, 0};

        System.out.println(bmw.getValues());
        System.out.println(truck.getValues());
    }
}