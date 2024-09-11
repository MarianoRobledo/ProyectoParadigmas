/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

/**
 *
 * @author Mariano
 */
public class Submarino extends Nave{
    private final static int MAX_VIDA=3;

    public Submarino() {
        super(MAX_VIDA, true);
    }

    @Override
    public String toString() {
        return "Submarino{" + '}';
    }
    
    
    
}
