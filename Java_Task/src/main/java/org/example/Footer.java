package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Footer{
    public String footer = "";

    public Integer number_of_trade_and_extrd_structures;

    public Integer number_of_characters_in_trade_and_extrd_structures;

    public List<String> extractFooter = new ArrayList();


    {

        for (int i=0;i<importFile.lines.size();i++){
            if (importFile.lines.get(i).substring(0,5).equals("FOOTR")){
                footer=importFile.lines.get(i).substring(0, 5);
                number_of_trade_and_extrd_structures=Integer.parseInt(importFile.lines.get(i).substring(5,15));
                number_of_characters_in_trade_and_extrd_structures=Integer.parseInt(importFile.lines.get(i).substring(15,25));;

                extractFooter.add(footer);
                extractFooter.add(String.valueOf(number_of_trade_and_extrd_structures));
                extractFooter.add(String.valueOf(number_of_characters_in_trade_and_extrd_structures));
                }

            }

        }
        public void footerPushToCsv(){
            FileWriter writer = null;
            try {
                writer = new FileWriter("outputfooter.csv");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //String[] array = { "Tag","FileVersion","File creation date and time","File comment" };
            try {
                writer.write("Tag,Number of TRADE and EXTRD structures,Number of characters in TRADE and EXTRD structures");
                writer.write("\n");
                writer.flush();

                String row = extractFooter.stream().collect(Collectors.joining(","));

                writer.write(row);
                writer.write("\n");
                writer.flush();

                writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //writer.write("\n");


        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }


