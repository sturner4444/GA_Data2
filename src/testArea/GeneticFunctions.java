/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
/*
package testArea;

import biocomputation.Individual;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sturner
 */
/*
public class GeneticFunctions {

    Random r = new Random();
    ArrayList<Individual> population;
    ArrayList<Individual> offspring;

    public GeneticFunctions(ArrayList<Individual> population, ArrayList<Individual> offspring) {
        this.population = population;
        this.offspring = offspring;

    }

    public void selection() {

        for (int i = 0; i < population.size(); i++) {

            int geneInit[] = new int[population.get(1).getChromeLen()];
            Random r = new Random(population.size());
            Individual evalOne = new Individual(0, geneInit);
            Individual evalTwo = new Individual(0, geneInit);
            int evalCount = 0;

            evalOne = population.get(r.nextInt(population.size()));
            evalTwo = population.get(r.nextInt(population.size()));

            if (evalOne.fitness > evalTwo.fitness) {
                offspring.add(evalOne);
            } else {
                offspring.add(evalTwo);
            }
        }

    }

}
*/