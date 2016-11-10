/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author sturner
 */
public class Individual {

    Import math;
    private int chromeLen;
    private int[] genes = new int[0];
    private int fitness;
    private ArrayList<Rule> rules = new ArrayList();

    public Individual(int chromeLen) {
        this.chromeLen = chromeLen;
        this.genes = new int[chromeLen];
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public int getChromeLen() {
        return chromeLen;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    //-------------------------------------------------------------------------------------------------------------------------//

    //randomly initialise genes
    public void initGenes() {

        for (int i = 0; i < chromeLen; i++) {
            //Mod the length of a rule + 1 to avoid action bit being anything other than 1 or 0
            if ((i + 1) % 7 == 0) {
                Random r = new Random();
                this.genes[i] = r.nextInt(2);
            } else {
                Random r = new Random();
                int randInt = r.nextInt(3);

                //Chance for wildcard to be introduced.
                if (randInt == 3) {

                    this.genes[i] = 2;
                } else {

                    this.genes[i] = randInt;
                }

            }

        }
        String TESTOUTPUT = "";

        for (int i = 0; i < this.genes.length; i++) {

            if ((i + 1) % 7 == 0) {

                TESTOUTPUT += " ";
                TESTOUTPUT += Integer.toString(this.genes[i]);
                TESTOUTPUT += "\n";

            } else {
                TESTOUTPUT += this.genes[i];

            }

        }
        System.out.println(TESTOUTPUT);

    }

    public void evaluateFitness(ArrayList<Data> data) {

        this.fitness = 0;
        int binStringMatch = 0;

        int matchCount = 0;

        //loop rules
        //loop each data point
        for (int i = 0; i < data.size(); i++) {

            //check data against each rule
            for (int k = 0; k < rules.size(); k++) {
                binStringMatch = 0;

                //compate bits of rule and data
                for (int l = 0; l < rules.get(k).getCondition().length; l++) {

                    if (data.get(i).getVariable()[l] == rules.get(k).getCondition()[l] || rules.get(k).getCondition()[l] == 2) {

                        binStringMatch++;

                    } else {

                        break;
                    }

                }

                if (binStringMatch == 6) {
                    if (data.get(i).getOutput() == rules.get(k).getOutput()) {

                        this.fitness++;
                        break;

                    } else {
                        break;
                    }
                }

            }

        }

    }

    //gene broken down into 10 objects containing an array 5 bits long and an output of 1 or 0  
    public void creatRules() {
        //to ensure that the rules list never grows greater than 10, used when saving the best of each generation.
        if (this.rules.size() > 0) {

            this.rules.clear();

        }

        for (int i = 0; i < genes.length - 6; i += 7) {
            int[] cond = Arrays.copyOfRange(genes, i, i + 6);
            int out = genes[i + 6];
            Rule r = new Rule(cond, out);

            this.rules.add(r);

        }

    }

}
