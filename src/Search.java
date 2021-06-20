import java.util.*;

public interface Search {
    void search(int[][] matrix, Node start, Node target);
}

class BFS extends Algorithm implements Search {
    public BFS(int[][] matrix, Node start, Node target) {
        super(matrix, start, target);
    }

    public void search(int[][] matrix, Node start, Node target) {
        System.out.println("BFS SERACH");
        LinkedList<Node> frontier = new LinkedList<Node>();
        Set<Node> explorer_set = new HashSet<>();
        frontier.add(start);
        while (true) {
            if (frontier.isEmpty()) {
                System.out.println("failure");
                return;
            }
            Node node = frontier.poll();
            explorer_set.add(node);
            int i = node.state - 65;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != -1 && matrix[i][j] != 0) {
                    int path_cost = matrix[i][j];
                    Node child = new Node(node.state + j - i, node, path_cost + (int) node.g, j - i);
                    if (solution(child, target)) {
                        return;
                    }
                    if (!insideSet(child, explorer_set))
                        frontier.add(child);
                }
            }
        }
    }
}

    class DFS extends Algorithm implements Search {
    public DFS(int[][] matrix, Node start, Node target) {
        super(matrix, start, target);
    }

    public void search(int[][] matrix, Node start, Node target) {
        System.out.println("DFS SERACH");

        boolean noMoreChilds = false;
        Stack<Node> frontier = new Stack<>();
        Set<Node> explorer_set = new HashSet<>();
        frontier.add(this.start);
        while (true) {
            if (frontier.isEmpty()) {
                System.out.println("failure");
                return;
            }

            Node node = frontier.peek();
            if (noMoreChilds)
                frontier.pop();
            if (solution(node, this.target)) {
                return;
            }
            explorer_set.add(node);
            int i = node.state - 65;
            for (int j = 0; j < this.matrix[i].length; j++) {
                //ADDED 183
                //int i = node.state - 65;
                if (this.matrix[i][j] != -1 && this.matrix[i][j] != 0) {
                    int path_cost = this.matrix[i][j];
                    Node child = new Node(node.state + j - i, node, path_cost + (int) node.g, j - i);
                    if (solution(child, target)) {
                        return;
                    }
                    if (!insideSet(child, explorer_set)) {
                        frontier.add(child);
                        node = child;
                        i = j;
                    }
                }
            }
            noMoreChilds = true;
        }
    }
}

class UCS extends Algorithm implements Search {
    public UCS(int[][] matrix, Node start, Node target) {
        super(matrix, start, target);
    }

    public void search(int[][] matrix, Node start, Node target) {
        System.out.println("UCS SERACH");

        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.g, node2.g);
            }
        });
        Set<Node> explorer_set = new HashSet<>();
        frontier.add(start);
        while (true) {
            if (frontier.isEmpty()) {
                System.out.println("failure");
                return;
            }
            Node node = frontier.poll();
            explorer_set.add(node);
            int i = node.state - 65;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != -1 && matrix[i][j] != 0) {
                    int path_cost = matrix[i][j];
                    Node child = new Node(node.state + j - i, node, path_cost + (int) node.g, j - i);
                    if (solution(child, target))
                        return;
                    Node is_inside_frontier = insideQueue(child, frontier);
                    boolean is_inside_explorer_set = insideSet(child, explorer_set);
                    if (!is_inside_explorer_set || is_inside_frontier == null) {
                        frontier.add(child);
                    } else {
                        if (is_inside_frontier.g < child.g) {
                            frontier.remove(is_inside_frontier);
                            frontier.add(child);
                        }
                    }
                }
            }
        }
    }
}
