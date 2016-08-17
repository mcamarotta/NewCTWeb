package uy.com.abstracta.ctweb.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datatypes.DataCombination;
import datatypes.DataVariableAndValues;
import handler.handlerCombinationCTWebNewLogic;

@Path("services")
public class CTWebServices {

	
	@GET
    @Path("getSimplePairWise")
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public  ArrayList<DataCombination> getSimplePairWiseCombination() {
        
    	ArrayList<DataVariableAndValues> variableAndValues = new ArrayList<DataVariableAndValues>();
    	
    	ArrayList<String> values1= new ArrayList<>();
    	values1.add("ValueVar11");
    	values1.add("ValueVar12");
    	DataVariableAndValues var1= new DataVariableAndValues("Var1",values1 );
    	variableAndValues.add(var1);
    	
    	ArrayList<String> values2= new ArrayList<>();
    	values2.add("ValueVar21");
    	values2.add("ValueVar22");
    	DataVariableAndValues var2= new DataVariableAndValues("Var1",values2 );
    	
    	variableAndValues.add(var2);
    	
    	
    	ArrayList<DataCombination> combinationResponse = handlerCombinationCTWebNewLogic.getPairWiseCombinationWithSimplePairWise(variableAndValues);
    	
    	return combinationResponse;
    }
	
	@GET
    @Path("testJSONVariableAndValues")
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public  ArrayList<DataVariableAndValues> test() {
        
    	ArrayList<DataVariableAndValues> variableAndValues = new ArrayList<DataVariableAndValues>();
    	
    	ArrayList<String> values1= new ArrayList<>();
    	values1.add("ValueVar11");
    	values1.add("ValueVar12");
    	DataVariableAndValues var1= new DataVariableAndValues("Var1",values1 );
    	variableAndValues.add(var1);
    	
    	ArrayList<String> values2= new ArrayList<>();
    	values2.add("ValueVar21");
    	values2.add("ValueVar22");
    	DataVariableAndValues var2= new DataVariableAndValues("Var1",values2 );
    	
    	variableAndValues.add(var2);
    	
    	
    	ArrayList<DataCombination> combinationResponse = handlerCombinationCTWebNewLogic.getPairWiseCombinationWithSimplePairWise(variableAndValues);
    	
    	return variableAndValues;
    }
	
	@GET
    @Path("testJSONVariableAndValuesPassing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  ArrayList<DataCombination> test2(ArrayList<DataVariableAndValues> variableAndValues) {
        
//    	ArrayList<DataVariableAndValues> variableAndValues = new ArrayList<DataVariableAndValues>();
    	
//    	ArrayList<String> values1= new ArrayList<>();
//    	values1.add("ValueVar11");
//    	values1.add("ValueVar12");
//    	DataVariableAndValues var1= new DataVariableAndValues("Var1",values1 );
//    	variableAndValues.add(var1);
//    	
//    	ArrayList<String> values2= new ArrayList<>();
//    	values2.add("ValueVar21");
//    	values2.add("ValueVar22");
//    	DataVariableAndValues var2= new DataVariableAndValues("Var1",values2 );
//    	
//    	variableAndValues.add(var2);
    	
    	
    	ArrayList<DataCombination> combinationResponse = handlerCombinationCTWebNewLogic.getPairWiseCombinationWithSimplePairWise(variableAndValues);
    	
    	return combinationResponse;
    }
}
