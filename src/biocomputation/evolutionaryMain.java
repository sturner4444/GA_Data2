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
        float muteRate = (float) 0.001;
        float xoverRate = (float) 0.1;

        Population p = new Population(populationSize, geneLength);
        Individual best = new Individual(geneLength);
        PrintWriter bestText = new PrintWriter("best.txt", "utf-8");
        PrintWriter meanText = new PrintWriter("mean.txt", "utf-8");
        Validation validation = new Validation();
//------------------------------------------------------------------------------------------------------------------------------------------------------\\

        //randomly create new population
        p.populateData();
        p.initialise();
        p.populationFitnessEval();

        //keep the best of the previous generation.
        while (genCount < generations) {

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

            p.crossedFitnessEval();

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

        }//WHILE GENERATIONS

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
        int correctValidations = validation.validate(best);

        System.out.println("Correctly Validated unseen data: " + correctValidations);
//-------------classification--------------This should be delt with by class....----------------------------------------------------------------------------------------------
        //check the GA hasnt failed to create the model
    }
}
