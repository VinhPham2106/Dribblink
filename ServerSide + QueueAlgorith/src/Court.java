import java.util.*;

public class Court {
    int totalCapacity;
    int courtID;
    int currCapacity;
    List<List<String>> playerList;

    int mode;
    public Court (int courtID, int mode, int currCapacity, List<List<String>> playerList) {
        this.totalCapacity = mode*2;
        this.currCapacity = currCapacity;
        this.playerList = playerList;
        this.courtID = courtID;
        this.mode = mode;

    }
}
