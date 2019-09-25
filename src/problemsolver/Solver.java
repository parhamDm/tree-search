/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author parham
 */
public abstract class Solver {

    protected boolean isGraph;
    protected HashSet<Problem> closeList;
    public int nodesSeen=0;
    public int nodesExpanded=0;
    public int maxNodes=0;
    
    public Solver(boolean isGraph) {
        this.isGraph = isGraph;
        closeList = new HashSet<Problem>();
    }

    void solve(Problem p) {

    }

    protected void printPath(Problem p) {
        p.printAncestors();
        System.out.println("# of nodes seen : "+nodesSeen);
        System.out.println("# of expanded nodes : "+nodesExpanded);
        System.out.println("max nodes saved : "+maxNodes);
        System.out.println("distance traveled : "+p.distanceTraveled);
    }
}
