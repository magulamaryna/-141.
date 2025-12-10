package Main;

public class Mein {
    static void main() {
        Car bmw = new Car(200.6f,1000,"White",new byte[]{99,3,0});
        bmw.e_e.setE_s(false, 2000);
        bmw.e_e.ific();


        Track track = new Track(100.993f,2000,"Green", true);
        track.e_e.setE_s(true, 1000);
        track.e_e.ific();
        //track.moveDid();
        //track.setStack(100.993f,2000,"Green", new byte[]{9,9,3}, true);

        //System.out.println(track.getSee());

        Car flyCrai = new Car(200.6f,1000,"White",new byte[]{0,9,93}){
            @Override
            public String getSee() {
                return "fly Crai";
            }
            @Override
            public void moveDid() {
                System.out.println("Did is Craing");
            }

        };
        flyCrai.moveDid();
    }
}

