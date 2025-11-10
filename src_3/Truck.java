public class Truck extends Transport {

    private boolean isLoaded;

    public  Truck(float speed, float weight, String color, boolean isLoaded) {
        super(speed, weight,color);
        this.isLoaded = isLoaded;
        System.out.println(super.getValues());
    }

    public void setLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }
    public void getLoaded() {
        if(isLoaded)
            System.out.println("Truck is loaded");
        else
            System.out.println("Truck is NOT loaded");
    }
}
