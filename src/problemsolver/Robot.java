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
public class Robot extends Problem {

    public int n, m, currX, currY;
    public ArrayList<Invalid> cantGo;

    public Robot(int n, int m, int currX, int currY,Problem  father,ArrayList<Invalid> arr) {
        this.n = n;
        this.m = m;
        this.currX = currX;
        this.currY = currY;
        this.cantGo = arr;
//        cantGo = new ArrayList<>();
//        cantGo.add(new Invalid(3, 2, 4, 2));
//        cantGo.add(new Invalid(3, 3, 4, 3));
//        cantGo.add(new Invalid(2, 3, 2, 4));
//        cantGo.add(new Invalid(3, 3, 3, 4));
//        cantGo.add(new Invalid(3, 1, 4, 1));
//        cantGo.add(new Invalid(4, 4, 4, 5));
        this.father=father;
    }

    @Override
    public void generateNodes() {
        if (currX == 3 && currY == 3) {
        }
        nodes = new ArrayList<>();
        if (!((currX == n) || cantGo.contains(new Invalid(currX, currY, currX + 1, currY)))) {
            nodes.add(new Robot(n, m, currX + 1, currY,this,cantGo));
        }

        if (!(currY == m || cantGo.contains(new Invalid(currX, currY, currX, currY + 1)))) {
            nodes.add(new Robot(n, m, currX, currY + 1,this,cantGo));
        }
        
        if (!(currX == 1 || cantGo.contains(new Invalid(currX-1, currY, currX, currY)))) {
            nodes.add(new Robot(n, m, currX - 1, currY,this,cantGo));
        }
        
        if (!(currY == 1 || cantGo.contains(new Invalid(currX, currY-1, currX, currY)))) {
            nodes.add(new Robot(n, m, currX, currY - 1,this,cantGo));
        }
    }   

    @Override
    boolean isAnswer() {
        return currX == m && currY==m;
    }

    @Override
    public String toString() {
        return currX + " " + currY;
    }

    @Override
    public boolean equals(Object obj) {
           // If the object is compared with itself then return true  
            if (obj == this) {
                return true;
            }
              
            /* Check if o is an instance of Complex or not
              "null instanceof [type]" also returns false */
            if (!(obj instanceof Robot)) {
                return false;
            }

            // typecast o to Complex so that we can compare data members 
            Robot c = (Robot) obj;
            // Compare the data members and return accordingly 
            return ((currX == c.currX) & (currY == c.currY) & (m == c.m) & (n == c.n));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.n;
        hash = 89 * hash + this.m;
        hash = 89 * hash + this.currX;
        hash = 89 * hash + this.currY;
        return hash;
    }
    @Override
    public int h(){
        return m-currX+n-currY+distanceTraveled;
    }
    

}
