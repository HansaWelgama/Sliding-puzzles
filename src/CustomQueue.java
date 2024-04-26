
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

public class CustomQueue {

    private final Node[] nodeQueue; // Array to store the nodes in the queue
    private int front; // Index of the front element
    private int rear; // Index of the rear element
    private int size; // Current size of the queue
    private final int capacity; // Maximum capacity of the queue

    public CustomQueue(int capacity) {
        nodeQueue = new Node[capacity]; // Initialize the nodeQueue array with the specified capacity
        this.capacity = capacity; // Set the maximum capacity of the queue
        size = 0; // Initialize the size of the queue to 0
        front = 0; // Initialize the front index of the queue
        rear = -1; // Initialize the rear index of the queue
    }

    public void enqueue(Node info) {
        if (isFull()) { // Check if the queue is full
            System.out.println("Queue Full"); // Print a message indicating that the queue is full
        } else {
            rear = (rear + 1) % capacity; // Calculate the next rear index using modulo operation
            nodeQueue[rear] = info; // Add the node to the rear of the queue
            size++; // Increment the size of the queue
        }
    }

    public void enqueueAll(Node node) {
        for (int i = 0; i < node.nextNodes.size(); i++) { // Iterate through the nextNodes list of the given node
            enqueue(node.nextNodes.get(i)); // Enqueue each node in the nextNodes list
        }
    }

    public Node dequeue() {
        if (isEmpty()) { // Check if the queue is empty
            System.out.println("Queue empty"); // Print a message indicating that the queue is empty
            return null; // Return null as there are no nodes to dequeue
        } else {
            Node removedNode = nodeQueue[front]; // Get the node at the front of the queue
            front = (front + 1) % capacity; // Calculate the next front index using modulo operation
            size--; // Decrement the size of the queue
            return removedNode; // Return the dequeued node
        }
    }

    public boolean isFull() {
        return size == capacity; // Check if the size of the queue is equal to the capacity
    }

    public boolean isEmpty() {
        return size == 0; // Check if the size of the queue is 0
    }
}
