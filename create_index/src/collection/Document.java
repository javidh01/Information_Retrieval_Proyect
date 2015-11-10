/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

//import static collection.Collection.tokens;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.tika.language.LanguageIdentifier;

/**
 * Clase que representa un documento de
 * la colección almacenado en tokens
 * 
 * @author javi
 */
public class Document {
    private final int id;
    static List<String> tokens = new ArrayList<String>();
    private final String idiom;
    private StringTokenizer toke;
    static HashMap<Integer,String> vocab = new HashMap<>();
    static HashMap<Integer,String> docs = new HashMap<>();
    //private static final AtomicInteger count = new AtomicInteger(0);
    
    public Document(){
        id = 0;
        idiom = null;
    }
    
    /**
     * Constructor por defecto
     * 
     * @param id
     * @param texto
     */
    public Document(int id, String texto){
        //this.id = count.incrementAndGet();
        this.id = id;
        
        //Obtenemos el lenguaje en el que está el documento
        LanguageIdentifier ident = new LanguageIdentifier(texto);
        this.idiom = ident.getLanguage();
        
        //Eliminamos caracteres que no vamos a tokenizar
        String delim = ":;. \"|\'=(),-+/%1234567890•¿?¡!/—...»«{}[]@#©\t\n\b\r\f";
        toke = new StringTokenizer(texto.toLowerCase(),delim);
        //String delim ="[^\\p{L}\\p{N}]";
        
        this.tokens = new ArrayList<String>(Arrays.asList(toke.toString()));
        //this.tokens = new ArrayList<String>(Arrays.asList(texto.replaceAll(delim," ").toLowerCase().split("\\s")));
    }
    
    /**
     * Devuelve la lista de tokens
     * @return 
     */
    public List<String> getTokens(){
        return this.tokens;
    }
    
    /**
     * Devuelve el id del documento
     * @return id
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Devuelve el idioma del documento
     * @return idiom
     */
    public String getIdiom(){
        return this.idiom;
    }
    
    public void createVoc(){
        Iterator it = tokens.iterator();
        int idTer = 1;
        while(it.hasNext()){
            vocab.put(idTer, (String) it.next().toString());
            System.out.println(idTer + " - " + vocab.get(idTer));
            idTer++;
        }
    }
    
    public void createDoc(ArrayList<File> listFiles){
        Iterator it = listFiles.iterator();
        int idDoc = 1;
        while(it.hasNext()){
            docs.put(idDoc, (String) it.next());
            idDoc++;
        }
    }
} //End Class