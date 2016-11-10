/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
/*package biocomputation;

public class runTest {

    /**
     * @param args the command line arguments
     */
   //S public static void main(String[] args) {
        //instantiate population
        /*
        Population p = new Population(50, 15);
        GeneticFunctions g = new GeneticFunctions(p.getPopulation(), p.getOffspring()); //create initial population
        p.initialise();
        g.selection();
        p.getOffspring().equals(g.offspring);
        //Everything from here onwards will be looped until appropriate solution is foundS
        System.out.println("Population FItness " + p.popFitness());
        System.out.println("Offspring fitness " + p.offFitness());

        String test = "";
        int[] testChromeOne = new int[15];
        int[] testChromeTwo = new int[15];

        for (int i = 0; i < 15; i++) {
            testChromeOne[i] = 1;

            if (i % 2 == 0) {
                testChromeTwo[i] = 1;

            } else {
                testChromeTwo[i] = 0;
            }

        }

        int head = 0;
        int tail = 15;
        int[] newIndividualOne = new int[15];
        int[] newIndividualTwo = new int[15];
        for (int i = 0; i < 15; i++) {

            if (i < 6) {
                newIndividualOne[i] = testChromeOne[i];
                newIndividualTwo[i] = testChromeTwo[i];

            } else {

                newIndividualOne[i] = testChromeTwo[i];
                newIndividualTwo[i] = testChromeOne[i];

            }

        }
        String binOne = "";
        String binTwo = "";
        for (int i = 0; i < 15; i++) {
            binOne += newIndividualOne[i];
            binTwo += newIndividualTwo[i];

        }
        // System.out.println(binOne);
        // System.out.println(binTwo);
        int top = p.maxPop - 1;
        for (int i = 0; i < p.maxPop / 2; i++) {

            p.crossOver(p.getOffspring().get(i), p.getOffspring().get(top));
            //System.out.println(p.getOffspring().get(i).getFitness());
            //System.out.println(p.getOffspring().get(top).getFitness());
            top--;

        }

        for (int i = 0; i < p.maxPop - 1; i++) {

            System.out.println("OFFSPRING FITNESS: " + p.offspring.get(i).getFitness());

        }
    }

}*/
