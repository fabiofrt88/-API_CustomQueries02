package com.example.API_CustomQueries02.entities;

import java.util.Random;

public class GeneratedString {

    Random random = new Random();

    // genera una Stringa random di 10 caratteri
    public String genString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}