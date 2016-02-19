package ctweb;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import datatypes.DataCombination;
import datatypes.DataVariableAndValues;
import handler.handlerCombinationCTWebNewLogic;
import junit.framework.TestCase;

public class TestHandlerAetg {

	static ArrayList<DataVariableAndValues> variableAndValues;

	static ArrayList<DataCombination> combinationsExpected;

	@BeforeClass
	public static void setUpClass() throws Exception {

		variableAndValues = new ArrayList<DataVariableAndValues>();

		combinationsExpected = new ArrayList<DataCombination>();

		List<String> file = Files.readAllLines(Paths.get("C:/Users/Michel/workspace/CTWebLogic/src/test/resources/TestPlayExampleBase.txt"));
		
		// File Format:
		// (Variable Name, (Values,)+\n)+ --at least one value for variable)
		// #
		// --Combination Expected
		// (Value,)+\n)+ -- the number of values is same of number of variables
		// on the first part of the file. The number of lines
		// is related with the Oracle used to calculate the expected result.

		// To separate the two parts of the file, Variable & Values and the
		// Expected Result

		int indexOfMidiumPart = file.indexOf("#");
		if (indexOfMidiumPart == -1)
			throw new Exception("The Test File do not have the separator # beteween the Imput and the Expected Result");

		List<String> firstPart = file.subList(0, indexOfMidiumPart);
		List<String> secondPart = file.subList(file.indexOf("#") + 1, file.size());

		DataVariableAndValues dataVarValues;
		// Read the Variables and Values, it all separated by comma.
		for (String line : firstPart) {
			if (line.contains(",")) {
				String[] splited = line.split(",");
				String variableName = splited[0];
				ArrayList<String> values = new ArrayList<String>();
				for (int i = 1; i < splited.length; i++) {
					String value = splited[i].trim();
					values.add(value);
				}
				dataVarValues = new DataVariableAndValues(variableName, values);
				variableAndValues.add(dataVarValues);
			}
		}
		// Read the Expected Result
		for (String line : secondPart) {
			if (line.contains(",")) {
				combinationsExpected.add(constructDataCombinationFromStringFormatedOnTheLegacyWay(line));
			}
		}

	}

	@Test
	
	public void PlayExampleFromOldCTWeb() {


		ArrayList<DataCombination> combinations = handlerCombinationCTWebNewLogic.getPairWiseCombinationWithSimplePairWise(variableAndValues);

		assertTrue(Arrays.equals(combinationsExpected.toArray(), combinations.toArray()));


	}

	/**
	 * This construct a combination result from this format
	 * {Ludo,Dice,Person,2,Quiz}
	 * 
	 * @param stringCombination
	 * @return DataCombination
	 */
	public static DataCombination constructDataCombinationFromStringFormatedOnTheLegacyWay(String stringCombination) {

		stringCombination = stringCombination.substring(1, stringCombination.indexOf("}"));

		String[] values = stringCombination.split(",");

		ArrayList<String> valuesList = new ArrayList<String>();

		for (int i = 0; i < values.length; i++) {
			valuesList.add(values[i].trim());
		}

		DataCombination combination = new DataCombination(valuesList);

		return combination;
	}

}
