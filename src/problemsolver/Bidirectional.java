/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author parham
 */
public class Bidirectional extends Solver {

    Queue goalQueue;
    Queue fromQueue;
    protected HashSet<Problem> cli, clg;

    public Bidirectional(boolean isGraph) {
        super(isGraph);
        this.goalQueue = new LinkedList();
        this.fromQueue = new LinkedList();
        cli = new HashSet<>();
        clg = new HashSet<>();
    }

    public void solve(Problem init, Problem goal) {
        fromQueue.add(init);
        goalQueue.add(goal);
        while (doStepForInit() == null && doStepForGoal() == null) {
            maxNodes = Math.max(maxNodes, goalQueue.size() + fromQueue.size());
        }
    }

    public Problem doStepForInit() {
//        if(fromQueue.isEmpty()){
//            return null;
//        }
        Problem node = (Problem) fromQueue.remove();

        if (node.isAnswer()) {
//            printPath(node);
//            return node;
        }
        nodesExpanded++;
        node.generateNodes();
        for (Iterator<Problem> it = node.nodes.iterator(); it.hasNext();) {

            Problem problem = it.next();
            if (isGraph) {
                if (cli.contains(problem)) {
                    nodesSeen++;
                    continue;
                }
            }
            nodesSeen++;
            problem.distanceTraveled = node.distanceTraveled + 1;

            if (goalQueue.contains(problem)) {
                for (Object p : goalQueue) {
                    if (p.equals(problem)) {
                        printPath(problem, (Problem) p);
                    }
                }
                return problem;
            }
            problem.visited = true;
//                System.out.println(problem.distanceTraveled);
            //System.out.println(problem);
            fromQueue.add(problem);
            cli.add(problem);
        }
        return null;
    }

    public Problem doStepForGoal() {
        Problem node = (Problem) goalQueue.remove();

        if (node.isAnswer()) {
//            printPath(node);
//            return node;
        }
        nodesExpanded++;
        node.generateNodes();
        for (Iterator<Problem> it = node.nodes.iterator(); it.hasNext();) {

            Problem problem = it.next();
            if (isGraph) {
                if (clg.contains(problem)) {
                    nodesSeen++;
                    continue;
                }
            }
            problem.distanceTraveled = node.distanceTraveled + 1;

            if (fromQueue.contains(problem)) {
                for (Object p : fromQueue) {
                    if (p.equals(problem)) {
                        printPath(problem, (Problem) p);
                    }
                }
                return problem;
            }
            problem.visited = true;
//                System.out.println(problem.distanceTraveled);
            //System.out.println(problem);
            nodesSeen++;
            goalQueue.add(problem);
            clg.add(problem);
        }

        return null;
    }

    protected void printPath(Problem p0, Problem p1) {

        Stack stack = new Stack();
        System.out.println(p0.distanceTraveled);
        p1.distanceTraveled += p0.distanceTraveled;
        while (p0 != null) {
            stack.push(p0);
            p0 = p0.father;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println("here they met :)");
        printPath(p1);

    }

}
