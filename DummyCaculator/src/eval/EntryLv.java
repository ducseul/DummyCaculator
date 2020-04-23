/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval;

/**
 *
 * @author hacke
 */
public class EntryLv {

    int level;
    int index;

    public EntryLv(int level, int index) {
        this.level = level;
        this.index = index;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Entry{" + "level=" + level + ", index=" + index + '}';
    }

}
