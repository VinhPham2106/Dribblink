public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        IDandPasswords iDandPasswords = new IDandPasswords(); //instance of ID and Passwords

        LoginPage loginPage = new LoginPage(iDandPasswords.getLoginInfo());  ///instance of login page, and return the hashmap and send to login page
    }
}
