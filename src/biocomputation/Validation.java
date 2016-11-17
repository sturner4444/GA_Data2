/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomputation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sd2-turner
 */
public class Validation {

    public Validation() {
    }

    public int validate(Individual ind) throws IOException {
        ArrayList<Data> newData = createValidationData();
        int correctValidations = 0;
        //each data to be classified
        for (Data newData1 : newData) {
            //each rule in the model
            for (Rule rule : ind.getRules()) {

                int matches = 0;
                //each bit in data and model rule
                for (int i = 0; i < rule.getCondition().length; i++) {
                    if (newData1.getVariable()[i] == rule.getCondition()[i] || rule.getCondition()[i] == 2) {
                        matches++;
                    } else {
                        break;
                    }
                    if (matches == 6) {
                        if (newData1.getOutput() == rule.getOutput()) {
                            correctValidations++;
                            break;
                        }
                    } 

                }

            }

        }

        return correctValidations;
    }

    private ArrayList<Data> createValidationData() throws FileNotFoundException, IOException {

        String sc;
        BufferedReader br = new BufferedReader(new FileReader("validationData"));
        ArrayList<Data> newData = new ArrayList();
        //parse data to be calsified and get it into arrays
        while ((sc = br.readLine()) != null) {

            String[] split = sc.split(" ");

            int[] ruleEnconding = new int[6];
            int out;

            for (int i = 0; i < 6; i++) {

                ruleEnconding[i] = Character.getNumericValue(split[0].charAt(i));
            }
            out = Character.getNumericValue(split[1].charAt(0));
            Data temp = new Data(ruleEnconding, out);
            newData.add(temp);
            //  System.out.println("READ DATA SIZE: " + dataList.size());
        }
        return newData;
    }

}
