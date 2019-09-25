/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;

/**
 *
 * @author parham
 */

public abstract class Problem {

    public ArrayList<Problem> nodes;
    boolean visited;
    public int distanceTraveled = 0;
    public Problem father = null;
    public int nodesSeen=0;
    public int nodesExpanded=0;
    public int maxNodes=0;
    
    boolean isAnswer() {
        return false;
    }

    ;
    public void generateNodes() {
    }

    
    public int h() {
        return 0;
    }
    
    public void printAncestors(){
        Problem p =this;
        while(p!=null){
            System.out.println(p);
            p=p.father;
        }
    }
}
