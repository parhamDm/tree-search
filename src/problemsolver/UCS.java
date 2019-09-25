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
public class UCS extends Solver {

    public UCS(boolean isGraph) {
        super(isGraph);
    }

    @Override
    public void solve(Problem p) {
        ArrayList<Problem> list = new ArrayList<>();
        list.add(p);
        closeList.add(p);
        nodesSeen++;

        p.visited = true;
        while (!list.isEmpty()) {
            Problem node = removeDeserve(list);
            if (node.isAnswer()) {
                printPath(node);
                return;
            }
            nodesExpanded++;
            node.generateNodes();
            for (Problem problem : node.nodes) {
                if (isGraph) {
                    if (closeList.contains(problem)) {
                        nodesSeen++;
                        continue;
                    }
                } else {
                    closeList.add(problem);
                }
                nodesSeen++;
                closeList.add(problem);
                problem.distanceTraveled = node.distanceTraveled + 1;
//                System.out.println(nodesSeen);
                list.add(problem);
            }
            maxNodes = Math.max(maxNodes, list.size());
        }
    }

    protected Problem removeDeserve(ArrayList<Problem> list) {

        int deserve = Integer.MAX_VALUE;
        Problem p = null;
        for (Iterator<Problem> iterator = list.iterator(); iterator.hasNext();) {
            Problem next = iterator.next();
            if (next.distanceTraveled < deserve) {
                deserve = next.distanceTraveled;
                p = next;
            }
        }
//        System.out.println(p.distanceTraveled);

        list.remove(p);
        return p;
    }

}
