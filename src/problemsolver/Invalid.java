/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolver;

/**
 *
 * @author parham
 */
public class Invalid {

    int fx, fy, tx, ty;

    public Invalid(int fx, int fy, int tx, int ty) {
        this.fx = fx;
        this.fy = fy;
        this.tx = tx;
        this.ty = ty;
    }

    @Override
    public String toString() {
        return fx + " " + fy + " " + tx + " " + ty;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
              "null instanceof [type]" also returns false */
        if (!(o instanceof Invalid)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members 
        Invalid c = (Invalid) o;

        // Compare the data members and return accordingly 
        return ((fx == c.fx) & (fy == c.fy) & (tx == c.tx) & (ty == c.ty));
    }

}
