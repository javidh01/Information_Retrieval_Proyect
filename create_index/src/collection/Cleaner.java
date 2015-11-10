/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

/**
 * Clase que elimina las palabras vacías
 * de un documento
 * @author javi
 */
public class Cleaner {
    private final Map<String, Object> spanish = new HashMap<String, Object>();
    
    /**
     * Constructor por defecto
     */
    public Cleaner(){
        File spWords = new File("clean/palabras_vacias.txt");
        
        addWords(spanish, spWords);
    }
    
    /**
     * Elimina palabras vacías de una lista de tokens
     * @param tokens 
     */
    public void removeSpStopWords(List<String> tokens){
        removeWords(tokens, spanish);
    }
    
    /**
     * Elimina un token de una lista, 
     * si este ya aparece en el map
     * @param tokens
     * @param map 
     */
    public void removeWords(List<String> tokens, Map map){
        for(Iterator<String> iterator = tokens.iterator(); iterator.hasNext();){
            String token = iterator.next();
            if(map.containsKey(token)) iterator.remove();
        }
    }
    
    /**
     * 
     * @param spanish
     * @param spWords 
     */
    private void addWords(Map map, File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file); //Leemos el archivo
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //assert fileReader != null;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null)
                map.put(line, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileReader.close(); //Cerramos el lector
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
