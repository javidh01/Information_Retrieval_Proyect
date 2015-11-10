/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

/**
 *
 * 
 * @author javi
 * @param <S>
 * @param <T>
 */   
    
public class Pair<S, T>{
    private S first; //Primer miembro del pair
    private T second; //Segundo miembro del pair
    
    /**
     * Constructor que inicializa 
     * un par de elementos
     * @param first
     * @param second 
     */
    public Pair(S first, T second){
        this.first = first;
        this.second = second;
    }
    
    /**
     * Permite cambiar el valor
     * del primer elemento del par
     * @param first 
     */
    public void setFirst(S first){
        this.first = first;
    }

    /**
     * Permite cambiar el valor
     * del segundo elemento del par
     * @param second 
     */
    public void setSecond(T second) {
        this.second = second;
    }

    /**
     * Devuelve el primer elemento
     * del par
     * @return 
     */
    public S getFirst() {
     return this.first;
    }
    
    /**
     * Devuelve el segundo elemento
     * del par
     * @return 
     */
    public T getSecond() {
     return this.second;
    }   
    
}
