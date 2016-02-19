package core.algorithm;

import core.Algorithm;

public class AETGTest extends AlgorithmTest {

	@Override
	protected Algorithm createAlgorithm() {
		return new AETGAlgorithm();
	}

	@Override
	protected void postTest(Algorithm algorithm) {
		assertTrue(algorithm.evaluateGoodness()==1);
	}

	public void test2() {}


}
