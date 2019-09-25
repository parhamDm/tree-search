/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author parham
 */
public class AStar extends UCS {

    public AStar(boolean isGraph) {
        super(isGraph);
    }
    
    
    @Override
    protected Problem removeDeserve(ArrayList<Problem> list) {

        int deserve = Integer.MAX_VALUE;
        Problem p = null;
        for (Iterator<Problem> iterator = list.iterator(); iterator.hasNext();) {
            Problem next = iterator.next();
            int hx=next.h();
            if (hx < deserve) {
                deserve = hx;
                p = next;
            }
        }
        System.out.println(p.h());

        list.remove(p);
        return p;
    }

}
