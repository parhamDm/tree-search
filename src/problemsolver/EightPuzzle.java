/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author parham
 */
public class EightPuzzle extends Problem {

    int[][] puzzle;
    int zeroX, zeroY;
    final int answer[];
    
    public EightPuzzle(int[][] puzzle, int zeroX, int zeroY, Problem father,int [] answer) {
        this.puzzle = puzzle;
        this.zeroX = zeroX;
        this.zeroY = zeroY;
        this.father = father;
        this.answer=answer;
    }

    @Override
    public int h() {
        int incurrect = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((3 * i + j) != puzzle[i][j]) {
                    incurrect++;
                }
            }
        }
        return incurrect + distanceTraveled;
    }

    @Override
    public void generateNodes() {
        nodes = new ArrayList<>();
        if (zeroX != 0) {
            int[][] newP = arrayClone(puzzle);
            newP[zeroX][zeroY] = newP[zeroX - 1][zeroY];
            newP[zeroX - 1][zeroY] = 0;
            nodes.add(new EightPuzzle(newP, zeroX - 1, zeroY, this,answer ));

        }

        if (zeroY != 0) {
            int[][] newP = arrayClone(puzzle);
            newP[zeroX][zeroY] = newP[zeroX][zeroY - 1];
            newP[zeroX][zeroY - 1] = 0;
            nodes.add(new EightPuzzle(newP, zeroX, zeroY - 1, this,answer));
        }

        if (zeroX != 2) {
            int[][] newP = arrayClone(puzzle);
            newP[zeroX][zeroY] = newP[zeroX + 1][zeroY];
            newP[zeroX + 1][zeroY] = 0;
            nodes.add(new EightPuzzle(newP, zeroX + 1, zeroY, this,answer));
        }

        if (zeroY != 2) {
            int[][] newP = arrayClone(puzzle);
            newP[zeroX][zeroY] = newP[zeroX][zeroY + 1];
            newP[zeroX][zeroY + 1] = 0;
            nodes.add(new EightPuzzle(newP, zeroX, zeroY + 1,this,answer));
        }
    }

    @Override
    boolean isAnswer() {
        for(int i=0;i<9;i++){
            if(puzzle[i/3][i%3] != answer[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s.append(puzzle[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String toString(int puzzle[][]) {
        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s.append(puzzle[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int[][] arrayClone(int[][] matrix) {
        int[][] myInt = new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            myInt[i] = matrix[i].clone();
        }
        return myInt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EightPuzzle)) {
            return false;
        }
        EightPuzzle o = (EightPuzzle) obj;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] != o.puzzle[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(puzzle);
    }

}
