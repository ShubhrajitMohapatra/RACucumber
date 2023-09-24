package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JasonToString {
    public static String generatePayload(String fileName) throws IOException {
        String FilePath = "/Users/shubhrajitmohapatra/Downloads/Project/RA_Cucumber/"+fileName;

        //retuning the file into String.
        return new String(Files.readAllBytes(Paths.get(FilePath)));

    }
}
