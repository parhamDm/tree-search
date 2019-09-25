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
public class HumanEaterProblem extends Problem {

    int eaterFrom;
    int humanFrom;
    int eaterGoal;
    int humanGoal;
    boolean IsBoatInGoal;
//    HumanEaterProblem father;

    public HumanEaterProblem(int eaterFrom, int humanFrom, int eaterGoal, int humanGoal, boolean IsBoatInGoal, HumanEaterProblem father) {
        this.eaterFrom = eaterFrom;
        this.humanFrom = humanFrom;
        this.eaterGoal = eaterGoal;
        this.humanGoal = humanGoal;
        this.IsBoatInGoal = IsBoatInGoal;
        this.father = father;
    }

    @Override
    public void printAncestors() {
        Problem p =this;
        while(p!=null){
            System.out.println(p);
            p=p.father;
        }
    }

    @Override
    public void generateNodes() {
        nodes = new ArrayList<Problem>();
        if (IsBoatInGoal) {
            //1 human
            if (humanGoal >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom, humanFrom + 1, eaterGoal, humanGoal - 1, !IsBoatInGoal, this);
                addNode(hep);
            }
            //1 eater
            if (eaterGoal >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom + 1, humanFrom, eaterGoal - 1, humanGoal, !IsBoatInGoal, this);
                addNode(hep);
            }
            //2 human
            if (humanGoal >= 2) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom, humanFrom + 2, eaterGoal, humanGoal - 2, !IsBoatInGoal, this);
                addNode(hep);

            }
            //2 eater
            if (eaterGoal >= 2) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom + 2, humanFrom, eaterGoal - 2, humanGoal, !IsBoatInGoal, this);
                addNode(hep);
            }
            //1 human 1 eater
            if (eaterGoal >= 1 && humanGoal >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom + 1, humanFrom + 1, eaterGoal - 1, humanGoal - 1, !IsBoatInGoal, this);
                addNode(hep);
            }
        } else {
            //1 human
            if (humanFrom >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom, humanFrom-1, eaterGoal, humanGoal+1, !IsBoatInGoal, this);
                addNode(hep);
            }
            //1 eater
            if (eaterFrom >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom - 1, humanFrom, eaterGoal + 1, humanGoal, !IsBoatInGoal, this);
                addNode(hep);
            }
            //2 human
            if (humanFrom >= 2) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom, humanFrom  - 2, eaterGoal, humanGoal + 2, !IsBoatInGoal, this);
                addNode(hep);

            }
            //2 eater
            if (eaterFrom >= 2) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom - 2, humanFrom, eaterGoal + 2, humanGoal, !IsBoatInGoal, this);
                addNode(hep);
            }
            //1 human 1 eater
            if (eaterFrom >= 1 && humanFrom >= 1) {
                HumanEaterProblem hep;
                hep = new HumanEaterProblem(eaterFrom - 1, humanFrom - 1, eaterGoal + 1, humanGoal + 1, !IsBoatInGoal, this);
                addNode(hep);
            }
        }

    }

    @Override
    boolean isAnswer() {
        return eaterGoal == 3 && humanGoal == 3;
    }

    @Override
    public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
              
            /* Check if o is an instance of Complex or not
              "null instanceof [type]" also returns false */
            if (!(obj instanceof HumanEaterProblem)) {
                return false;
            }

            // typecast o to Complex so that we can compare data members 
            HumanEaterProblem c = (HumanEaterProblem) obj;
            // Compare the data members and return accordingly 
            return (eaterFrom == c.eaterFrom) & (humanFrom == c.humanFrom) & 
                    (eaterGoal == c.eaterGoal) & (humanGoal == c.humanGoal) &
                    (IsBoatInGoal==IsBoatInGoal);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String s="";
        s="human from side: "+humanFrom+"\n";
        s+="eater from side: "+eaterFrom+"\n";
        s+="human goal side: "+humanGoal+"\n";
        s+="eater goal side: "+eaterGoal+"\n";
        s+="is in goal: "+IsBoatInGoal+"\n";
        return s;
    }
    

    public boolean isValidState(HumanEaterProblem p) {
        if (((p.eaterGoal > p.humanGoal) && p.humanGoal !=0) || 
                ((p.humanFrom < p.eaterFrom) && p.humanFrom !=0)) {
            return false;
        }
        
        return true;
    }

    ;

    private void addNode(HumanEaterProblem hep) {
        if (isValidState(hep)) {
            nodes.add(hep);
        }
    }

}
