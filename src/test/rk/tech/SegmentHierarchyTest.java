package rk.tech;

import java.util.stream.IntStream;

import org.apache.commons.math3.util.Pair;

import junit.framework.TestCase;

public class SegmentHierarchyTest extends TestCase {

	public void testAdd() {
		
		
		SegmentHierarchy seg = new SegmentHierarchy();
		int npoints = 10000;
		
		
		IntStream
			.range(0, npoints).mapToDouble(j -> j*2*Math.PI*npoints/100)
			.mapToObj(x -> Pair.create(x,Math.sin(x)))
			.forEach(xy -> seg.add(xy.getKey(), xy.getValue()));
		
		System.out.println(seg.size());
		
		System.out.println(seg);
		
	}
	
	
}
