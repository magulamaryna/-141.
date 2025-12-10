package Main;

import java.util.stream.StreamSupport;

public class Track extends Transport{

    private boolean isLooks;

    public Track(float _speed, int _weight, String _color, boolean isLooks) {
        super(_speed, _weight, _color);
        this.isLooks = isLooks;
        System.out.print(super.getSee());
    }
    public void setLooks(boolean isLooks){
        this.isLooks = isLooks;
    }
    public void getLooks(){
        if(isLooks)
            System.out.print("Track is Looks");
        else
            System.out.print("Track is NOT Looks");
    }


}
