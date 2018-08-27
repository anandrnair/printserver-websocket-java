package com.faya.printserver;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.*;

public class PrintServerThread extends Thread {
//    private Socket socket;
    PrinterServices services;
    PrintService[] printServices;
    String filePath;

    public PrintServerThread( String filePath) {
        this.printServices = PrintServiceLookup.lookupPrintServices(null, null);
        this.services = new PrinterServices();
        this.filePath = filePath;
    }

    public void run() {
        try {
//            InputStream input = socket.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//
//            OutputStream output = socket.getOutputStream();
//            PrintWriter writer = new PrintWriter(output, true);
////            String tempFilename = "/Users/Anand/Desktop/testReceived.pdf";

//
//            String text;

//            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//            System.out.println("Number of print services: " + printServices.length);
//            com.faya.printserver.PrinterServices services = new com.faya.printserver.PrinterServices();
//            System.out.println("***********************************");
//            for (PrintService printer : printServices) {
//                System.out.println("Printer: " + printer.getName());
////            services.printService(printer);
//                System.out.println("***********************************");
//            }

//            String tempFilename = filePath;
            PrinterServices services = new PrinterServices();
            PrintService[] printServices = services.listPrinters();
            services.startPrint(printServices[0], filePath);

//            do {
//                text = reader.readLine();
//                System.out.println(text);
//                String reverseText = new StringBuilder(text).reverse().toString();
//                writer.println("Server: " + reverseText);
//
//            } while (!text.equals("bye"));

//            try {
//                output = new FileOutputStream(tempFilename);
//            } catch (FileNotFoundException ex) {
//                System.out.println("File not found. ");
//            }
//
//            byte[] bytes = new byte[16*1024];
//
//            int count;
//            while ((count = input.read(bytes)) > 0) {
//                output.write(bytes, 0, count);
//            }
//
//            output.close();
//            input.close();
//            writer.println("Server: File Received");
//            services.startPrint(printServices[0],tempFilename);




//            socket.close();

        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
//            try {
////                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class PrinterServices {

    public PrintService[] listPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        return printServices;
    }

    public void printService(PrintService printService) {
        PrintService ps0 = printService;
        DocFlavor f[] = ps0.getSupportedDocFlavors();
        for(int i = 0; i < f.length; i++){
            System.out.println("MIME Type:"+f[i].getMimeType());
            System.out.println("Media Subtype:"+f[i].getMediaSubtype());
            System.out.println("Media Type:"+f[i].getMediaType());
            System.out.println("--------------------------------------");
        }
    }

    public void startPrint(PrintService printService, String filename) {
        DocPrintJob job = printService.createPrintJob();
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printDataTransferCompleted(PrintJobEvent event){
                System.out.println("data transfer complete");
            }
            public void printJobNoMoreEvents(PrintJobEvent event){
                System.out.println("received no more events");
            }
        });
        try {
            FileInputStream fis = new FileInputStream(filename);
            Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            // Doc doc=new SimpleDoc(fis, DocFlavor.INPUT_STREAM.JPEG, null);
            PrintRequestAttributeSet attrib = new HashPrintRequestAttributeSet();
            attrib.add(new Copies(1));
            job.print(doc, attrib);
        }
        catch(Exception ex) {
            System.out.println("ex: " + ex.getMessage());
        }
    }
}
