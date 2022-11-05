package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class importFile{
    String line = "";
    protected static List<String> lines = new ArrayList();
    BufferedReader br;

    public void fileReader(){
        System.out.println("abs");
        try {
            br = new BufferedReader(new FileReader("SAMPLE_v3.dat"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while(true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                System.out.println("This is an empty file");
            }
            lines.add(line);
        }
        System.out.println(lines);
    }

}