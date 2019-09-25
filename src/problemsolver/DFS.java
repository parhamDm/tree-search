/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author parham
 */
public class DFS extends Solver {

    private int depthAllowed;
    boolean gotAnAnswer;

    public DFS(boolean isGraph) {
        super(isGraph);
        this.gotAnAnswer = false;
        this.depthAllowed = Integer.MAX_VALUE;
    }

    public void setDepthAllowed(int depthAllowed) {
        this.depthAllowed = depthAllowed;
    }

    @Override
    public void solve(Problem p) {

        Stack stack = new Stack();
        stack.push(p);
        closeList.add(p);

        while (!stack.isEmpty()) {

            Problem node = (Problem) stack.pop();
            if (node.isAnswer()) {
                gotAnAnswer = true;
                printPath(node);
                return;
            }
            if (node.distanceTraveled == depthAllowed) {
                continue;
            }
            node.generateNodes();
            nodesExpanded++;

            for (Iterator<Problem> it = node.nodes.iterator(); it.hasNext();) {
                nodesSeen++;
                Problem problem = it.next();
                if (isGraph && closeList.contains(problem)) {
                    continue;
                }

                problem.distanceTraveled = node.distanceTraveled + 1;

                stack.push(problem);
                closeList.add(problem);
            }
            maxNodes = Math.max(maxNodes, stack.size());

        }
    }
}
