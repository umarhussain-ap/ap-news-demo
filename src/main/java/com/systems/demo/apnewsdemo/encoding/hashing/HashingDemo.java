package com.systems.demo.apnewsdemo.encoding.hashing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashingDemo {

    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into sign-um representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) {
        try {
            String entryFilename = "testingfile";
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] data = md.digest(entryFilename.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }

            log.info(" arrays to string ");
            log.info(Arrays.toString(data));
            log.info(" appending array:  ");
            log.info("using bigInteger: ");
            log.info(encryptThisString(entryFilename));
            log.info("Google Hashing");
            log.info(Hashing.sha512().hashString(entryFilename, StandardCharsets.UTF_8).toString());
            log.info("Iterative");
            log.info(sb.toString());

        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
