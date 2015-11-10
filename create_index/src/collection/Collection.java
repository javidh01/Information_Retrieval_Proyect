/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;


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
    
    static Index index; //Índice
    static ArrayList<File> listFiles = new ArrayList();
    static ArrayList<Document> collection = new ArrayList(); //Colección de documentos
    static Reader read = new Reader(); //Lector de documentos
    static String path; //Ruta donde se encuentra la colección
    
    
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
       
        if(!file.exists()){
            //System.out.println(file + " no existe.");
            return false;
        }else if(file.isDirectory()){
            for(File f : file.listFiles()){
                addFiles(f);                   
            }
        } else{
            listFiles.add(file);               
        }
        
        /*
        if(file != null){
            for(File fileEntry : file.listFiles()){
                if(fileEntry.isDirectory())
                    addFiles(fileEntry);
                else listFiles.add(fileEntry);
            }
        }
        else return false;
        */
        
        return true;
    }
    
    /**
     * 
     * @param dir
     * @return true si se ha realizado correctamente
     *         false en caso contrario
     * @throws IOException
     * @throws TikaException
     * @throws SAXException 
     */
    public static Boolean load(File dir) throws IOException, TikaException, SAXException{
             
        if(addFiles(dir)){
            Reader reader = new Reader();
            int idFile = 0;
            for(File file : listFiles){
                collection.add(reader.readDoc(idFile, file));
                idFile++;
            }
            return true;
        }
        return false;
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
            index.createIndex(doc);
            System.out.println(doc.getIdiom());
        }
    }
    
    /**
     * 
     * @param lista 
     */
    public static void printList(List<String> lista){
        for (String l : lista) {
            System.out.println(l);
        }
    }
    
    /**
     * 
     */
    public static void printCol(){
        for(Document doc : collection){
            System.out.println(doc.getId());
            doc.createVoc();
        }
    }
    
    /**
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws org.apache.tika.exception.TikaException
     * @throws org.xml.sax.SAXException
     * 
     */
    public static void main(String[] args) throws IOException, TikaException, SAXException{
        
        System.out.println("Introduzca el path de la colección\n");

        //Para leer los datos de entrada
        //Scanner in = new Scanner(System.in);
        //path = in.next();
        
        path = "C:\\Users\\javi\\Desktop\\colecciones";
        
        //Directorio donde se encuentra la colección
        File file = new File(path);     
        
        //Añadimos la ruta a nuestra función
        
        if(load(file)){ //Si se carga correctamente   
            System.out.println("Se ha cargado correctamente");        
        
            System.out.println("Tamaño de listFiles: " + listFiles.size());
            System.out.println("Tamaño de la colección: " + collection.size());
            
            Reader read = new Reader();
            
            //Para cada documento
            for(Document doc : collection){
                List<String> token = doc.getTokens(); //Obtenemos los tokens del documento
                System.out.println("Idioma: " + doc.getIdiom()); //Idioma
                read.readDoc(doc.getId(),listFiles.get(doc.getId())); //Leemos el documento
                
                //Eliminamos palabras vacías y estemizamos cada documento
                preprocessColl();
                
                //Imprimimos los tokens
                printList(token);
                
                
            }        
          
        //System.out.println("He cargado la colección");
        //preprocessColl();
        //printCol();
        //System.out.println("He procesado la colección");
        
        //printCol();
        //indexation();
        //
        //System.out.println(collection.size());
        } //End if
        else{
            System.err.print("\nNo se ha podido cargar la colección desde ese directorio\n");
        }
    }
    
} //End Class
