/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sangiovannesi
 */
public class AdvObjectContainer extends AdvObject {

    private List<AdvObject> list = new ArrayList<>();
    private boolean open;

    /**
     *
     * @param id
     */
    public AdvObjectContainer(int id) {
        super(id);
    }

    /**
     *
     * @param id
     * @param name
     */
    public AdvObjectContainer(int id, String name) {
        super(id, name);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     */
    public AdvObjectContainer(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

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
    

    @Override
    public boolean isOpen() {
        return open;
    }

    /**
     *
     * @param open
     */
    @Override
    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean setOpen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
