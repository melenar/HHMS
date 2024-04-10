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
        } catch (IOException e1) { e1.printStackTrace(); }
    }

    private String generateID(String dob, String firstname, String lastName) {
        String id = firstname.substring(0,3)+lastName.substring(0,3)+dob.substring(0,2);
        return id;
    }

    String newPatient(String firstname, String lastName, String gender, long phone, String dob) {
        String patientId;
        String path = "IdRecods.txt";
        do {
            patientId = generateID(dob, firstname, lastName);
        } while (numberExistsInFile(path, patientId));

        appendToFile(path, patientId);
        saveToFile(patientId + "_PatientInfo.txt", firstname, lastName, gender, phone, dob);

        return patientId;
    }

    private static boolean numberExistsInFile(String filePath, String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(id)) {
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

    private void saveToFile(String fileName, String firstname, String lastName,String gender,long phone, String dob) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(firstname + "\n");
            fw.write(lastName + "\n");
            fw.write(gender + "\n"); 
            fw.write(phone + "\n");
            fw.write(dob + "\n");
            fw.close();
        } catch (IOException e1) { e1.printStackTrace(); }
    }

    public String loadHistory() {
        String history = "abcd";

        return history;
    }

    void saveMessage(String message, String sender, String patientId) {

    }
}