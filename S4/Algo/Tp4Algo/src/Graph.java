import java.io.*;
import java.util.*;

public class Graph {
    LinkedList <Arc>[] listOfAdjacency;
    private int numberOfVertex;
    private double density;
    private int numberOfArcs;
    private File file;

// constructeur permettant de génerer aléatoirement des graphs
    public Graph(int numberOfVertex, double density) {
        this.numberOfVertex = numberOfVertex;
        this.density = density;
        this.numberOfArcs = (int) (numberOfVertex * (numberOfVertex - 1) * this.density);
        initialize();
        generateArcs();
    }

    public void  initialize(){
        this.listOfAdjacency = new LinkedList[this.numberOfVertex];
        for(int index = 0; index < this.numberOfVertex; index ++){
            this.listOfAdjacency[index] = new LinkedList<>();
        }
    }

     public void generateArcs() {
         int index = 0;
         Random random = new Random();
         while (index < this.numberOfArcs) {
             int vertex1 = random.nextInt(numberOfVertex);
             int vertex2 = random.nextInt(numberOfVertex);
             int weight = random.nextInt(20);
             if (vertex1 != vertex2) {
                 Arc arc = new Arc(vertex1, vertex2, weight);
                 if (!containsArc( arc)) {
                     this.listOfAdjacency[arc.getStart()].addFirst(arc);
                     index++;
                 }
             }
         }
     }

     public boolean containsArc(Arc arc){
        for(Arc thisArc: listOfAdjacency[arc.getStart()]){
            if (thisArc.equals(arc))
                return true;
        }
        return false;
     }
// retourne la liste de sommets de 0 à nombre de sommet-1
     public ArrayList<Integer> getVertices(){
        ArrayList<Integer> vertices = new ArrayList<>(getNumberOfVertex()) ;
        for(int vertex =0; vertex< getNumberOfVertex(); vertex++){

            vertices.add( vertex);
         }
        return vertices;
     }



     //-----------------------------------------------------------------------------------------------
     //---------------------------------Graphe à partir d'un fichier texte----------------------------


     // constructeur de graphe à partir d'un fichier texte
     public Graph( File file) throws FileNotFoundException {
        this.file = file;
        ArrayList<Arc>listOfArcs = getArcs();
         this.listOfAdjacency = new LinkedList[this.numberOfVertex];
         for(int index = 0; index < this.numberOfVertex; index ++){
             this.listOfAdjacency[index] = new LinkedList<>();
         }
         for (Arc arc : listOfArcs) {
             listOfAdjacency[arc.getStart()].addFirst(arc);
         }
     }
      public ArrayList<Arc> getArcs() {
          ArrayList<Arc> listOfArcs = new ArrayList<>();
          Scanner scanner = null;
          try {
              scanner = new Scanner(this.file);

              this.numberOfVertex = scanner.nextInt();


              while (scanner.hasNextInt()) {
                  listOfArcs.add(new Arc(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
              }
          } catch (Exception e) {
              e.printStackTrace();
          }

          scanner.close();
          return listOfArcs;
      }

      public Arc[] getNeighbors(int vertex){
        Arc[] neighbors = new Arc[listOfAdjacency[vertex].size()];
        int index =0;
        for (Arc neighbor : listOfAdjacency[vertex]){
            neighbors[index] = neighbor;
            index++;
        }
        return neighbors;
      }

    public int getNumberOfVertex() {
        return numberOfVertex;
    }


    public int getDensity() {
        return (int) density;
    }


    @Override
    public String toString() {
        String myGraph = "";
        for (LinkedList<Arc> line : listOfAdjacency){
            myGraph+= "[";
            for (Arc arc : line){
                myGraph += arc+"; ";
            }
            myGraph = myGraph.substring(0, myGraph.length()-2);
            myGraph += "]\n";
        }
        return myGraph;
    }
}
