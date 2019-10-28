/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessingapplication;

/**
 * A template for defining a  compression algorithm
 * @author sai
 */

interface Compressor<G,T> {
    
    G compress(T entity);
    
}
