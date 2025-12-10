package Main;

import java.util.stream.StreamSupport;

public class Track extends Transport{

    private boolean isLooks;

    public Track(float _speed, int _weight, String _color, boolean isLooks) {
        super(_speed, _weight, _color);
        this.isLooks = isLooks;
    }

    public void setStack(float speed, int weight, String color, byte[] coordinates, boolean isLooks) {
        super.speed = speed;
        super.weight = weight;
        super.color = color;
        super.coordinates = coordinates;
        this.isLooks = isLooks;

    }

    @Override
    public String getSee() {
        String infa = super.getSee();
        return infa + "\n" + getLooks();
    }

    public void setLooks(boolean isLooks){
        this.isLooks = isLooks;
    }
    public String getLooks(){
        if(isLooks)
            return "Track is Looks";
        else
            return "Track is NOT Looks";
    }


}
