/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parham
 */
public class RubicsCube extends Problem implements Cloneable {

    Side top, front, buttom, back, left, right;
    Side faces[] = new Side[6];
    String lastMove;

    public RubicsCube(Side top, Side front, Side buttom, Side back, Side left, Side right) {
        this.top = top;
        this.front = front;
        this.buttom = buttom;
        this.back = back;
        this.left = left;
        this.right = right;
        lastMove = "";
        updateFaces();
    }

    public void updateFaces() {
        faces = new Side[6];
        faces[0] = top;
        faces[1] = front;
        faces[2] = buttom;
        faces[3] = back;
        faces[4] = left;
        faces[5] = right;
    }

    Problem rotateF(RubicsCube p) {

        p.front = new Side(front.face[2], front.face[0], front.face[3], front.face[1]);
        p.left = new Side(left.face[0], buttom.face[0], left.face[2], buttom.face[1]);
        p.right = new Side(top.face[2], right.face[1], top.face[3], right.face[3]);
        p.buttom = new Side(right.face[2], right.face[0], buttom.face[2], buttom.face[3]);
        p.top = new Side(top.face[0], top.face[1], left.face[3], left.face[1]);
        p.father = this;
        p.lastMove = "F";
        return p;
    }

    Problem rotateFC(RubicsCube p) {
        p.front = new Side(front.face[1], front.face[3], front.face[0], front.face[2]);
        p.left = new Side(left.face[0], top.face[3], left.face[2], top.face[2]);
        p.right = new Side(buttom.face[1], right.face[1], buttom.face[0], right.face[3]);
        p.buttom = new Side(left.face[1], left.face[3], buttom.face[2], buttom.face[3]);
        p.top = new Side(top.face[0], top.face[1], right.face[0], right.face[2]);
        p.father = this;
        p.lastMove = "FC";

        return p;
    }

    Problem rotateR(RubicsCube p) {
        p.right = new Side(right.face[2], right.face[0], right.face[3], right.face[1]);
        p.front = new Side(front.face[0], buttom.face[1], front.face[2], buttom.face[3]);

        p.buttom = new Side(buttom.face[0], back.face[1], buttom.face[2], back.face[3]);
        p.back = new Side(back.face[0], top.face[1], back.face[2], top.face[3]);
        p.top = new Side(top.face[0], front.face[1], top.face[2], front.face[3]);
        p.father = this;
        p.lastMove = "R";

        return p;
    }

    Problem rotateRC(RubicsCube p) {

        p.right = new Side(right.face[1], right.face[3], right.face[0], right.face[2]);
        p.front = new Side(front.face[0], top.face[1], front.face[2], top.face[3]);
        p.buttom = new Side(buttom.face[0], front.face[1], buttom.face[2], front.face[3]);
        p.back = new Side(back.face[0], buttom.face[1], back.face[2], buttom.face[3]);
        p.top = new Side(top.face[0], back.face[1], top.face[2], back.face[3]);
        p.father = this;
        p.lastMove = "RC";
        return p;
    }

    Problem rotateTC(RubicsCube p) {

        p.top = new Side(top.face[1], top.face[3], top.face[0], top.face[2]);
        p.front = new Side(left.face[0], left.face[1], front.face[2], front.face[3]);
        p.left = new Side(back.face[3], back.face[2], left.face[2], left.face[3]);
        p.right = new Side(front.face[0], front.face[1], right.face[2], right.face[3]);
        p.back = new Side(back.face[0], back.face[1], right.face[1], right.face[0]);
        p.father = this;
        p.lastMove = "TC";
        return p;

    }

    Problem rotateT(RubicsCube p) {
        p.top = new Side(top.face[2], top.face[0], top.face[3], top.face[1]);
        p.front = new Side(right.face[0], right.face[1], front.face[2], front.face[3]);
        p.left = new Side(front.face[0], front.face[1], left.face[2], left.face[3]);
        p.right = new Side(back.face[3], back.face[2], right.face[2], right.face[3]);
        p.back = new Side(back.face[0], back.face[1], left.face[1], left.face[0]);
        p.father = this;
        p.lastMove = "T";
        return p;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printAncestors() {
        RubicsCube p = this;
        Stack s = new Stack();
        while (p != null) {
            s.push(p);
            p = (RubicsCube) p.father;
        }
        while (!s.isEmpty()) {
            p = (RubicsCube) s.pop();
            System.out.print(p.lastMove + " ");
        }
        System.out.println("");
    }

    @Override
    public void generateNodes() {
        nodes = new ArrayList<>();
        RubicsCube p[] = new RubicsCube[6];
        try {
            for (int i = 0; i < 6; i++) {
                p[i] = (RubicsCube) this.clone();
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RubicsCube.class.getName()).log(Level.SEVERE, null, ex);
        }
        rotateF(p[0]);
        rotateFC(p[1]);
        rotateRC(p[3]);
        rotateR(p[2]);
        rotateTC(p[4]);
        rotateT(p[5]);
        for (int i = 0; i < 6; i++) {
            p[i].updateFaces();
            nodes.add(p[i]);
        }
    }

    @Override
    boolean isAnswer() {
        for (int i = 0; i < 6; i++) {
            if (!faces[i].areSame()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Head:\n" + top.toString() + front.toString()
                + buttom.toString() + back.toString()
                + left.toString() + right.toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RubicsCube)) {
            return false;
        }

        RubicsCube o = (RubicsCube) obj;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.top);
        hash = 17 * hash + Objects.hashCode(this.front);
        hash = 17 * hash + Objects.hashCode(this.buttom);
        hash = 17 * hash + Objects.hashCode(this.back);
        hash = 17 * hash + Objects.hashCode(this.left);
        hash = 17 * hash + Objects.hashCode(this.right);
        return hash;
    }
}
