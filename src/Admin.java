import java.util.*;
import java.io.*;

public class Admin {

    boolean newPatient(String firstname, String lastName,String gender,long phone, int age) {
        int id;
        String path = "IdRecods.txt";
        do {
            id = generateID();
        } while (numberExistsInFile(path, id));
        return true;
    }

    private int generateID() {
        Random rand = new Random();
        return rand.nextInt((99999 - 10000) + 1) + 10000;
    }

    private static boolean numberExistsInFile(String filePath, int number) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(String.valueOf(number))) {
                    return true; // Number found in file
                }
            }
            return false; // Number not found in file
        } catch (IOException e) {
            System.err.println("Internal Server Error");
            System.out.println("Internal Server Error");
            return false; // Assume number doesn't exist in case of an error
        }
    }

    void save() {

    }
}