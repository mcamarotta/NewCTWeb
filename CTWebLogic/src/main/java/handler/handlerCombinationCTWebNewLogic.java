package handler;

import datatypes.DataVariableAndValues;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

import core.Algorithm;
import core.Combination;
import core.SetCustom;
import core.algorithm.AETGAlgorithm;
import ctweb.ParametersLoader;
import datatypes.DataCombination;

/**
 * @author Michel Camarotta This class works like a interface between logic of
 *         pairwise algorithms and other layer which wants to use some algorithm
 * 
 *         Here there are different methods to invoke the different algorithms
 *         which have different results of pairwise sets.
 */
public final class handlerCombinationCTWebNewLogic {

//	private static ArrayList<DataVariableAndValues> variableAndValues;

	/**
	 * This method receive a set of variables with different values and combine
	 * them with algorithm SimplePairWise. Return back a pairwise combination of
	 * variables received by parameter
	 * 
	 * @param variableAndValues
	 * @return DataPairCombination
	 */
	/**
	 * @param algorithmName
	 * @param variableAndValues
	 * @return
	 */
	public static ArrayList<DataCombination> getPairWiseCombinationWithSimplePairWise(ArrayList<DataVariableAndValues> variableAndValues) {

		int howManyColums= variableAndValues.size();
		String algorithmName="aetg";
		
		//The algorithm come from the front-end, and define how to calculate the combinations
		Algorithm a = getAlgorithm(algorithmName);
		
		//To return the results
		ArrayList<DataCombination> combinations = new ArrayList<DataCombination>();
		
		//To transform the new set of variable to the structure that legacy code can understand
		Vector<SetCustom> sSets = ParametersLoader.loadSets(variableAndValues);

		//Preparing data as the legacy code did it
		for (SetCustom set : sSets) {
			a.add(set);
		}
		
		// This comment for who knows the old code: In this implementation we don't need to iterate, because always we will do one time.
		a.clear();
		a.buildCombinations();
		
		//This line brings the combination done by the algorithm
		Vector<Combination> resOldWayCombinationsToTranform = a.getSelectedCombinations();
		
		//To transform the results brought by the legacy code to the new structure to return to the up layer which consume this data. 
		combinations =  transformaOldWayCombinationToNewOne(howManyColums,resOldWayCombinationsToTranform);

	
		return combinations;

	}

	
	
	private static ArrayList<DataCombination> transformaOldWayCombinationToNewOne(int howManyColums, Vector<Combination> resOldWayCombinationsToTranform) {
		
		ArrayList<DataCombination> resNewWayCombination = new ArrayList<DataCombination>(); 
		
		
		//I need the combination by combination transforming the elements that it has to the new datatype 
		//Which will be interpreted by the Rest Service to send back the information.
		for (Combination oldWayCombination : resOldWayCombinationsToTranform) {
			DataCombination combinationNewWay = new DataCombination(); 
			for (int i = 0; i < howManyColums; i++) {
				//The old combination has a set of values. This values are stored on Strings.
				//The only thing that I need to do is iterate and copy the values to the new way, more simple. 
				combinationNewWay.addToValueToCombination(oldWayCombination.getElements()[i].getValue().toString());
			}
			resNewWayCombination.add(combinationNewWay);
			
//			if (a instanceof PROWAlgorithm) {
////				PROWAlgorithm aa=(PROWAlgorithm) a;
////				out.print("; sel. factor=" + aa.getSelectionFactor(cont-1));
////			}
		}
		return resNewWayCombination;

	}

	private static Algorithm getAlgorithm(String algorithmName) {
		if (algorithmName.equals("allCombinations"))
			return null;//new AllCombinationsAlgorithm();
		if (algorithmName.equals("eachChoice"))
			return null;//EachChoiceAlgorithm();
				// if (algorithmName.equals("antirandom"))
				// return new AntirandomAlgorithm();
				// if (algorithmName.equals("comb"))
				// return new CombAlgorithm();
				// if (algorithmName.equals("genetic"))
				// return new GeneticAlgorithm();
				// if (algorithmName.equals("pairwise"))
				// return new PairwiseAlgorithm();
		if (algorithmName.equals("aetg"))
			return new AETGAlgorithm();
		// if (algorithmName.equals("customizableaetg"))
		// return new
		// edu.uclm.combTest.core.customizableaetg.CustomizableAETGAlgorithm();
		if (algorithmName.equals("prow"))
			return null;//new PROWAlgorithm();
			// if (algorithmName.equals("customizablepw"))
			// return new CustomizablePairwiseAlgorithm();
			// if (algorithmName.equals("bacteriologic"))
			// return new BacteriologicAlgorithm();
		return new AETGAlgorithm();
	}

}
