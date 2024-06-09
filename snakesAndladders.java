import java.util.*;
public class snakesAndladders{
    static int diceRoll;
    static int[][] game = new int[10][10]; //rows = 10, columns = 10, stores 100 elements 
    static Graph graph = new Graph(100, 100); //Rows of graph & columns of graph 100
    static boolean[] visited = new boolean[10 * 10]; //boolean array of size 100
    static int[] rolls = new int[10 * 10]; 
    static ArrayQueue<Node> queue = new ArrayQueue<>(); 

    public static int snakeAndLadder(ArrayList<ArrayList<Integer>> L, ArrayList<ArrayList<Integer>> S) { //takes ladders, snakes

        try {
        int adjacentNode;
        int i = 0;

        //Adjacency list ---> helps find adjacency of node easily 
        while(i < 100){ //if less than 100 do
            diceRoll = 1;
            while(diceRoll < 7){ //if less than 7 do
                adjacentNode = i + diceRoll; 
                if (adjacentNode < 100) { //adjacent node did not reach the end of the game
                    graph.Connect(i, adjacentNode); //connect i with adjacent node
                }
                diceRoll++;
            }
            i++;

        }
        //Marking snakes on the game
        for (int j = 0; j < S.size(); j++) { 
            ArrayList<Integer> snake = S.get(j); 
            int baseSnake = snake.get(0); //make base of snake
            int endSnake = snake.get(1); //make end of snake
            game[baseSnake / 10][baseSnake % 10] = endSnake; // get the rows and columns location of snake
            graph.Connect(baseSnake, endSnake); //then connect the base and end of snakes together
        }

        //Marking ladders on the game
        for (int j = 0; j <L.size(); j++) {
            ArrayList<Integer> ladder = L.get(j);
            int baseLadder = ladder.get(0); //make base of ladder 
            int endLadder = ladder.get(1); //make end of ladder
            game[baseLadder / 10][baseLadder % 10] = endLadder; // get the rows and columns location of ladder
            graph.Connect(baseLadder, endLadder); // then connect the base and end of ladders together 
        }

        //BFS O(V+E) used because it helps to find the shortest path (we dont need to count if the node has been finished)
        queue.enqueue(new Node(0, 0)); //enqueue new node
        visited[0] = true; //set visited to true

        int s = queue.size();
        while (s != 0) { //while queue is not 0, i.e while queue is not empty
            Node current = (Node) queue.dequeue(); //make current and let it be the dequeued value of queue
            if (current.node  == 99) //if current node reached sqaure 99 of board
            {
                 current.distance = current.distance - 1; //decrement distance
                 return current.distance; //return the distance
            }

            ArrayList<Integer> adjacencyList = graph.getList(current.node);
            for (int nextNode : adjacencyList) { //for each nextNode in adjacenecyList (find adjacent nodes)
                if (!visited[nextNode]) { //if nextNode is not visited 
                    queue.enqueue(new Node(nextNode, current.distance + 1)); //enqueue new node and update distance
                    visited[nextNode] = true; //set visited to true
                    rolls[nextNode] = current.distance + 1;
                }
            }
        }
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("The numbers for ladders and/or snakes have to be positive or you are out of bounds");
    }


        return -1; //no solution found
    }
    public static void main(String[] args) 
    {
            ArrayList<ArrayList<Integer>> ladders = new ArrayList<>();
            ArrayList<ArrayList<Integer>> snakes = new ArrayList<>();
    
         
            ladders.add(new ArrayList<>(Arrays.asList(32, 62)));
            ladders.add(new ArrayList<>(Arrays.asList(42, 68)));
            ladders.add(new ArrayList<>(Arrays.asList(12, 98)));
    
    
            snakes.add(new ArrayList<>(Arrays.asList(95, 13)));
            snakes.add(new ArrayList<>(Arrays.asList(97, 25)));
            snakes.add(new ArrayList<>(Arrays.asList(93, 37)));
            snakes.add(new ArrayList<>(Arrays.asList(79, 27)));
            snakes.add(new ArrayList<>(Arrays.asList(75, 19)));
            snakes.add(new ArrayList<>(Arrays.asList(49, 47)));
            snakes.add(new ArrayList<>(Arrays.asList(67, 17)));
           
    
            int result = snakeAndLadder(ladders, snakes);
            System.out.println("The least number of rolls is = " + result);
        }
}
