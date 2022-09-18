import java.io.*;
import java.net.Socket;
import java.util.*;
import org.apache.commons.io.FileUtils;

public class Queue {

    //Initializes Queue
    public static List<List<String>> q = new ArrayList<>();
    public static List<Court> courts = new ArrayList<>();
    public static HashMap<String, Integer> ports = new HashMap<String, Integer>();

    public static void AddUser() throws IOException {

        //Creating a File object for directory
        File directoryPath = new File("src/Input");

        //List of all files and directories
        File filesList[] = directoryPath.listFiles();

        for (File file : filesList) {

            Scanner input = new Scanner(new File(file.getAbsolutePath()));
            String name = input.nextLine();
            String size = input.nextLine();
            String mode = input.nextLine();


            input.close();
            Queue.q.add(Arrays.asList(name, size, mode));
        }

        FileUtils.cleanDirectory(directoryPath);

        System.out.println(Queue.q);

        Queue.AddToCourt();
    }


    public static void RemoveUser() throws IOException {
        //Creating a File object for directory
        File directoryPath = new File("src/Remove");

        //List of all files and directories
        File filesList[] = directoryPath.listFiles();

        for(File file : filesList) {

            Scanner input = new Scanner(new File(file.getAbsolutePath()));
            String name = input.nextLine();
            String size = input.nextLine();
            List<String> user = new ArrayList<>(Arrays.asList(name, size));

            for (Court i : Queue.courts) {
                if (i.playerList.contains(user)) {
                    i.playerList.remove(user);
                }
            }

            input.close();
        }

        FileUtils.cleanDirectory(directoryPath);
    }

    public static void AddToCourt() throws IOException {
        //Sort the courts by player availability -- Reverse Bubble sort
        for (int i = 0; i < courts.size(); i++) {
            for (int j = i; j < courts.size(); j++) {
                int c1 = courts.get(i).totalCapacity - courts.get(i).currCapacity;
                int c2 = courts.get(j).totalCapacity - courts.get(j).currCapacity;

                if (c1 < c2) {
                    Court temp = courts.get(i);
                    courts.set(i, courts.get(j));
                    courts.set(j, temp);
                }
            }
        }

        //If player availability > 0, request more players from queue
        for (Court i : courts) {
            int availability = i.totalCapacity - i.currCapacity;

            // If availability = 0, all subsequent availabilities will be 0.
            // If q size is 0, no more users are in queue
            if (availability == 0 || q.size() == 0) {
                break;
            } else {
                List<List<String>> playerChange;

                //Calls findGroup function to
                playerChange = findGroup(q, availability);
                q.removeAll(playerChange);
                i.playerList.addAll(0, playerChange);

            }
        }

        for (Court i : courts) {
            returnToUser(i);

            System.out.println(i.courtID);
            System.out.println(i.playerList);
            System.out.printf("\n");
        }
    }
    public static List<List<String>>  findGroup(List<List<String>> q, int groupRequest) {
        // q is formatted as such:
        // [userID, number of members in group]

        //Stores group members currently in group
        List<List<String>> currGroup = new ArrayList<>();

        //Tracks if a member that can be removed from the queue was found
        boolean found = true;

        //Tracks members to be removed from q
        List<List<String>> toRemove = new ArrayList<>();

        while (found) {
            //Every for loop must find a new user, else break
            found = false;

            //Checks if queue is empty
            if (q.size() == 0)
                return currGroup;

            for (List<String> i : q) {
                // If a value is found, flip found
                if (Integer.parseInt(i.get(1)) < groupRequest) {
                    groupRequest -= Integer.parseInt(i.get(1));
                    currGroup.add(i);
                    toRemove.add(i);
                    found = true;

                    //If value is equal to the remaining amount, break
                } else if (Integer.parseInt(i.get(1)) == groupRequest) {
                    groupRequest = 0;
                    currGroup.add(i);

                    //Ensures while break, even though it is true
                    found = false;
                    break;

                }
            }
            //Removes all items from q after the end of a for loop
            q.removeAll(toRemove);
        }

        return currGroup;
    }

    public static void returnToUser( Court court) throws IOException {
        int total = 0;
        for(List<String> j : court.playerList) {
            System.out.println("J: " + j.get(1));
            total += Integer.parseInt(j.get(1));
        }

        System.out.println(total);

        for (List<String> i : court.playerList) {
            if (i.size() == 0)
                continue;

            String name = i.get(0);
            File file = new File("src/Return/" + name + ".txt");

            FileWriter writer = new FileWriter("src/Return/" + name + ".txt");
            writer.write(court.courtID + "\n" + total);
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        //In the future this should call the list of all courts
        for (int i = 0; i < 10; i++) {
            List<List<String>> temp_l = new ArrayList<>();
            courts.add(new Court(i+1, 5, 0, temp_l));

        }
        AddUser();
        System.out.println(q);

        RemoveUser();
        System.out.println(courts.get(0).playerList);
        File returnDirectory = new File("src/Return");
    }
}
