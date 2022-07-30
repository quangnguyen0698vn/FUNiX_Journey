package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

	private List<Node> neighbors;
	
	public Node() {
		this.neighbors = new ArrayList<>();
	}
	
	public List<Node> getNeighbors() {
		return Collections.unmodifiableList(neighbors);
	}
}
