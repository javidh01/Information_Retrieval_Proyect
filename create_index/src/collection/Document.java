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
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.tika.language.LanguageIdentifier;

/**
 * Clase que representa un documento de
 * la colección almacenado en tokens
 * 
 * @author javi
 */
public class Document {
    private final int id;
    private List<String> tokens = new ArrayList();
    private final String idiom;
    private final  StringTokenizer toke;
     HashMap<String, Integer> listfrec = new HashMap();
     HashMap<Integer,String> vocab = new HashMap();
     HashMap<Integer,String> docs = new HashMap();
    
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
       
        //Rellenamos la tabla de tokens
        fillToken();
    }
    
    /**
     * 
     * @param toke 
     */
    
    public void fillToken(){        
       while(toke.hasMoreElements()){ 
           String token = toke.nextToken();
           tokens.add(token);
        }
    }
    
     public void fillFrec(){        
         
         Iterator it = tokens.iterator();
       while(it.hasNext()){ 
           String token = (String) it.next();
            if( !listfrec.containsKey(token)){
               listfrec.put(token,1);
           }
           else{               
               listfrec.put(token,(int)listfrec.get(token)+1);
           }
        }
    }
    
    /**
     * 
     * @return HashMap<String,Integer>
     */
    public HashMap<String,Integer> getFrec(){
        return listfrec;
    }
    
     /**
     * 
     * Método que permite imprimir una lista pasada como argumento
     * 
     * @param lista 
     */
    public  void printList(List<String> lista){
        for (String l : lista) {
            System.out.println(l);
        }
    }
    
    
    /**
     * Devuelve la lista de tokens
     * @return 
     */
    public List<String> getTokens(){
        
        return tokens;
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
    
   
} //End Class