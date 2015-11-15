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
    
    static Index index = new Index(); //Índice
    static ArrayList<File> listFiles = new ArrayList();
    static ArrayList<Document> collection = new ArrayList(); //Colección de documentos
    static Reader read = new Reader(); //Lector de documentos
    static String path; //Ruta donde se encuentra la colección
    static HashMap<String,Integer> vocab = new HashMap<>();
    static HashMap<Integer,String> docum = new HashMap<>();
    static HashMap<Integer,Double> idf = new HashMap<>();
    static HashMap<Integer,Double> normaDoc = new HashMap<>();
    
    
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
            int idFile = 1;
            for(File file : listFiles){
                collection.add(reader.parserDoc(idFile, file));
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
    
    
     public static void createVoc(){
        Iterator it = collection.iterator();
            //System.out.println( col.get(docId-1).getFrec());
        
        int idTer = 1;
        while(it.hasNext()){
            Document doc = (Document) it.next();
            
            List<String> a = doc.getTokens();
            
            for ( String b : a ){
                if ( !vocab.containsValue(b) ){
                    vocab.put( b, idTer);
               System.out.println( idTer + " " + b);
                }
                ++idTer;
            }
        
       } 
    }
    
    public static void createDoc(){
        
    }
    
    /**
     * 
     */
    public static void indexation(  ){
        
        
       
        
        for(Document doc : collection){
            doc.fillFrec();
            index.createIndex(doc, collection,vocab);
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
            
            
            /*
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
            */
            
            
             //Eliminamos palabras vacías y estemizamos cada documento
            preprocessColl();
            
            
            createVoc();
            
            
            indexation();
            
            //System.out.println( collection.get(0).getFrec() );
            //System.out.println( collection.get(1).getFrec() );
            //System.out.println(index.getTokens().toString());
            index.printIndex();
            //System.out.println(index.getNumberDoc("el"));
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
