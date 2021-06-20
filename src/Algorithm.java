// Tal alter 206292450
// Or abitbol 312614605

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

public class Algorithm {

    int[][] matrix;
    Node start;
    Node target;
    public Search searchType;

    public Algorithm(int[][] matrix, Node start, Node target) {
        this.matrix = matrix;
        this.start = start;
        this.target = target;
    }

    public void trytosearch() {
        //searchType.search();
    }

    public void setSearchType(Search searchType){
        this.searchType=searchType;
    }

    public void search(){
        this.searchType.search(this.matrix,this.start,this.target);
    }

    public boolean insideSet(Node node, Set<Node> h) {
        for (Node ele : h) {
            if (ele.state == node.state)
                return true;
        }
        return false;
    }

    public boolean solution(Node current, Node target) {
        if (current.state == target.state) {
            LinkedList<Node> path;
            LinkedList<Node> reversed;
            path = get_path(current);
            reversed = reverse_path(path);
            printPath(reversed);
            return true;
        }
        return false;
    }

    public LinkedList<Node> get_path(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        while (target != null) {
            path.add(target);
            target = target.parent;
        }
        return path;
    }

    public LinkedList<Node> reverse_path(LinkedList<Node> path) {
        LinkedList<Node> reversed = new LinkedList<Node>();
        for (int i = path.size() - 1; i >= 0; i--)
            reversed.add(path.get(i));
        return reversed;
    }

    public void printPath(LinkedList<Node> path) {
        for (int i = 0; i < path.size(); i++)
            if (i != path.size() - 1)
                System.out.print(path.get(i).state + " > ");
            else {
                System.out.println(path.get(i).state);
                System.out.println("Total cost: " + path.get(i).g);
            }
    }


    public Node insideQueue(Node node, PriorityQueue<Node> q) {
        for (Node ele : q) {
            if (ele.state == node.state)
                return ele;
        }
        return null;
    }
}
