/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.impl.Window;

/**
 *
 * @author pierpaolo
 */
public interface GameObservable {
    
    /**
     *
     * @param o
     */
    public void attach(GameObserver o);
    
    /**
     *
     * @param o
     */
    public void detach(GameObserver o);
    
    /**
     *
     * @param window */
    public void notifyObservers(Window window);
    
}
