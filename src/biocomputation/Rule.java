/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

/**
 *
 * @author sturner
 */
public class Rule {

    private int[] condition;
    private int output;
    private int ruleFitness;

    public int getRuleFitness() {
        return ruleFitness;
    }

    public void setRuleFitness(int ruleFitness) {
        this.ruleFitness = ruleFitness;
    }

    public Rule(int[] condition, int output) {
        this.condition = condition;
        this.output = output;
    }

    public int[] getCondition() {
        return condition;
    }

    public int getOutput() {
        return output;
    }

    public void incFitness() {

        this.ruleFitness++;

    }

}
