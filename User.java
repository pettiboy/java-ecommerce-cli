package UML;

import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class User {
    Integer id;
    String phone;
    String address;
    Date timestamp;
    boolean isStaff;
    private boolean inDB = false;
    
    public User(String phone, Scanner scanner) {
        this.id = (int) Utils.numOfLinesIn("users.csv");
        this.phone = phone;
        this.address = getOrAddAddress(phone, scanner);
        this.timestamp = new Date();

        addUserToFile(this);
    }

    public String getOrAddAddress(String phone, Scanner scanner) {
        File file = new File("users.csv");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
            // process the file, one line at a time
            while (fileScanner.hasNextLine()) {
                String[] line = fileScanner.nextLine().split(",");
                if (line[1].equals(phone)) {
                    fileScanner.close();
                    inDB = true;
                    if (line[4].equals("true")) {
                        this.isStaff = true;
                    }
                    return line[2];                    
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }        
        return Utils.getStringInRange("Your Address: ", 1, 100, scanner);
    }

    public void addUserToFile(User user) {
        String data = user.csvString();
        if (inDB) return;
        try {
            // Creates a Writer using FileWriter
            FileWriter writer = new FileWriter("users.csv", true);
            // Writes string and line seperator to the file
            writer.write(data);
            writer.write(System.getProperty("line.separator"));
            // Closes the writer
            writer.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String csvString() {
        return this.id.toString() + "," + this.phone + "," + this.address.toString() + "," + this.timestamp + "," + this.isStaff;
    }

}
