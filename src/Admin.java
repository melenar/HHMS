import java.util.*;
import java.io.*;

public class Admin {

    private static void appendToFile(String filePath, String text) {
        try {
            // Create file if it doesn't exist
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open file in append mode
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Append text to file
            bw.write(text);
            bw.newLine(); // Move to the next line

            // Close the resources
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println("Internal Server Error");
            System.out.println("Internal Server Error");
        }
    }

    private int generateID() {
        Random rand = new Random();
        return rand.nextInt((99999 - 10000) + 1) + 10000;
    }

    int newPatient(String firstname, String lastName, String gender, long phone, int age) {
        int id;
        String path = "IdRecods.txt";
        do {
            id = generateID();
        } while (numberExistsInFile(path, id));

        String patientId = String.valueOf(id);
        appendToFile(path, patientId);

        saveToFile(patientId + "_PatientInfo.txt", firstname, lastName, gender, phone, age);


        return id;
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

    private void saveToFile(String fileName, String firstname, String lastName,String gender,long phone, int age) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(firstname + "\n");
            fw.write(lastName + "\n");
            fw.write(gender + "\n"); 
            fw.write(phone + "\n");
            fw.write(age + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Internal Server Error");
            System.out.println("Internal Server Error");
        }
    }

    void save() {

    }
}