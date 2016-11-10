/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomputation;

/**
 *
 * @author sturner
 */
public class Classification {

    private int[] variable;
    private int output;

    public Classification(int[] variable) {
        this.variable = variable;

    }

    public void setOutput(int output) {
        this.output = output;
    }

    public int[] getVariable() {
        return variable;
    }

    public int getOutput() {
        return output;
    }

}
