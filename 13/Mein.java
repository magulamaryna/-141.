package Main;

public class Mein {
    static void main() {
        Transport bmw = new Transport(200.6f,1000,"White",new byte[]{99,3,0});

        Transport track = new Transport(100.993f,2000,"Green");

        System.out.println(bmw.getSee());
        System.out.println(track.getSee());

    }
}

