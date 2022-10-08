import java.util.*;

public class Dijkstra {
    private ArrayList<Integer> vertices;
    private Graph graph;
    private int vertex;
    private final ArrayList <Integer>shorterPath = new ArrayList<>();

    public Dijkstra() {

    }
    public int dijkstra(Graph graph, int vertex, int destination){
        this.graph = graph;
        this.vertex = vertex;
        this.vertices = graph.getVertices() ; //F = S
        int [] distances = new int[vertices.size()];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[vertex] =0; //d[s] = 0;
        while (!vertices.isEmpty()){ //tant que F!=0/;
            int min = extractMin(distances); // u = extraire_min_(F)
            Arc [] neighbors = graph.getNeighbors(min);
            for(Arc vert : neighbors){ // pour chaque v E V(u)
                if(distances[vert.getEnd()]> (distances[min] + vert.getWeight() )){
                    distances[vert.getEnd()] =  (distances[min] + vert.getWeight() );

                }
            }
            shorterPath.add(min);

        }
        System.out.println("The shorter path is: " + shorterPath);
        return distances[destination];
    }

    private int extractMin(int[] distances) {
        int vertex = vertices.get(0);
        int vertexIndex =0 ;
        int min = distances[vertex];
        ArrayList<Integer> vertices = this.vertices;
        for (int index =1; index< vertices.size(); index++){
            if(min > distances[vertices.get(index)]){
                min = distances[vertices.get(index)];
                vertexIndex = index;
                vertex = vertices.get(index);
            }
        }
        swap(vertices,vertexIndex,vertices.size()-1);
        vertices.remove(vertices.size()-1);
        return vertex;
    }

    private void swap(ArrayList<Integer> tab, int index1, int index2){
        int val1 = tab.get(index1);
        tab.set(index1, tab.get(index2));
        tab.set(index2, val1);
    }


   //-----------------------------Deuxième approche--------------------------


    public int[] dijkstraPriorityQueue(Graph graph, int source, int dest) {
        this.vertices = graph.getVertices() ; //F = S
        int size = graph.getNumberOfVertex();
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);
        int[] distances = new int[size];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Arc> queue = new PriorityQueue();
        queue.offer(new Arc(0, 0, 0));


        while (!queue.isEmpty()) { //tant que F!=0/;
            Arc currentArc = queue.poll();
            int currentIndex = currentArc.getStart();
            int currentWeight = currentArc.getWeight();
            if (!visited[currentIndex]) {
                visited[currentIndex] = true;
                Arc[] neighbors = graph.getNeighbors(currentArc.getStart());
                for (Arc neighbor : graph.listOfAdjacency[currentIndex]) {
                    queue.add(neighbor);System.out.println(currentIndex +" : " + visited[currentIndex]);
                    if (distances[neighbor.getEnd()] > currentWeight + neighbor.getWeight()) {
                        distances[neighbor.getEnd()] = currentWeight + neighbor.getWeight();

                    }
                }
            }
        }
        return distances;
//    }
//    public static class ArcComparator implements Comparator<Arc>  {
//        @Override
//        public int compare(Arc arc1, Arc arc2) {
//            if( arc1.getWeight() < arc2.getWeight())
//            return -1;
//            else if(arc1.getWeight() > arc2.getWeight()){
//                return 1;
//            }
//            return 0;
//        }
    }


    public ArrayList<Integer> getVertices() {
        return vertices;
    }

    public ArrayList<Integer> getShorterPath() {return shorterPath;}


    public int getVertex() {
        return vertex;
    }

    public Graph getGraph() {
        return graph;
    }
    @Override
    public String toString() {
        return "Dijkstra{" +
                "vertices=" + vertices +
                ", graph=" + graph +
                ", vertex=" + vertex +
                '}';
    }
}