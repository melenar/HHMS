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
        return id.toLowerCase();
    }

    String newPatient(String firstname, String lastName, String gender, long phone, String dob) {
        String patientId;
        String path = "files/IdRecords.txt";
        do {
            patientId = generateID(dob, firstname, lastName);
        } while (numberExistsInFile(path, patientId));

        appendToFile(path, patientId);
        saveToFile(patientId + "_PatientInfo.txt", firstname, lastName, gender, phone, dob);

        return patientId;
    }

    static boolean numberExistsInFile(String filePath, String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(id)) {
                    return true; // id found in file
                }
            }
        } catch (IOException e1) { e1.printStackTrace(); }
        return false; // id not found in file
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

    public String[] loadHistory(String patientId) {
        String path = patientId + "_PatientInfo.txt";
        List<String> historyList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            StringBuilder visitData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Start of a new visit
                if (line.startsWith("Visit:")) {
                    if (visitData.length() > 0) {
                        historyList.add(visitData.toString()); // Add previous visit data to history list
                        visitData = new StringBuilder(); // Reset visit data
                    }
                    visitData.append(line).append("\n"); // Add visit timestamp to visit data
                } else {
                    visitData.append(line).append("\n"); // Add other visit details to visit data
                }
            }

            // Add the last visit data to the history list
            if (visitData.length() > 0) {
                historyList.add(visitData.toString());
            }
        } catch (IOException e1) { e1.printStackTrace(); }

        return historyList.toArray(new String[0]);
    }

    void saveMessage(String message, String sender, String patientId) {
        String path = patientId + "_messages.txt";
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e1) { e1.printStackTrace(); }
        
        try (FileWriter writer = new FileWriter(path, true)) {
            // Append the message to the file
            writer.write("["+(new Date())+"]"+sender + ":\n" + message + "\n");
        } catch (IOException e1) { e1.printStackTrace(); }
    }

    String loadMessages(String patientId) {
        String path = patientId + "_messages.txt";
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e1) { e1.printStackTrace(); }

        StringBuilder messages = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.append(line).append("\n"); // Append each line to the messages StringBuilder
            }
        } catch (IOException e1) { e1.printStackTrace(); }

        return messages.toString();
    }
    
    public static String getPatientName(String patientId) {
        String path = patientId + "_PatientInfo.txt";
        String name = "";
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
             name = ((String)reader.readLine() + " " + reader.readLine()); // Return the patient name
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;    
}
    
    public static String getPatientPhone(String patientId) {
    	String path = patientId + "_PatientInfo.txt";
        String phone = "0";
        BufferedReader reader;
        try {
        	reader = new BufferedReader (new FileReader(path));
                	
        	reader.readLine();
        	reader.readLine(); // Skip the patient name
        	reader.readLine(); //
        	
        	try {
            	phone = reader.readLine(); // Return the patient phone number
            } catch (NumberFormatException e) { e.printStackTrace(); } 
              catch (IOException e) { e.printStackTrace(); }
        	
        } catch (IOException e) { e.printStackTrace(); } // Skip the patient ID

        
return phone;
}
}