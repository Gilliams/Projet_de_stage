/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_embedding;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Salim Jouili
 * Implementation d'une nouvelle technique de GRAPH EMBEDDING
 * Basée sur le papier : Optimal cluster parserving embedding of nonmetric proximity data
 *                       PAMI, VOL 25, No 12, 2003
 *                       Volker Roth, Julian Laub, motoaki kawanabe, Joachim M. Buhmann
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        // lire le fichier contenant la matrice de distances
        /*
         Riesen_Embedding_Technique RET = new Riesen_Embedding_Technique();
        RET.doit();
         * */
        /*
         * args[0] : original file .grf
         * args[1] : output embed file
         * */
        if(args.length!=3){
            System.out.println("Please define the arguments as follows:" +
                    "\n java -jar -Xmx1000m Graph_Embedding.jar input_file.grf output_file rate");
        System.out.println(new File(".").getAbsolutePath());
    }

        if(args.length==3){
//        System.out.println("----------------------------------------------");
//        System.out.println("Graph Embedding Using Constant Shift Embedding");
//        System.out.println("Salim Jouili and Salvatore Tabbone");
//        System.out.println("LORIA-INRIA Nancy Grand Est, France");
//        System.out.println("Contact: {salim.jouili,tabbone}@loria.fr");
//        System.out.println("----------------------------------------------");
//        System.out.println("");
//        System.out.println("----------------------------------------------");
        System.out.println("Dossier " + args[0] +" Fichier de sortie "+args[1] +"\n");

        My_embedding_technique MET = new My_embedding_technique();
        MET.doitICPRContest(args[0], args[1], Double.parseDouble(args[2]));

        }

        

    }
}
