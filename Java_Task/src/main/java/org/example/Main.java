package org.example;

public class Main {
    public static void main(String[] args) {
        importFile file1= new importFile();
        file1.fileReader();
        //System.out.println(file1.lines.get(0).substring(0,5));

        Headr headerRead1= new Headr();
        System.out.println(headerRead1.extractHeadr);
        headerRead1.headerPushToCsv();

        Footer footerRead1= new Footer();
        System.out.println(footerRead1.extractFooter);
        footerRead1.footerPushToCsv();

        Trade tradeRead1= new Trade();
        System.out.println(tradeRead1.extractTrade);
        tradeRead1.tradePushToCsv();


    }
}