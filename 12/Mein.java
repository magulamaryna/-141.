package Main;

public class Mein {
    static void main() {
        Transport12 bmw = new Transport12();
        bmw.setStack(200.6f,1000,"White",new byte[]{99,3,0});

        Transport12 track = new Transport12();
        track.setStack(100.993f,2000,"Green",new byte[]{9,9,3});

        System.out.println(bmw.getSee());
        System.out.println(track.getSee());

    }
}

