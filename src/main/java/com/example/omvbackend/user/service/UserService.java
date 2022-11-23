package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import com.example.omvbackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    private final String PEPPER_CHARACTERS = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
    private final Random random = new Random();


    // Adds salt and pepper to the user, and saves it in the database
     public User create(User user){
         String salt = generateSalt();
         String pepper = generatePepper();

         user.setSalt(salt);
         user.setPassword(hashPassword(pepper, user.getPassword(), salt));

         return repo.save(user);
     }

    //Read
    public List<User> getAll(){
         return repo.findAll();
    }

    public Optional<User> get(long id){
        return repo.findById(id);
    }

    //update -


    //delete
    public boolean delete(long id) {
        Optional<User> optionalEmployee = get(id);

        if (optionalEmployee.isPresent()) {
            repo.delete(optionalEmployee.get());
            return true;
        } else {
            return false;
        }
    }


    // Checking of the password
    public User checkLogin(User user){
        Optional<User> optionalEmployee = repo.findUserByUsername(user.getUsername());

        if(optionalEmployee.isEmpty()) return null;

        if (checkPassword(
                optionalEmployee.get().getPassword(),
                optionalEmployee.get().getSalt(),
                user.getPassword())) {
            return optionalEmployee.get();
        } else {
            return null;
        }
    }

    private boolean checkPassword(String userPassword, String userSalt, String passwordToCheck) {
        String hashToCheck;

        //Goes through the whole hash and converts it to the password then checks password
        for (int i = 0; i < PEPPER_CHARACTERS.length(); i++) {
            hashToCheck = hashPassword(
                    PEPPER_CHARACTERS.substring(i, i + 1),
                    passwordToCheck,
                    userSalt);

            if (hashToCheck.equals(userPassword)) {
                return true;
            }
        }
        return false;
    }

    private String generatePepper() {
        return String.valueOf(
                PEPPER_CHARACTERS.charAt(
                        random.nextInt(PEPPER_CHARACTERS.length())));
    }

    /**
     * generates a random 16 character string, from 94 possible characters
     */
    private String generateSalt() {
        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            salt.append(Character.toChars(random.nextInt(94) + 32));
        }
        return salt.toString();
    }

    /**
     * hashes the password, with the salt and pepper added unto it
     */
    private String hashPassword(String pepper, String password, String salt) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] encodedHash = digest.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8));//hvordan forstår vi det byte set, specifikation for sprog.


        return bytesToHex(encodedHash);
    }

    /**
     * converts the byte[] into a string
     */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b); // bitwise operator "&"
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
