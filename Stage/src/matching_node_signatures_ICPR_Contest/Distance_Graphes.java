package matching_node_signatures_ICPR_Contest;


import java.util.Vector;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sourceforge.gxl.GXLAttr;
import net.sourceforge.gxl.GXLDocument;
import net.sourceforge.gxl.GXLEdge;
import net.sourceforge.gxl.GXLFloat;
import net.sourceforge.gxl.GXLGraph;
import net.sourceforge.gxl.GXLNode;
import net.sourceforge.gxl.GXLString;


/**
 *
 * @author noussa
 */
public class Distance_Graphes {
    // float Distance;

//Le constructeur prend en parametre deux chemins de deux fichiers GXL
    public static float Distance_Graphes(GXLGraph g1, GXLGraph g2) {
        Read_GXL R_GXL1 = new Read_GXL();
        Read_GXL R_GXL2 = new Read_GXL();
        R_GXL1.RecupereGrf(g1);
        R_GXL2.RecupereGrf(g2);
        R_GXL1.Remplir_signature();
        R_GXL2.Remplir_signature();

        //________________________Remplir la matrice distance entre deux Graphes__________________//

        float[][] Mat_dist = new float[R_GXL1.Sign_Vect.length][R_GXL2.Sign_Vect.length];
        for (int i = 0; i < R_GXL1.Sign_Vect.length; i++) {
            for (int j = 0; j < R_GXL2.Sign_Vect.length; j++) {
                Dist_Signatures DS = new Dist_Signatures(R_GXL1.Sign_Vect[i], R_GXL2.Sign_Vect[j]);
                Mat_dist[i][j] = (float) DS.Dist_S;
            }
        }
       /* for(int i=0; i < R_GXL1.Sign_Vect.length; i++)
        {
            for (int j=0;j<R_GXL2.Sign_Vect.length; j++)
            {
                System.out.println("The distance between the signature "+i+" and signature "+j+" is "+Mat_dist[i][j]);
            }
        }
        System.out.println("--------------------------------------------------------------------");*/
        //_________________________Affichage de la matrice distance_________________________________//

        //_________________________Calcul de la distance entre deux graphes en utilisant la methode HAngroise______________________________//

        Hungarian H = new Hungarian(Mat_dist);
        int m = min(R_GXL1.Sign_Vect.length, R_GXL2.Sign_Vect.length);
        float dist = (float) H.H_sum / m + Math.abs(R_GXL1.Sign_Vect.length - R_GXL2.Sign_Vect.length);
   
        return dist;
    }

