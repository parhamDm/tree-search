/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author parham
 */
public class ProblemSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
//(int eaterFrom, int humanFrom, int eaterGoal, int humanGoal) {

//        HumanEaterProblem hep=new HumanEaterProblem(3, 3, 0, 0, false, null);
//        HumanEaterProblem goal=new HumanEaterProblem(0, 0, 3, 3, true, null);
//        System.out.println(hep);
//        System.out.println(hep.nodes);
        Bidirectional a = new Bidirectional(true);
//        a.solve(hep,goal);
        
 //       System.exit(0);

        //        Robot aa=new Robot(100, 100, 12, 1, "");
        //        System.out.println(aa.h());
        //        Solver a=new AStar(true);
        //        a.solve();
        //        BFSSolver a =new BFSSolver(false);
        //        a.bfs();
//        int[][] multi = new int[][]{
//            {1, 4, 2},
//            {6, 0, 5},
//            {7, 3, 8},};
//        int[][] multi0 = new int[][]{
//            {0, 1, 2},
//            {3, 4, 5},
//            {6, 7, 8},};
//        int[] ans = new int[]{
//            0, 1, 2, 3, 4, 5, 6, 7, 8
//        //        1,2,3,4,5,6,7,8,0
//        };
//        int[] ans1 = new int[]{
//            5, 1, 8, 2, 0, 3, 7, 6, 4
//        };
//        EightPuzzle init = new EightPuzzle(multi, 1, 1, null, ans);
//        EightPuzzle goal = new EightPuzzle(multi0, 0, 0, null, ans1);
//        Bidirectional a = new Bidirectional(false);
//        a.solve(init, goal);
        RubicsCube r;
//        r = new RubicsCube(
//                new Side('g', 'b', 'w', 'o'),
//                new Side('o', 'y', 'w', 'b'),
//                new Side('b', 'y', 'w', 'g'),
//                new Side('r', 'y', 'w', 'y'),
//                new Side('o', 'b', 'g', 'r'),
//                new Side('g', 'r', 'o', 'r')
//        );
//                r = new RubicsCube(
//                        new Side('y', 'b', 'y', 'b'),
//                        new Side('g', 'y', 'g', 'y'),
//                        new Side('w', 'g', 'w', 'g'),
//                        new Side('b', 'w', 'b', 'w'),
//                        new Side('r', 'r', 'r', 'r'),
//                        new Side('o', 'o', 'o', 'o')
//                );
//        r = new RubicsCube(
//                new Side('r', 'o', 'w', 'y'),
//                new Side('o', 'g', 'r', 'b'),
//                new Side('w', 'r', 'o', 'g'),
//                new Side('y', 'r', 'g', 'w'),
//                new Side('o', 'b', 'b', 'b'),
//                new Side('o', 'g', 'y', 'w')
//        );
        //        Solver a = new BFS(true);
        //                a.setDepthAllowed(14);
        //        a.solve(r);
        ;
//        System.out.println(r.rotateRC((RubicsCube) r.clone()));
//        System.exit(0);
//        System.exit(0);
        Scanner scan = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("choose question: \nrobot(1)\n8puzzle(2)\nrubic cube(3)");
            int question = scan.nextInt();
            switch (question) {
                case 1:
                    doForRobot(scan);
                    break;
                case 2:
                    dofor8Puzzle(scan);
                    break;
                case 3:
                    doForRubic(scan);
                    break;
            }
        }
    }

    private static void doForRobot(Scanner s) {
        System.out.println("write input:\n");
        int dx = s.nextInt();
        int dy = s.nextInt();
        int walls = s.nextInt();
        ArrayList<Invalid> ar = new ArrayList();
        for (int i = 0; i < walls; i++) {
            boolean add;
            add = ar.add(new Invalid(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()));
        }
        Robot problem = new Robot(dx, dy, 1, 1, null, ar);
        Robot goal = new Robot(dx, dy, 5, 5, null, ar);
        startSearching(problem, goal, s);

    }

    private static void dofor8Puzzle(Scanner s) {
        System.out.println("write input:\n");
        int[] goal = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[][] goalState = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},};
        int start[][] = new int[3][3];
        int startAsTarget[] = new int[9];
        for (int i = 0; i < 3; i++) {
            start[i][0] = s.nextInt();
            start[i][1] = s.nextInt();
            start[i][2] = s.nextInt();
            startAsTarget[3 * i + 0] = start[i][0];
            startAsTarget[3 * i + 1] = start[i][1];
            startAsTarget[3 * i + 2] = start[i][2];
        }
        int helperI = 0, helperJ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (start[i][j] == 0) {
                    helperI = i;
                    helperJ = j;
                }
            }
        }
        EightPuzzle problem = new EightPuzzle(start, helperI, helperJ, null, goal);
        EightPuzzle goalp = new EightPuzzle(goalState, 0, 0, null, startAsTarget);
        System.out.println(problem + " " + helperI + " " + helperI);
        startSearching(problem, goalp, s);
    }

    private static void startSearching(Problem problem, Problem goal, Scanner s) {
        System.out.println("search type:\n"
                + "UCS(1)\n"
                + "DFS(2)\n"
                + "Bidirectional(3)\n"
                + "A*(4)\n");
        int searchType = s.nextInt();
        System.out.println("tree(1)\n"
                + "graph(2)\n");
        int treeOrGraph = s.nextInt();
        boolean isGraph = false;
        if (treeOrGraph == 2) {
            isGraph = true;
        }

        switch (searchType) {
            case 1:
                Solver solve = new UCS(isGraph);
                solve.solve(problem);
                break;
            case 2:
                solve = new DFS(true);
                solve.solve(problem);
                break;
            case 3:
                Bidirectional bi = new Bidirectional(isGraph);
                bi.solve(problem, goal);
                break;
            case 4:
                solve = new AStar(isGraph);
                solve.solve(problem);
                break;
        }
        System.exit(0);
    }

    private static void doForRubic(Scanner s) {
        System.out.println("write input:\n");

        RubicsCube problem = new RubicsCube(
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0)),
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0)),
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0)),
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0)),
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0)),
                new Side(s.next().charAt(0), s.next().charAt(0), s.next().charAt(0), s.next().charAt(0))
        );
        System.out.println(problem);
        System.out.println("search type:\n"
                + "BFS(1)\n"
                + "DFS(2)\n"
                + "DFS increasing lenth(3)");
        int searchType = s.nextInt();
        System.out.println("tree(1)\n"
                + "graph(2)\n");
        int treeOrGraph = s.nextInt();
        boolean isGraph = false;
        if (treeOrGraph == 2) {
            isGraph = true;
        }
        switch (searchType) {
            case 1:
                Solver solve = new BFS(isGraph);
                solve.solve(problem);
                break;
            case 2:
                DFS slve = new DFS(isGraph);
                slve.setDepthAllowed(14);
                slve.solve(problem);
                break;
            case 3:
                solve = new DFSIncreasingLenth(isGraph);
                solve.solve(problem);
                break;
        }
        System.exit(0);
    }
}
