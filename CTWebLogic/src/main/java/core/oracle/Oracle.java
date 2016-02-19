package core.oracle;

import java.util.Vector;

import core.Element;





public abstract class Oracle {
	protected Vector<ParameterValues> parametersValues;
	protected String oracleExpression;
	protected String description;
	protected boolean isOtherWiseOracle;

	public Oracle() {
		this.parametersValues=new Vector<ParameterValues>();
		this.oracleExpression="";
		this.isOtherWiseOracle=false;
	}

	public Vector<ParameterValues> getParametersValues() {
		return this.parametersValues;
	}
	
	public abstract String getOracleExpression(Element[] elements) throws Exception;
	
	public void addParameterValues(ParameterValues pv) {
		this.parametersValues.add(pv);
	}

	public void setOracleExpression(String oracleExpression) {
		this.oracleExpression = oracleExpression;
	}

	public void setParameterValues(Vector<ParameterValues> parametersValues) {
		this.parametersValues=parametersValues;
	}

	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setIsOtherWiseOracle(boolean isOtherWiseOracle) {
		this.isOtherWiseOracle=isOtherWiseOracle;
	}
	
	public boolean getIsOtherWiseOracle() {
		return this.isOtherWiseOracle;
	}
}
