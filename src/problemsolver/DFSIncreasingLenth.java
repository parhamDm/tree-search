/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

/**
 *
 * @author parham
 */
public class DFSIncreasingLenth extends Solver{

    public DFSIncreasingLenth(boolean isGraph) {
        super(isGraph);
    }
    

    @Override
    public void solve(Problem p){
        int limit = 2;
        DFS solver=new DFS(true);
        while(!solver.gotAnAnswer){
            int nodeMem=solver.maxNodes;
            int nodeexp=solver.nodesExpanded;
            int seen=solver.nodesSeen;
            
            solver=new DFS(isGraph);
            solver.maxNodes+=nodeMem;
            solver.nodesExpanded+=nodeexp;
            solver.nodesSeen+=seen;
            solver.setDepthAllowed(limit);
            solver.solve(p);
            limit++;
        }
    }
}
