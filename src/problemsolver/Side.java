/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

import java.util.Arrays;

/**
 *
 * @author parham
 */
public class Side implements Cloneable {

    char[] face = new char[4];

    public Side(char first, char second, char third, char forth) {
        face = new char[]{
            first, second, third, forth
        };
    }

    @Override
    public String toString() {
        return face[0] + " " + face[1] + "\n"
                + face[2] + " " + face[3] + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Side)) {
            return false;
        }
        Side o = (Side) obj;
        for (int i = 0; i < 3; i++) {
            if(face[i]!=o.face[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(face);
    }
    public boolean areSame() {
        return (face[0]==face[1])&&(face[1]==face[2])&&(face[2]==face[3]);
    }

}
