public class Node {
    //A=65
    char state;
    Node parent;
    int g;
    int action;
    public Node(int state,Node parent,int g,int action){
        this.state=(char)state;
        this.parent=parent;
        this.g=g;
        this.action=action;
    }

}

