/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

/**
 *
 * @author Mariano
 */
public class Lancha extends Nave{
    private final static int MAX_VIDA=1;

    public Lancha() {
        super(MAX_VIDA, false);
    }

    @Override
    public String toString() {
        return "Lancha{" + '}';
    }
    
    
    
}
