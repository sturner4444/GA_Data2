/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 
package testArea;

import biocomputation.Individual;
import java.util.ArrayList;
import java.util.Random;

public class Biocomputation {

    /**
     * @param args the command line arguments
     */
/*
    public static void main(String[] args) {

        final int maxPop = 50;
        final int individualLen = 15;
        final int[] geneInit = new int[individualLen];
        final int selections = 50;

        ArrayList<Individual> population = new ArrayList<Individual>();
        ArrayList<Individual> offspring = new ArrayList<Individual>();
        //Generate original population
        for (int i = 0; i < maxPop; i++) {
            Individual e = new Individual(individualLen, geneInit);

            e.setGenes();
            e.evaluateFitness();
            population.add(e);
        }

        for (int i = 0; i < population.size(); i++) {
            Random r = new Random(population.size());
            Individual evalOne = new Individual(0, geneInit);
            Individual evalTwo = new Individual(0, geneInit);

            evalOne = population.get(r.nextInt(population.size()));
            evalTwo = population.get(r.nextInt(population.size()));

            if (evalOne.fitness > evalTwo.fitness) {
                offspring.add(evalOne);
            } else {
                offspring.add(evalTwo);
            }

        }

        //print individual population
        for (int i = 0; i < maxPop; i++) {
            //Print fintess of original population fitness. 
            System.out.println("Individual " + i + ":" + " " + population.get(i).getFitness());

        }
        System.out.println("Population: " + population.size());
        System.out.println("Offspring Size: " + offspring.size());

    }

}
*/