    private static List<String> getfiles(String fileList) throws IOException {
        List<String> filesName =  new ArrayList<String>();
        //try {
            /*String ligne;
            BufferedReader dataFile = new BufferedReader(new FileReader(fileList));
            while ((ligne = dataFile.readLine()) != null) {
                System.out.println("Adding file"+ligne);
               filesName.add(ligne);
            }
            dataFile.close();
            */
            File folder = new File(fileList);
            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
       
                    //System.out.println(fileEntry.getName());
                    filesName.add(fileEntry.getPath());
                }
            }

        /*} catch (FileNotFoundException ex) {
            System.out.println("Erreur de lecture du fichier");
            Logger.getLogger(Distance_Graphes.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return filesName;
    }

    private static GXLGraph getGraphFromCtFile(String file) throws FileNotFoundException, IOException {
        //System.out.println("File : " + file);
        BufferedReader dataFile = new BufferedReader(new FileReader(file));
        String ligne;
        HashMap<Integer,String> ligneDuFichier = new HashMap<Integer,String>();
        //System.out.println("Getting graph from : " + file);
        int count = 0;
        while ((ligne = dataFile.readLine()) != null) {
              ligneDuFichier.put(count, ligne);
              count++;
            }
        dataFile.close();
        GXLGraph graph = new GXLGraph(file.replace(".ct", ""));
        String[] templineComponent = ligneDuFichier.get(1).trim().split("\\s+");
        int nodeNumber = Integer.parseInt(templineComponent[0]);
        int edgeNumber = Integer.parseInt(templineComponent[1]);
        for(int i= 0; i<nodeNumber;i++ ){
            templineComponent = ligneDuFichier.get(i+2).trim().split("\\s+");
            String x = templineComponent[0];
            String y = templineComponent[1];
            String z = templineComponent[2];
            String label = templineComponent[3];
          
             GXLNode node= new GXLNode(String.valueOf(i+1));
             //System.out.println("Node : "+String.valueOf(i));
             GXLFloat S1= new GXLFloat(Float.parseFloat(x));
             GXLFloat S2= new GXLFloat(Float.parseFloat(y));
             GXLFloat S3= new GXLFloat(Float.parseFloat(z));
             GXLString S4= new GXLString(label);
             GXLAttr att1 = new GXLAttr("x" ,S1);
             GXLAttr att2 = new GXLAttr("y" ,S2);
             GXLAttr att3 = new GXLAttr("z" ,S3);
             GXLAttr att4 = new GXLAttr("label" ,S4);
             node.add(att1);
             node.add(att2);
             node.add(att3);
             node.add(att4);
             graph.add(node);
             //System.out.println(graph.getGraphElementAt(i).toString());
        }
         for(int i= 0; i<edgeNumber;i++ ){
           templineComponent = ligneDuFichier.get(nodeNumber+i+2).trim().split("\\s+");

            String src = templineComponent[0];
            String dst = templineComponent[1];
            String l1 = templineComponent[2];
            String l2 = templineComponent[3];

            GXLString S1= new GXLString(l1);
            GXLString S2= new GXLString(l2);
            GXLAttr att1 = new GXLAttr("l1" ,S1);
            GXLAttr att2 = new GXLAttr("l2" ,S2);

            GXLEdge edge = new GXLEdge(src,dst);
            //System.out.println(edge.getSourceID());
            //System.out.println(edge.getTargetID());
            edge.add(att1);
            edge.add(att2);
            graph.add(edge);

           
        }

        return graph;
    }

    class Lancecalcul extends Thread {

        public int pos;

        public Lancecalcul(int position) {
            this.pos = position;
        }
    }



    public static Vector getdata(String fileList) throws IOException {
        Vector resut = new Vector();
        List<String> graphSet = getfiles(fileList);

        for(String file : graphSet){
               //System.out.println("Fichier lu : "+file);
            GXLGraph g = getGraphFromCtFile(file);
            GXLDocument gxldoc = new GXLDocument();
            gxldoc.getDocumentElement().add(g);
            resut.add(g);
            
        }

        return resut;

    }

    public static void main(String[] args) throws IOException {

        
        String grffile =args[0];
        System.out.print("Loading input file ..... ");
        BufferedReader dataFile = new BufferedReader(new FileReader(grffile));
        System.out.print("OK\n");

        String ActualGrapgString="",ligne;


        System.out.print("Reading input data ");
        while((ligne = dataFile.readLine()) != null )
          {
            System.out.print(".");

             ActualGrapgString = ActualGrapgString +ligne + "\n";
             ligne="";
              
          }
        //System.out.println(ActualGrapgString);
        String[] split = ActualGrapgString.split("G ");

        int nombredegraphe = split.length-1;
        System.out.println(nombredegraphe);
        for(int i =1; i< split.length;i++)
        {
        
        GXLGraph g = getgraph(split[i]);
        GXLDocument gxldoc = new GXLDocument();
            gxldoc.getDocumentElement().add(g);
            // Write the document to file
            try {
                gxldoc.write(new File(g.getID()));
            } catch (IOException ioe) {
                System.out.println("Error while writing to file: " + ioe);
            }
        }
        
        /*
        try {
            //BufferedWriter fichierImp = new BufferedWriter(new FileWriter("implicitEmbedding.txt"));
           
            float[][] distanceMatrix = new float[nombredegraphe][nombredegraphe];
            for (int i = 1; i < nombredegraphe+1; i++) {
                for (int j = i; j < nombredegraphe+1; j++) {
                    GXLGraph g1 = getgraph(split[i]);
                    GXLGraph g2 = getgraph(split[j]);

                    float d = Distance_Graphes(g1,g2);
                    d *= 1000.0;
                    d = Math.round(d);
                    d /= 1000.0;
                    distanceMatrix[i-1][j-1] = d;
                    distanceMatrix[j-1][i-1] = d;
                    //fichierImp.write(g1.getID()+" "+g2.getID() + " " + (Float)d);
                    //fichierImp.newLine();
                    System.out.println(g1.getID() + " ---> " + g2.getID() + "  =   " + d);
                }
            }
            //fichierImp.close();
            BufferedWriter fichier = new BufferedWriter(new FileWriter(args[1]));

            for (int i = 0; i < nombredegraphe; i++) {
                for (int j = 0; j < nombredegraphe; j++) {
                    fichier.write(String.valueOf(distanceMatrix[i][j]) + " ");
                }
                fichier.newLine();
            }
            fichier.close();

        } catch (Exception ex) {
            Logger.getLogger(Distance_Graphes.class.getName()).log(Level.SEVERE, null, ex);
        }//*/

    }

    public static GXLGraph getgraph(String grStr)
    {
        String[] t= grStr.split("\n");
        String id=t[0];
        int Graph_id = Integer.parseInt(id);

        GXLGraph graph = new GXLGraph(id);

        int sizeGraphe = Integer.parseInt(t[1]);
        for (int i=2;i<sizeGraphe+2;i++)
        {
            
            String [] att =t[i].split(" ");
            //System.out.println(att.length);
            //System.out.println();
            //System.out.println();
             GXLNode node= new GXLNode(att[0]);
             GXLFloat S1= new GXLFloat(Float.parseFloat(att[1]));
             GXLFloat S2= new GXLFloat(Float.parseFloat(att[2]));
             GXLFloat S3= new GXLFloat(Float.parseFloat(att[3]));
             GXLFloat S4= new GXLFloat(Float.parseFloat(att[4]));
             GXLAttr att1 = new GXLAttr("S" ,S1);
             GXLAttr att2 = new GXLAttr("R" ,S2);
             GXLAttr att3 = new GXLAttr("G" ,S3);
             GXLAttr att4 = new GXLAttr("B" ,S4);
             node.add(att1);
             node.add(att2);
             node.add(att3);
             node.add(att4);
             graph.add(node);
        }

        int idnodes=0;
        for (int i=sizeGraphe+2;i<t.length;i++)
        {
            if(t[i].length()>=1 && !t[i].contains(" "))
            {
                int sizeedges=Integer.parseInt(t[i]);
                for(int k=1;k<sizeedges+1;k++)
                {
                    String [] edgg= t[i+k].split(" ");
                    int source = Integer.parseInt(edgg[0]);
                    int target = Integer.parseInt(edgg[1]);

                    GXLEdge edge = new GXLEdge(String.valueOf(source),String.valueOf(target));
                    graph.add(edge);
                    

                }
            }
        }
        return graph;


    }

//________________________________________________________________Fonctions_________________________________________________________________
    public static int min(int a, int b) {
        if (a < b) {
            return (a);
        } else {
            return (b);
        }
    }
}
