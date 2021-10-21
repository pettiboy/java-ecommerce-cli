package UML;

import java.util.Random;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PhoneOtp {
    String phone;
    String otp;

    public PhoneOtp(String phone) {
        this.phone = phone;
    }

    public void main(String[] args) {
    }

    public boolean generateOtp() {
        return sendOtp(getOtp());
    }

    public boolean validateOtp(String phone, String otp) {
        if (this.otp.equals(otp) && this.phone.equals(phone)) {
            return true;
        }
        return false;
    }

    private static String getOtp() {
        Random rand = new Random();
        return Integer.toString((rand.nextInt(1000000)));
    }

    private boolean sendOtp(String otp) {
        this.otp = otp;

        // make API request to otp provider
        // create/update a file 'otp.csv' and print inside it 'phone,otp'
        try (PrintWriter writer = new PrintWriter("otp.csv")) {
            writer.write(phone + ',' + otp);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

}
