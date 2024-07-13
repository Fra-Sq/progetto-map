/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sangiovannesi
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>();

    /**
     *
     * @return
     */
    public List<AdvObject> getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    /**
     *
     * @param o
     */
    public void add(AdvObject o) {
        list.add(o);
    }

    /**
     *
     * @param o
     */
    public void remove(AdvObject o) {
        list.remove(o);
    }
}
