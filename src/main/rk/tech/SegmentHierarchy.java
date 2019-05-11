package rk.tech;

import java.util.ArrayList;
import java.util.Optional;
import java.util.SortedSet;

public class SegmentHierarchy {

	private static final int MIN_DATA_LEN = 10;
	private static final int MIN_CHILD_CNT = 4;
	
	ArrayList<Double> X;
	ArrayList<Double> Y;
	RegressionNode root;
	int last;
	
	public SegmentHierarchy() {
		this.X = new ArrayList<Double>();
		this.Y = new ArrayList<Double>();
		this.root = new RegressionNode();
		this.last = 0;
	}

	public void add(double x, double y) {
		
		X.add(x);
		Y.add(y);
		
		if (X.size()-root.end < MIN_DATA_LEN)
			return;
	
		root.end = X.size();
		increment(root);
	}
	
	public int size() {
		return X.size();
	}
	
	@Override
	public String toString() {
		return "SegmentHierarchy [size(X)=" + X.size() + ", size(Y)=" + Y.size() + ", root=" + root.toString(2) + "]";
	}

	private RegressionNode findLast(RegressionNode root) {
		if (root.children.size()==0)
			return root;
		
		return findLast(root.children.last());
	}
	
	
	private void increment(RegressionNode parent) {
		
		// try add depth
		if (parent.children.isEmpty()) {
			if (parent.end - parent.start >= MIN_DATA_LEN) {
				Regression r = new Regression();
				System.arraycopy(parent.regression.k, 0, r.k, 0, 2);
				System.arraycopy(parent.regression.e, 0, r.e, 0, 2);
				
				RegressionNode n = new RegressionNode(r,parent.start,parent.end);
				parent.add(n);
			}
			else return;
		}
		
		// try merge children
		if (parent.children.size()>=MIN_CHILD_CNT) {
			Optional<RegressionNode> mergedOptional = merge(parent.children);
			if (mergedOptional.isPresent()) {
				RegressionNode merged = mergedOptional.get();
				merged.children.addAll(parent.children);
				parent.children.clear();
				parent.children.add(merged);
			}
		}
		
		RegressionNode child = parent.children.last();
		
		if (child.end == parent.end)
			return;
		
		Regression r0 = child.regression;
		Regression r = Regression.fit(X,Y,child.start,parent.end);

		if (r.e[0] - r0.e[0] <= 0) {
			
			child.regression = r;
			child.end = parent.end;
		}
		else {

			// otherwise, make new one

			child = new RegressionNode(r,child.end,parent.end);
			parent.add(child);
		}
		
		increment(child);
	}
	
	private Optional<RegressionNode> merge(SortedSet<RegressionNode> nodes ) {

		if (nodes.isEmpty())
			return Optional.empty();
		
		int start = nodes.first().start;
		int end = nodes.last().end;
		Regression r = Regression.fit(X,Y,start,end);
		return Optional.of(new RegressionNode(r,start,end));
	}
}
