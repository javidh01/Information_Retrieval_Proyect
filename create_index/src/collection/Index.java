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
    private final Map<Integer, Map<Integer, Integer>> index = new HashMap();
    
    
    /**
     * Crea el índice a partir de un documento
     * @param doc 
     */
    public void createIndex(Document doc, ArrayList<Document> col){
        //System.out.println("Entro createIndex()");
        List<String> tokens = doc.getTokens();
        for(String token : tokens ){
            indexToken(doc.getId(), token, col);
        }
    }
    
    
    /**
     * Devuelve la tabla de tokens
     * @return 
     */
    public List<Pair<String, Integer>> getTokens(){
        List<Pair<String, Integer>> tokens = new ArrayList();
        String key;
        
        for(Object entry : index.entrySet()){
            Map.Entry pairs = (Map.Entry) entry;
            key = pairs.getKey().toString();
            tokens.add(new Pair(key, ((Map<String, Integer>)pairs.getValue()).size()));
        }
        
        return tokens; //Devolvemos la lista con los tokens
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
    
    
    
    /**
     * 
     * @param docId
     * @param token 
     */
    private void indexToken(int docId, String token, ArrayList<Document> col){
        

        int currenFreq = -1;
        //System.out.println(docId);
        Map<Integer, Integer> oc = new HashMap<>(); //Número de ocurrencias del token
        
        //if(oc != null){
            HashMap<String, Integer> aux = col.get(docId-1).getFrec();
            Iterator it = aux.entrySet().iterator();
            //System.out.println( col.get(docId-1).getFrec());
            while(it.hasNext()){
                Map.Entry e = (Map.Entry)it.next();
                
                if(e.getKey() == token){
                  // System.out.println(e.getKey() + " == " + token);
                    currenFreq = (int) e.getValue();
                    oc.put(docId,currenFreq);
                    
                    
                }
            }    
        //}
        if ( currenFreq != -1  ){
           //int indice = 
           if ( !index.containsKey(token) )
               index.put(indice, oc); //Lo añadimos al índice
        
        
        else 
            index.get(indice).put(docId, currenFreq);

        }
    //if( oc != null )
        //System.out.println("token: " + token + oc.toString());
        
        
        
        /*
        if(oc != null){ //Si el token está en el índice
            Integer currentFreq = oc.get(docId);
            if(currentFreq != null) //Y está en el mismo documento
                oc.put(docId, currentFreq++);
            else oc.put(docId, 1); //Si no está en ese documento
        }
        else{ //No está en el índice
            oc = new HashMap();
            oc.put(docId, 1);
            
        }
                */
        
    }
    
    public void printIndex(){
        //System.out.println(index.toString());
        
        
        for (Map.Entry e : index.entrySet()) { 
            HashMap a = (HashMap) e.getValue();
            //if(it != null)
            System.out.println(e.getKey() + " --> " + e.getValue().toString());
        }
       
    }
} //End clas