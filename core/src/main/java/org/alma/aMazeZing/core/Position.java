package org.alma.aMazeZing.core;

public class Position implements Comparable<Position>{
	public int x,y;

	public Position(int i, int j){
		x=i;
		y=j;
	}

	@Override
	public int compareTo(Position o){
		int cmp = x - o.x;
		if (cmp!=0)
			return cmp;
		return y - o.y;
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof Position)
			return compareTo((Position)o) ==0;
		return false;
	}
}