import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {

//        Graph graph1 = new Graph(1000,0.9);
//        System.out.println("\n------------Generated graph---------------\n");
//        displayGraphs(graph1, 0, 99999);



//       System.out.println("\n------------Graph built from txt file--------------\n");
//       Graph graph2 = new Graph(new File("/amuhome/r20031646/Documents/Tp4Algo/src/graphe.txt"));
//       displayGraphs(graph2,0,5);
  //      System.out.println("\n------------Graph built from txt file--------------\n");
  //      Graph graph2 = new Graph(new File("/amuhome/r20031646/Documents/Tp4Algo/src/graphtest.txt"));
  //      displayGraphs(graph2,5,0);

              System.out.println("\n------------Graph built from txt file--------------\n");
              Graph graph2 = new Graph(new File("/amuhome/r20031646/Documents/Tp4Algo/src/graphe-100.txt"));
              displayGraphs(graph2,99,);
    }

    public static void displayGraphs(Graph graph, int source, int dest) {
        System.out.println(graph);
        System.out.println("\n-----------Première approche------------\n");
        int distance = new Dijkstra().dijkstra(graph,source,dest);
        System.out.println("The weight of shorter path is: "+ distance);
        //System.out.println("\n------------Deuxième approche ------------------\n");
        //System.out.println(Arrays.toString(new Dijkstra().dijkstraPriorityQueue(graph, source, dest)));

    }





}
