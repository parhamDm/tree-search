/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author parham
 */
public class BFS extends Solver {

    public BFS(boolean isGraph) {
        super(isGraph);
    }

    @Override
    public void solve(Problem p) {
        // BFS uses Queue data structure
        Queue queue = new LinkedList();
        queue.add(p);
        closeList.add(p);

        while (!queue.isEmpty()) {

            Problem node = (Problem) queue.remove();
            if (node.isAnswer()) {
                printPath(node);
                return;
            }
//            System.out.println(node);
            node.generateNodes();
            nodesExpanded++;
            for (Iterator<Problem> it = node.nodes.iterator(); it.hasNext();) {
                nodesSeen++;
                Problem problem = it.next();
                if (closeList.contains(problem)) {
                    continue;
                }
                problem.visited = true;
                problem.distanceTraveled = node.distanceTraveled + 1;
//                System.out.println(problem.distanceTraveled);
//                System.out.println(problem);
                queue.add(problem);
                closeList.add(problem);
            }
            maxNodes = Math.max(maxNodes, queue.size());
        }
        
    }
}
