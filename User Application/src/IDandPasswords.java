import java.util.*;
public class IDandPasswords {
    HashMap<String, String> loginInfo = new HashMap<String, String>();  //hashmaps to contain all the logins

    IDandPasswords() {  //Constructor that contains all the usernames and passwords
        loginInfo.put("Oliver", "Cheung");
        loginInfo.put("Vinh", "Chilling");
        loginInfo.put("Pedro", "Sushi");
        loginInfo.put("Ed", "Eddy");
        loginInfo.put("","");
    }

    protected HashMap<String, String> getLoginInfo() {  //access modifier will be protected because not everyone can access except getter method
        return loginInfo;
    }
}
