/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sturner
 */
public class Population {

    private ArrayList<Individual> population = new ArrayList();
    private ArrayList<Individual> offspring = new ArrayList();
    private ArrayList<Individual> crossed = new ArrayList();
    private int chromeLen;
    private int maxPop;
    private Random r = new Random();
    private ArrayList<Data> dataList = new ArrayList();

    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public void populateData() throws FileNotFoundException, IOException {
        String sc;
        BufferedReader br = new BufferedReader(new FileReader("data2.txt"));

        while ((sc = br.readLine()) != null) {

            String[] split = sc.split(" ");

            int[] ruleEnconding = new int[6];
            int out;

            for (int i = 0; i < 6; i++) {

                ruleEnconding[i] = Character.getNumericValue(split[0].charAt(i));
            }
            out = Character.getNumericValue(split[1].charAt(0));
            Data temp = new Data(ruleEnconding, out);
            dataList.add(temp);
            //  System.out.println("READ DATA SIZE: " + dataList.size());
        }

    }

    //constructor
    public Population(int maxPop, int chromeLen) {
        this.chromeLen = chromeLen;
        this.maxPop = maxPop;

    }

    public ArrayList<Individual> getCrossed() {
        return crossed;
    }

    public int getMaxPop() {
        return maxPop;
    }

    public Random getR() {
        return r;
    }

    //create population
    public void initialise() throws IOException {

        for (int i = 0; i < maxPop; i++) {

            Individual e = new Individual(chromeLen);
            e.initGenes();

            e.evaluateFitness(dataList);
            e.creatRules();
            population.add(e);
            //System.out.println("NEW GENE FITNESS: " + population.get(i).fitness);
        }

    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }

    public int popFitness() {
        int totalFitness = 0;
        for (int i = 0; i < population.size(); i++) {

            totalFitness += population.get(i).getFitness();

        }
        return totalFitness;
    }

    public int countFitness(ArrayList<Individual> list) {
        int totalFitness = 0;
        for (int i = 0; i < list.size(); i++) {

            totalFitness += list.get(i).getFitness();

        }
        return totalFitness;
    }

    public ArrayList<Individual> getOffspring() {
        return offspring;
    }

    public int getChromeLen() {
        return chromeLen;
    }

    //Takes in two individials and creates two new indiviuals by switching tails (new individuals enter a new list)
    public void crossOver(Individual x, Individual y, float xoverProb) {

        int[] genes = new int[chromeLen];
        //empty individuals that will conain outcome of xover
        Individual newIndividualOne = new Individual(chromeLen);
        Individual newIndividualTwo = new Individual(chromeLen);

        int[] newArrayOne = new int[chromeLen];
        int[] newArrayTwo = new int[chromeLen];
        int crossPoint = r.nextInt(chromeLen);
        float prob = r.nextFloat();

        if (prob < xoverProb) {

            for (int i = 0; i < chromeLen; i++) {

                int temp = 0;
                //copy values up to crossover point

                if (i < crossPoint) {
                    temp = x.getGenes()[i];
                    newIndividualOne.getGenes()[i] = temp;
                    temp = y.getGenes()[i];
                    newIndividualTwo.getGenes()[i] = temp;
                    //copy values from crossover point to length of gene into new individual x to y and y to x
                } else {
                    temp = x.getGenes()[i];
                    newIndividualTwo.getGenes()[i] = temp;
                    temp = y.getGenes()[i];
                    newIndividualOne.getGenes()[i] = temp;

                }

            }
            crossed.add(newIndividualOne);
            crossed.add(newIndividualTwo);
            newIndividualOne.creatRules();
            newIndividualTwo.creatRules();
            newIndividualOne.evaluateFitness(dataList);
            newIndividualTwo.evaluateFitness(dataList);

        } else {

            for (int i = 0; i < chromeLen; i++) {
                int temp = 0;
                temp = x.getGenes()[i];
                newIndividualOne.getGenes()[i] = temp;
                temp = y.getGenes()[i];
                newIndividualTwo.getGenes()[i] = temp;

            }

            crossed.add(newIndividualOne);
            crossed.add(newIndividualTwo);
            newIndividualOne.evaluateFitness(dataList);
            newIndividualTwo.evaluateFitness(dataList);

        }
        //probably not the best place for this but it works, this is the call to the function where the genes are split and placed into new array in fomat of XXXXX Y
        newIndividualOne.creatRules();
        newIndividualTwo.creatRules();

    }

    public void selection() {
        int geneInit[] = new int[population.get(1).getChromeLen()];
        Random r = new Random();
        Individual evalOne = new Individual(0);
        Individual evalTwo = new Individual(0);
        for (int i = 0; i < population.size(); i++) {

            evalOne = population.get(r.nextInt(population.size()));
            evalTwo = population.get(r.nextInt(population.size()));
            //Adds selected individs to offspring TESTED WORKING
            if (evalOne.getFitness() > evalTwo.getFitness()) {
                offspring.add(evalOne);
                //displays the values in the Population and then in Offspring
                // System.out.println("EVAL ONE " + evalOne.fitness);
                //System.out.println("Offspring " + i +" "+ offspring.get(i).fitness);

            } else {
                offspring.add(evalTwo);

            }
        }

    }

    public void mutation(Individual ind, float muteRate) {
        Random r = new Random();
        for (int i = 0; i < ind.getChromeLen(); i++) {
            float prob = r.nextFloat();

            if (prob < muteRate) {

                if ((i + 1) % 7 == 0) {

                    if (ind.getGenes()[i] == 1) {

                        ind.getGenes()[i] = 0;

                    } else if (ind.getGenes()[i] == 0) {

                        ind.getGenes()[i] = 1;
                    }

                } else {
                    int newBit;

                    while ((newBit = r.nextInt(3)) == ind.getGenes()[i]) {

                    }

                    ind.getGenes()[i] = newBit;
                }
            }

        }
    }

}
