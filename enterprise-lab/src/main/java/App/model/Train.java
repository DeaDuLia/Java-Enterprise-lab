package App.model;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Train {
    private int id;
    private int speedKoeff;
    private int waitingKoeff;

    public Train (int id, int speedKoeff, int waitingKoeff) {
        this.id = id;
        this.speedKoeff = speedKoeff;
        this.waitingKoeff = waitingKoeff;
    }
    public Train (int id) {
        this(id, 1, 1);
    }

    public Train() {

    }
}
