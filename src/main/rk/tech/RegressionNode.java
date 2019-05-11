package rk.tech;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class RegressionNode {
	
	public static final Comparator<RegressionNode> comparator = 
		(n1,n2) -> {
			int overlap = Math.min(n1.end, n2.end) - Math.max(n1.start, n2.start);
			return overlap>0?0:n1.end<n2.start?-1:+1;
		};
	
	
	protected Regression regression;
	protected int start;
	protected int end;
	protected RegressionNode parent;
	protected SortedSet<RegressionNode> children;
	
	public RegressionNode() {
		this(new Regression(),0,0);
	}
	
	public RegressionNode(Regression regression, int start, int end) {
		this.regression = regression;
		this.start = start;
		this.end = end;
		this.children = new ConcurrentSkipListSet<RegressionNode>(comparator);
		this.parent = null;
	}
	
	public boolean add(RegressionNode node) {
		if (node.start < this.start)
			throw new IllegalArgumentException("Node start is outside of the parent's range");
		if (node.end > this.end)
			throw new IllegalArgumentException("Node end is outside of the parent's range");

		node.parent = this;
		return this.children.add(node);
	}
	
	public void addAll(Collection<RegressionNode> nodes) {
		nodes.forEach(this::add);
	}

	public void addAll(RegressionNode... nodes) {
		for (RegressionNode node: nodes)
			add(node);
	}

	@Override
	public String toString() {
		return 
			"RegressionNode [regression=" + regression 
			+ ", start=" + start + ", end=" + end + ", children="
			+ children + "]";
	}
	
	public String toString(int indent) {
		//System.out.println("RegressionNode [regression=%s, start=%d, end=%d]\n%"+indent+"s%s,");
		return String.format(
				"RegressionNode [regression=%s, start=%d, end=%d]\n%"+indent+"s%s",
				regression.toString(), start,end,"",children.stream().map(c -> c.toString(indent+2)).collect(Collectors.joining()));
		//
	}
	
}