package com.network.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.network.model.Node;
import com.network.utils.AppConstants;

public class NetworkService {

	private Map<String, ArrayList<Node>> networkMap = null;
	private Map<String, Node> nodesMap = new HashMap<String, Node>();

	public NetworkService() {
		this.networkMap = new HashMap<String, ArrayList<Node>>();
		this.nodesMap = new HashMap<String, Node>();
	}

	/**
	 * Add a node to the network
	 * 
	 * @param nodeName, nodeType
	 * @return message
	 */
	public String addNodeToNetwork(String nodeName, String nodeType) {
		Node node = new Node(nodeName, nodeType);
		// Check for duplicates
		if (networkMap.containsKey(nodeName))
			return AppConstants.ERROR_THAT_NAME_ALREADY_EXISTS;
		// Add Node to the network
		networkMap.put(nodeName, new ArrayList<Node>());
		nodesMap.put(nodeName, node);

		return String.format(AppConstants.SUCCESS_SUCCESSFULLY_ADDED_NODE, nodeName);
	}

	/**
	 * Connect two nodes in the network
	 * 
	 * @param nodeName1, nodeName2
	 * @return message
	 */
	public String connectNodes(String nodeName1, String nodeName2) {
		Node node1 = this.nodesMap.get(nodeName1);
		Node node2 = this.nodesMap.get(nodeName2);
		// Check for existing connection
		ArrayList<Node> node1Network = this.networkMap.get(nodeName1);
		if (node1Network.contains(node2)) {
			return AppConstants.ERROR_DEVICES_ALREADY_CONNECTED;
		}
		// Bi directional network connection
		node1Network.add(node2);
		this.networkMap.get(nodeName2).add(node1);

		return AppConstants.SUCCESS_SUCCESSFULLY_CONNECTED_NODE;
	}

	/**
	 * Sets device strength in the network
	 * 
	 * @param nodeName1, nodeName2
	 * @return message
	 */
	public String setDeviceStrength(String nodeName, Integer strength) {
		Node node = this.nodesMap.get(nodeName);
		// Check for existing connection
		ArrayList<Node> nodeNetwork = this.networkMap.get(nodeName);
		Node nodeDetails = this.nodesMap.get(nodeName);
		if (!nodeNetwork.contains(node)) {
			return AppConstants.ERROR_NODE_DOESNOT_EXIST;
		}
		if (nodeDetails.getType().equals("Repeater"))
			return AppConstants.ERROR_CANNOT_SET_STRENGTH_TO_REPEATER;
		nodeDetails.setStrength(strength);

		return AppConstants.SUCCESS_SUCCESSFULLY_DEFINED_STRENGTH;
	}

	public String findInfoRoute(String nodeName1, String nodeName2) {
		List<Node> connections1 = this.networkMap.get(nodeName1);
		List<Node> connections2 = this.networkMap.get(nodeName2);

		// Return if no Direct Routes found
		if (connections1.isEmpty() || connections2.isEmpty()) {
			return AppConstants.ERROR_ROUTE_NOT_FOUND;
		}

		Node startNode = this.nodesMap.get(nodeName1);
		Node endNode = this.nodesMap.get(nodeName2);

		if ("Repeater".equals(startNode.getType()) || "Repeater".equals(endNode.getType())) {
			return AppConstants.ERROR_ROUTE_CANNOT_BE_CALCULATED;
		}

		return null;
	}

}
