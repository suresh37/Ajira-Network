package com.network;

import java.util.Scanner;

import com.network.service.NetworkService;
import com.network.utils.AppConstants;

public class NetworkMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("Enter Option: ");
				System.out.println("1.Add Node");
				System.out.println("2.Add Connection");
				System.out.println("3.Info Route");
				System.out.println("4.Set Strength");
				System.out.println("5.Exit");
				Integer option = Integer.parseInt(sc.next());
				NetworkService networkService = new NetworkService();
				String node1 = null, node2 = null;
				switch (option) {
				case 1:
					System.out.println("Enter node name and type");
					String name = sc.next();
					String type = sc.next();
					networkService.addNodeToNetwork(name, type);
					break;
				case 2:
					System.out.println("Enter node 1 and Node 2 Names");
					node1 = sc.next();
					node2 = sc.next();
					networkService.connectNodes(node1, node2);
					break;
				case 3:
					System.out.println("Enter node 1 and Node 2 Names");
					node1 = sc.next();
					node2 = sc.next();
					networkService.findInfoRoute(node1, node2);
					break;
				case 4:
					// setDeviceStrength();
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println(AppConstants.ERROR_INVALID_COMMAND);
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();
		}
	}
}
