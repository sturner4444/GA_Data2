/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

/**
 *
 * @author sturner
 */
public class Data {

    private int[] variable;
    private int output;

    public Data(int[] variable, int output) {
        this.variable = variable;
        this.output = output;
    }

    public int[] getVariable() {
        return variable;
    }

    public int getOutput() {
        return output;
    }

}
