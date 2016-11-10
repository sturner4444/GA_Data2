/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package biocomputation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class evolutionaryMain {

    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {

        int genCount = 0;
        int generations = 2000;
        int geneLength = 35;
        int populationSize = 100;
        int bestGen = 0;
        float muteRate = (float) 0.02;
        float xoverRate = (float) 0.9;

        Population p = new Population(populationSize, geneLength);
        Individual best = new Individual(geneLength);
        PrintWriter bestText = new PrintWriter("best.txt", "utf-8");
        PrintWriter meanText = new PrintWriter("mean.txt", "utf-8");
//------------------------------------------------------------------------------------------------------------------------------------------------------\\

        //randomly create new population
        p.populateData();
        p.initialise();

        //keep the best of the previous generation.
        while (best.getFitness() < 41) {

            if (genCount > 0) {
                p.getPopulation().get(p.getR().nextInt(populationSize));
                p.getPopulation().add(best);
            }

            //Tournament selection creates a new offspring arrayList
            p.selection();

            //crosses over selected individuals and places them into a new arrayList this is based on the xover rate
            for (int i = 0; i < p.getMaxPop() / 2; i++) {

                p.crossOver(p.getOffspring().get(i), p.getOffspring().get(p.getMaxPop() - 1 - i), xoverRate);

            }

            //a chance for mutation on each bit of each individual.
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.mutation(p.getCrossed().get(i), muteRate);

            }

            //population and offspring cleared. the crossed list contained the mutated and xovered individuals
            p.getPopulation().clear();
            p.getOffspring().clear();

            //the manipulated individuals are placed into the population array for the next generation.
            for (int i = 0; i < p.getCrossed().size(); i++) {
                p.getPopulation().add(i, p.getCrossed().get(i));

            }
            //crossed list cleared for next generations crossover.
            p.getCrossed().clear();
            genCount++;

            //Best individual is saved to be placed into the next generation
            for (int i = 0; i < p.getPopulation().size(); i++) {

                if (p.getPopulation().get(i).getFitness() > best.getFitness()) {

                    best.setGenes(p.getPopulation().get(i).getGenes());
                    best.setRules(p.getPopulation().get(i).getRules());
                    best.evaluateFitness(p.getDataList());

                    bestGen = genCount;

                }

            }

//Text file related------------------------------------------------------------------------------------------------------------------------------
            System.out.println("Generation; " + genCount);
            System.out.println(best.getFitness());
            bestText.print(best.getFitness());
            bestText.append("\n");
            int totalFitness = 0;
            int fitMean = 0;

            for (Individual population : p.getPopulation()) {
                totalFitness += population.getFitness();
                //     System.out.println(population.fitness);
            }

            fitMean = (totalFitness / p.getMaxPop());
            meanText.print(fitMean);
            meanText.append("\n");

            System.out.println("Mean: " + fitMean);

        }

        bestText.close();
        meanText.close();
//-----------------------------------------------------------------------------------------------------------------------------------------

        //serperate 0 firing rules from 1 firing rules
        Set<Rule> bestSet = new HashSet<>(best.getRules());
        Set<Rule> zero = bestSet.stream().filter(rule -> rule.getOutput() == 0).collect(Collectors.toSet());
        Set<Rule> one = bestSet.stream().filter(rule -> rule.getOutput() == 1).collect(Collectors.toSet());
        
        //get the model rules into an indvidual
        Individual modelIndividual = new Individual(geneLength);
        modelIndividual.getRules().addAll(one);

        one.forEach(rule -> {
            System.out.println(Arrays.toString(rule.getCondition()) + Integer.toString(rule.getOutput()) + "\n");
        });

        System.out.println();

        zero.forEach(rule -> {
            System.out.println(Arrays.toString(rule.getCondition()) + Integer.toString(rule.getOutput()) + "\n");
        });

//-------------classification--------------This should be delt with by class....----------------------------------------------------------------------------------------------
        String sc;
        BufferedReader br = new BufferedReader(new FileReader("dataToClassify"));
        ArrayList<Classification> newData = new ArrayList();
        ArrayList<Rule> classificationRules = new ArrayList();
        //parse data to be calsified and get it into arrays
        while ((sc = br.readLine()) != null) {

            String[] split = sc.split(" ");

            int[] ruleEnconding = new int[6];
            int out = 0;

            for (int i = 0; i < 6; i++) {

                ruleEnconding[i] = Character.getNumericValue(split[0].charAt(i));
            }

            Classification temp = new Classification(ruleEnconding);
            newData.add(temp);
            //  System.out.println("READ DATA SIZE: " + dataList.size());
        }
        //check the GA hasnt failed to create the model
        if (one.size() > 1) {

            //each data to be classified
            for (Classification newData1 : newData) {
                //each rule in the model
                for (Rule rule : modelIndividual.getRules()) {

                    int matches = 0;
                    //each bit in data and model rule
                    for (int i = 0; i < rule.getCondition().length; i++) {
                        if (newData1.getVariable()[i] == rule.getCondition()[i] || rule.getCondition()[i] == 2) {
                            matches++;
                        } else {
                            break;
                        }
                        if (matches == 6) {
                            //all data initlialsed with 0 set to 1 if model dictates
                            newData1.setOutput(1);
                            break;

                        }

                    }

                }

            }
        }

        if (modelIndividual.getRules().size() == 4) {

            System.out.println("adaw");
        };
    }
}
