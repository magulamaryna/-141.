package PR_14;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, String> users;

    private static final String FILE_NAME = "users.dat";

    public UserManager() {

        loadUsers();
    }

    public void addUser(String login, String password) {

        users.put(login, password);

        saveUsers();
    }

    public boolean checkUser(String login, String password) {

        if(!users.containsKey(login)) {
            return false;
        }

        return users.get(login).equals(password);
    }

    private void saveUsers() {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            out.writeObject(users);

            out.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    private void loadUsers() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            users =
                    (HashMap<String, String>) in.readObject();

            in.close();

        } catch(Exception e) {

            users = new HashMap<>();
        }
    }
}