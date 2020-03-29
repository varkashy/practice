import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ToDo {

	public static void main(String[] args) {
		List<String> thingsToDo = new ArrayList<>();
		thingsToDo.add("Binary Tree Traversal");
		thingsToDo.add("Deletion in binary tree");
		thingsToDo.add("Insertion in binary tree");
		thingsToDo.add("DFS");
		thingsToDo.add("BFS");
		thingsToDo.add("Search Insert delete in Trie");
		thingsToDo.add("Doubly linked list and linked list");
		thingsToDo.add("Dynamic Programming - substring, lcs, knapsack");
		thingsToDo.add("100 linkedin problems");
		for(String things:thingsToDo){
			System.out.println(things);
		}
	}
}
