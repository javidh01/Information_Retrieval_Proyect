/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.*;

/**
 * Clase que representa el índice de documentos
 * @author javi
 */
public class Index {
    private final Map<String, Map<Integer, Integer>> index = new LinkedHashMap<String, Map<Integer, Integer>>();
    
    
    /**
     * Crea el índice a partir de un documento
     * @param doc 
     */
    public void createIndex(Document doc){
        for(String token : doc.getTokens()){
            indexToken(doc.getId(), token);
        }
    }
    
    
    /**
     * Devuelve la tabla de tokens
     * @return 
     */
    public List<Pair<String, Integer>> getTokens(){
        List<Pair<String, Integer>> tokens = new ArrayList<Pair<String, Integer>>();
        String key;
        
        for(Object entry : index.entrySet()){
            Map.Entry pairs = (Map.Entry) entry;
            key = pairs.getKey().toString();
            tokens.add(new Pair<String, Integer>(key, ((Map<String, Integer>)pairs.getValue()).size()));
        }
        
        return tokens; //Devolvemos la tabla con los tokens
    }
    
    
    /**
     * Devuelve el número de documentos
     * en los que aparece el término
     * @param token
     * @return 
     */
    public int getNumberDoc(String token){
        int n_doc = 0; //Inicialmente a cero
        Map<Integer, Integer> oc = index.get(token); //Número de ocurrencias del token
        
        if(oc != null){ //Si el término está en el índice
            n_doc = oc.size(); //Vemos en cuántos documentos aparece
        }
        
        return n_doc; //Devolvemos dicho número
    }
    
    
    /**
     * Devuelve el número total de ocurrencias
     * del término en el índice
     * @param token
     * @return 
     */
    public int getOcurrences(String token){
        int n_oc = 0;
        Map<Integer, Integer> oc = index.get(token);
        
        if(oc != null){ //Si está en el índice
            for(Object entry : oc.entrySet()){
                Map.Entry pairs = (Map.Entry) entry;
                n_oc += (int)pairs.getValue();
            }
        }
        
        return n_oc;
    }
    
    private void indexToken(int docId, String token){
        Map<Integer, Integer> oc = index.get(token); //Número de ocurrencias del token
        
        if(oc != null){ //Si el token está en el índice
            Integer currentFreq = oc.get(docId);
            if(currentFreq != null) //Y está en el mismo documento
                oc.put(docId, currentFreq++);
            else oc.put(docId, 1); //Si no está en ese documento
        }
        else{ //No está en el índice
            oc = new HashMap<Integer, Integer>();
            oc.put(docId, 1);
            index.put(token, oc); //Lo añadimos al índice
        }
    }
} //End class
