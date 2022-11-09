package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.stream.Collectors;


public class Trade {
    public String tag = "";
    public String dateTime = "";
    public String direction = "";
    public String itemID = "";
    public String price;
    public Integer quantity;
    public String buyer = "";
    public String seller = "";
    public String comment = "";
    public String version = "";
    public String nestedTags = "";



    public List<List<String>> extractTrade = new ArrayList();

    {
        for (int i = 0; i < importFile.lines.size(); i++) {
            if (importFile.lines.get(i).substring(0, 5).equals("TRADE")) {
                tag = importFile.lines.get(i).substring(0, 5);
                version = "";

                String dtt = importFile.lines.get(i).substring(5, 22);
                dateTime = dtt.substring(0, 4) + "/" + dtt.substring(4, 6) + "/" + dtt.substring(6, 8) + "-" + dtt.substring(8, 10) + ":" + dtt.substring(10, 12) + ":" + dtt.substring(12, 14) + "." + dtt.substring(14, 17);

                direction = importFile.lines.get(i).substring(22, 23);

                itemID = importFile.lines.get(i).substring(23, 35);

                //DecimalFormat df = new DecimalFormat("0.00");
                //df.setMaximumFractionDigits(2);
                //price = Float.valueOf(df.format(Float.parseFloat(importFile.lines.get(i).substring(36, 50))));
                price = importFile.lines.get(i).substring(35,36)+String.valueOf(Integer.parseInt(importFile.lines.get(i).substring(36,46)))+"."+importFile.lines.get(i).substring(46,50);

                quantity = Integer.parseInt(importFile.lines.get(i).substring(51, 61));

                buyer = importFile.lines.get(i).substring(61, 65);

                seller = importFile.lines.get(i).substring(65, 69);

                comment = importFile.lines.get(i).substring(69).replaceAll("[^a-zA-Z0-9\"?!]", " ");
                nestedTags = "";



                List<String> tempTrade = new ArrayList();
                tempTrade.add(tag);
                tempTrade.add(version);
                tempTrade.add(dateTime);
                tempTrade.add(direction);
                tempTrade.add(itemID);
                tempTrade.add(String.valueOf(price));
                tempTrade.add(String.valueOf(quantity));
                tempTrade.add(buyer);
                tempTrade.add(seller);
                tempTrade.add("'"+comment+"'");
                tempTrade.add(nestedTags);

                extractTrade.add(tempTrade);
            }

            if (importFile.lines.get(i).substring(0, 5).equals("EXTRD")) {
                tag = importFile.lines.get(i).substring(0, 5);

                version = importFile.lines.get(i).substring(5, 9);

                String dt = importFile.lines.get(i).substring(9, 26);
                dateTime = dt.substring(0, 4) + "/" + dt.substring(4, 6) + "/" + dt.substring(6, 8) + "-" + dt.substring(8, 10) + ":" + dt.substring(10, 12) + ":" + dt.substring(12, 14) + "." + dt.substring(14, 17);

                direction = importFile.lines.get(i).substring(26, 30);
                if(direction.equals("BUY_")){
                    direction = "BUY";
                }

                itemID = importFile.lines.get(i).substring(30, 42);

                //DecimalFormat df = new DecimalFormat("0.00");
                //df.setMaximumFractionDigits(2);
                //price = Float.valueOf(df.format(Float.parseFloat(importFile.lines.get(i).substring(43, 57))));
                price = importFile.lines.get(i).substring(42,43)+String.valueOf(Integer.parseInt(importFile.lines.get(i).substring(43,53)))+"."+importFile.lines.get(i).substring(53,57);



                quantity = Integer.parseInt(importFile.lines.get(i).substring(58, 68));

                buyer = importFile.lines.get(i).substring(68, 72);

                seller = importFile.lines.get(i).substring(72, 76);
                comment = "";

                nestedTags = importFile.lines.get(i).substring(76);


                List<String> tempTrade = new ArrayList();
                tempTrade.add(tag);
                tempTrade.add(version);
                tempTrade.add(dateTime);
                tempTrade.add(direction);
                tempTrade.add(itemID);
                tempTrade.add(String.valueOf(price));
                tempTrade.add(String.valueOf(quantity));
                tempTrade.add(buyer);
                tempTrade.add(seller);
                tempTrade.add(comment);
                tempTrade.add(nestedTags);

                extractTrade.add(tempTrade);

            }

        }

    }
    public void tradePushToCsv(){
        FileWriter writer = null;
        try {
            writer = new FileWriter("outputtrade.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String[] array = { "Tag","FileVersion","File creation date and time","File comment" };
        try {
            writer.write("Tag,Version,Trade Date and Time,Direction,ItemID,Price,Quantity,Buyer,seller,comment,Nested tags");
            writer.write("\n");
            writer.flush();

            for(int i=0;i<extractTrade.size();i++){
                String row = extractTrade.get(i).stream().collect(Collectors.joining(","));
                writer.write(row);
                writer.write("\n");
                writer.flush();

            }

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
