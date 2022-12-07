package aocDay7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Day7 {


	static class Node {
		Node parent;
		String name;
		int size;
		List<Node> children = new ArrayList<>();

		public Node(String name) {
			this.name = name;
		}

		int getRecursiveSize() {
			return children.stream().mapToInt(n -> {
				if (n.children.isEmpty()) {
					return n.size;
				} else {
					return n.getRecursiveSize();
				}
			}).sum();
		}
	}

	static Node currentDir;
	static Node root = new Node("/");
	static List<Node> dirs = new ArrayList<>();
	
	static void interpreteCommand(String command) {
		if (command.contains("cd /")) {
			currentDir = root;
		} else if (command.startsWith("$ cd ..")) {
			currentDir = currentDir.parent;
		} else if (command.startsWith("$ cd")) {
			String dest = command.split(" ")[2];
			Optional<Node> child = currentDir.children.stream().filter(n -> n.name.equals(dest)).findFirst();
			if (child.isPresent()) {
				currentDir = child.get();
			}
		} else if (command.startsWith("dir")) {
			Node node = new Node(command.split(" ")[1]);
			node.parent = currentDir;
			currentDir.children.add(node);
			dirs.add(node);
		} else if (Character.isDigit(command.charAt(0))) { // Create file ex : 72637 test.txt
			String[] nodeData = command.split(" ");
			Node n = new Node(nodeData[1]);
			n.parent = currentDir;
			n.size = Integer.parseInt(nodeData[0]);
			currentDir.children.add(n);
		} else {

		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Path inputPath = Paths.get("src/aocDay7/input_day7.txt");
        String input = Files.readString(inputPath);

		currentDir = root;
		dirs.add(root);

        String[] commands = input.split("\r\n");
        for (String command : commands) {
			interpreteCommand(command);
		}

		System.out.println(dirs.stream()
				.mapToInt(Node::getRecursiveSize)
				.filter(i -> i < 100_000)
				.sum());

		int totalSpace = 70_000_000;
		int requiredUnusedSpace = 30_000_000;
		int maxStorageForUpdate = totalSpace - requiredUnusedSpace;
		int totalUsedSpace = root.getRecursiveSize();
		int needToFreeSpace = totalUsedSpace - maxStorageForUpdate;

		Optional<Node> lowestNodeRequiredForDeletion = dirs.stream()
				.filter(d -> d.getRecursiveSize() > needToFreeSpace)
				.sorted(Comparator.comparingInt(Node::getRecursiveSize))
				.findFirst();

		if (lowestNodeRequiredForDeletion.isPresent()) {
			Node delete = lowestNodeRequiredForDeletion.get();
			System.out.println("Need to delete : " + delete.name + ", space : " + delete.getRecursiveSize());
		}
	}
}