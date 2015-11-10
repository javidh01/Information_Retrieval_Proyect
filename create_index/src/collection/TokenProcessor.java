/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.spanishStemmer;

import java.util.List;

/**
 * Clase que permite procesar los tokens
 * @author javi
 */
public class TokenProcessor {
    
    /**
     * Elimina las palabras vacías del documento
     * 
     * @param doc 
     */
    public static void removeStopWords(Document doc){
        Cleaner cl = new Cleaner();
        
        if(doc.getIdiom().equals("es")){
            cl.removeSpStopWords(doc.getTokens());
        }        
    }
    
    /**
     * Estemiza un documento según su idioma
     * @param doc 
     */
    public static void stemDocument(Document doc){
        
        if(doc.getIdiom().equals("es")){
            SnowballStemmer sp = new spanishStemmer();
            stemDocumentES(doc,sp);
        }
    }
    
    /**
     * Estemiza el documento
     * 
     * @param doc
     * @param st 
     */
    public static void stemDocumentES(Document doc, SnowballStemmer st){
        List<String> tokens = doc.getTokens();
        
        for(int i = 0; i < tokens.size(); i++){
            st.setCurrent(tokens.get(i));
            if(st.stem()){
                tokens.set(i,st.getCurrent());
            }
        }
    }    
}//End Class
