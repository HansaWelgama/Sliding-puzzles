/*
 * Task 1,2,3,4
 * Name: H.H.Welgama
 * IIT Number: 20221109
 * UOW Number: w1998723
 * I confirm that I understand what plagiarism / collusion /
 * contract cheating is and have read and understood
 * the section on Assessment Offences in the Essential Information for Students.
 * The work that I have submitted is entirely my own.
 * Any work from other authors is duly referenced and acknowledged.
 */

import java.util.ArrayList;

public class Node {

    int[] coordinates; // Coordinates of the node
    ArrayList<Node> nextNodes = new ArrayList<>(); // List of adjacent nodes
    Node previousNode = null; // Reference to the previous node
    String previousDirection; // Direction from the previous node to this node

    public Node(int[] coordinates) {
        this.coordinates = coordinates; // Initialize the coordinates of the node
    }

    public Node(int[] coordinates, String previousDirection, Node previousNode) {
        this.coordinates = coordinates; // Initialize the coordinates of the node
        this.previousDirection = previousDirection; // Initialize the previous direction
        this.previousNode = previousNode; // Initialize the previous node
    }

    public void addNextNode(Node nextNode) {
        nextNodes.add(nextNode); // Add the next node to the list of adjacent nodes
    }
}
