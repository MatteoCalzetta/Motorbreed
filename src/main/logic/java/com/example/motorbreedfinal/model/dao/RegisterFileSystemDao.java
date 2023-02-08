package com.example.motorbreedfinal.model.dao;

import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;


import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RegisterFileSystemDao {

    static int countOccurrences(String str, String sub){
        if (str.contains(sub)){
            return 1 + countOccurrences(str.replaceFirst(sub, ""), sub);
        }
        return 0;
    }

    public void registerNewAccount(String firstName, String lastName, String email, String password, String role) throws FailedRegistrationException{

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Luigi\\Desktop\\Users.txt", true))){
            String content  = Files.readString(Path.of("C:\\Users\\Luigi\\Desktop\\Users.txt"));

            int occurrences = countOccurrences(content, "id");
            occurrences = occurrences + 1;

            if(!content.contains(email)) {
                if (occurrences != 1) {
                    writer.newLine();
                }

                writer.write(email);
                writer.newLine();
                writer.write(password);
                writer.newLine();
                writer.write(role);
                writer.newLine();
                writer.write(firstName);
                writer.newLine();
                writer.write(lastName);
                writer.newLine();
                writer.write("id:" + occurrences);
                writer.newLine();
                writer.write("//");

            }else {
                throw new FailedRegistrationException("email already registered");
            }

            }catch (IOException e){
                //unhandled
            }
        }

}
