package rk.tech;

import java.util.Arrays;

import junit.framework.TestCase;

public class RegressionNodeTest extends TestCase {

	public void testRegressionNode() {
		
		new RegressionNode(new Regression(0,1,0,0),0,1);
	}

	public void testAdd() {
		RegressionNode root = new RegressionNode(new Regression(0,1,0,0),0,1);
		root.add(new RegressionNode(new Regression(0,1,0,0),0,1));
	}

	public void testAddAllCollectionOfRegressionNode() {
		RegressionNode root = new RegressionNode(new Regression(0,1,0,0),0,1);
		root.addAll(Arrays.asList(
				new RegressionNode(new Regression(0,1,0,0),0,1),
				new RegressionNode(new Regression(0,1,0,0),0,1)
				));
		System.out.println(root);
	}

	public void testAddAllRegressionNodeArray() {
		RegressionNode root = new RegressionNode(new Regression(0,1,0,0),0,1);
		root.addAll(
			new RegressionNode(new Regression(0,1,0,0),0,1),
			new RegressionNode(new Regression(0,1,0,0),0,1)
		);
		
		System.out.println(root);
	}
}
