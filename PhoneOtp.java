package UML;

import java.util.Random;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;

public class PhoneOtp {
    String phone;

    public PhoneOtp(String phone) {
        this.phone = phone;
    }

    public void main(String[] args) {
    }

    public boolean generateOtp() {
        return sendOtp(getOtp());
    }

    public boolean validateOtp(String phone, String otp) {
        try {
            Scanner readFileLine = new Scanner(new FileReader("otp.csv"));
            String[] splittedLine = readFileLine.next().split(",");
            String filePhone = splittedLine[0];
            String fileOtp = splittedLine[1];

            if (fileOtp.equals(otp) && filePhone.equals(phone)) {
                return true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    private static String getOtp() {
        Random rand = new Random();
        return Integer.toString((rand.nextInt(1000000)));
    }

    private boolean sendOtp(String otp) {
        try (PrintWriter writer = new PrintWriter("otp.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append(phone);
            sb.append(",");
            sb.append(otp);
            sb.append("\n");
            writer.write(sb.toString());
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
