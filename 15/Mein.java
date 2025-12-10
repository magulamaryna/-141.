package Main;

public class Mein {
    static void main() {
        Car bmw = new Car(200.6f,1000,"White",new byte[]{99,3,0});

        Track track = new Track(100.993f,2000,"Green", true);
        track.setStack(100.993f,2000,"Green", new byte[]{9,9,3}, true);

        System.out.println(track.getSee());

    }
}

