/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.awt.EventQueue;
import java.io.*;
import java.util.*;

import java.util.ArrayList;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author javi
 */
public class Collection{
    
    //static Index index;
    static ArrayList<File> listFiles = new ArrayList<File>();
    static ArrayList<Document> collection = new ArrayList<Document>();
    static Document doc = new Document();
    static Reader read = new Reader();
    
    static String path;
    
    
    /** 
     * 
     * Metodo que almacena en una lista las rutas
     * de todos los archivos que cuelgan de un directorio
     * 
     * @param file       
     * @return Boolean      
     * 
     **/

    public static Boolean addFiles(File file){ 
       /*
        if(!file.exists()){
            System.out.println(file + " no existe.");
        } else if(file.isDirectory()){
            for(File f : file.listFiles()){
                addFiles(f);                   
            }
        } else{
            listFiles.add(file);               
        }
        */
        
        if(file != null){
            for(File fileEntry : file.listFiles()){
                if(fileEntry.isDirectory())
                    addFiles(fileEntry);
                else listFiles.add(fileEntry);
            }
        }
        else return false;
        
        return true;
    }
    
    public static void load(File dir) throws IOException, TikaException, SAXException{
        Reader reader = new Reader();
        //File dir = path;
        addFiles(dir);
        
        int idFile = 0;
        for(File file : listFiles){
            collection.add(reader.readDoc(idFile, file));
            idFile++;
        }
    }
    
    /**
     * 
     */
    public static void preprocessColl(){
        for(Document doc : collection){
            TokenProcessor.removeStopWords(doc);
            TokenProcessor.stemDocument(doc);
        }
    }
    
    /**
     * 
     */
    public static void indexation(){
        System.out.println(collection.size());
        for(Document doc : collection){
            //index.createIndex(doc);
            System.out.println(doc.getIdiom());
        }
    }
    
    public static void printList(List<String> lista){
        for (String l : lista) {
            System.out.println(l);
        }
    }
    
    public static void printCol(){
        for(Document doc : collection){
            System.out.println(doc.getId());
            ArrayList<String> d = new ArrayList(doc.getTokens());
            doc.createVoc();
            //printList(d);
        }
    }
    
    /**
     * 
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) throws IOException, TikaException, SAXException{
        
        System.out.println("Introduzca el path de la colección\n");

        //Para leer los datos de entrada
        Scanner in = new Scanner(System.in);
        path = in.next();       
        
        File file = new File(path);     
        
        //Añadimos la ruta a nuestra función
        Boolean c = addFiles(file);
        
        System.out.println(c);
        
        load(file);
         //doc = read.
        //doc.createVoc();
        
        //System.out.println("He cargado la colección");
        preprocessColl();
        printCol();
        //System.out.println("He procesado la colección");
        
        //printCol();
        //indexation();
        //
        //System.out.println(collection.size());
    }
    
} //End Class
