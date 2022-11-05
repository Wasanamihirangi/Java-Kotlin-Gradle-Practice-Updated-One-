package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Headr{
    public String header = "";
    public String file_version = "";
    public String creation_date = "";
    public String comment = "";
    public List<String> extractHeadr = new ArrayList();


    {

        for (int i=0;i<importFile.lines.size();i++){
            if (importFile.lines.get(i).substring(0,5).equals("HEADR")){
                header=importFile.lines.get(i).substring(0, 5);


                if ((importFile.lines.get(i).substring(5,9).equals("0004"))|importFile.lines.get(i).substring(5,9).equals("0005")){
                    file_version=importFile.lines.get(i).substring(5,9);

                }

                if (importFile.lines.get(i).substring(9,26).length()==17){
                    String dt=importFile.lines.get(i).substring(9,26);
                    creation_date=dt.substring(0,4)+"/"+dt.substring(4,6)+"/"+dt.substring(6,8)+"-"+dt.substring(8,10)+":"+dt.substring(10,12)+":"+dt.substring(12,14)+"."+dt.substring(14,17);

                }
                comment=importFile.lines.get(i).substring(26);
                extractHeadr.add(header);
                extractHeadr.add(file_version);
                extractHeadr.add(creation_date);
                extractHeadr.add(comment);

            }
        }

    }

    public void headerPushToCsv(){
        FileWriter writer = null;
        try {
            writer = new FileWriter("outputheader.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String[] array = { "Tag","FileVersion","File creation date and time","File comment" };
        try {
            writer.write("Tag,FileVersion,File creation date and time,File comment");
            writer.write("\n");
            writer.flush();

            String row = extractHeadr.stream().collect(Collectors.joining(","));

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