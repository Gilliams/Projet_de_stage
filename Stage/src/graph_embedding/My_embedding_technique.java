/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph_embedding;

import Jama.Matrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import matching_node_signatures_ICPR_Contest.Distance_Graphes;
import net.sourceforge.gxl.GXLGraph;


/*
 *
 * @author salim
 */

public class My_embedding_technique {
    
    public void doitICPRContest( String Originalfile, String outputFile, double participationRate) throws IOException
    {
        Vector dataset=new Vector();
        dataset=Distance_Graphes.getdata(Originalfile);
        if(dataset!=null){
        double[][] Matrice = new double[dataset.size()][dataset.size()];
        System.out.println("The number of graph files are "+dataset.size()+"\n");
        for(int i=0; i<dataset.size(); i++)
        {
            for (int j=i;j<dataset.size(); j++)
            {
               
                Matrice[i][j]=Distance_Graphes.Distance_Graphes((GXLGraph)dataset.get(i), (GXLGraph)dataset.get(j));
                Matrice[j][i]=Matrice[i][j];
            }
        }
        for(int i=0; i<Matrice.length; i++)
        {
            for (int j=0;j<Matrice.length; j++)
            {
            	if(i != j){
            		System.out.println("Graph["+i+"] and Graph["+j+"] = "+ Matrice[i][j]);
            	}
            }
            if(i != Matrice.length -1){
                System.out.println("\n");
            }
        }
//        System.out.print("OK\n");
        
       
        PrintStream output = new PrintStream(new File(outputFile));
       

        for(int i=0; i<dataset.size(); i++)
        {
            GXLGraph g1 = (GXLGraph) dataset.get(i);
            for (int j=0;j<dataset.size(); j++)
            {
                GXLGraph g2 = (GXLGraph) dataset.get(j);
                if(g1 != g2){
                	//output.println("File["+String.valueOf(g1.getID())+"] and File["+String.valueOf(g2.getID())+"] = "+Matrice[i][j]);
                	output.println("File["+i+"] and File["+j+"] = "+Matrice[i][j]);
                }
            }
            if(i != dataset.size() -1){
                output.println("\n");
            }
        }
        output.close();

        


       
       /************************************************** 
        
        int n = Matrice.length; // Taille de la matrice

        Matrix D = new Matrix(Matrice);
        System.out.print("Constant Shift Embedding .....");
        **************************************************/




        // D coorespondera à D (dissimilarity matrix) dans le papier.

        /**************************************
         *                                    *
         * PART I : Constant Shift Embedding  *
         *                                    *
         **************************************/
        // frist STEP : objectif Sc
        // Maintenant le calcul de Sc=-1/2xDc avec Dc= QDQ, c pour centralized matrix.
        // Q= In-(1/n) x en x (en)T
        // en = (1,1,1,...)T n-vectors of ones
        // In = identity matrix
        // n Size of D ( D est nxn)
        // Let's go ...
        // (1) Computing en
        /************************************************************************
        double[] en_temp = new double[n];
        for (int i = 0; i < n; i++) {
            en_temp[i] = 1;
        }

        Matrix en = new Matrix(en_temp, n);
        ************************************************************************/

        /*public Matrix(double[] vals, int m) JAMA library
         *Construct a matrix from a one-dimensional packed array
         *Parameters:
         *      vals - One-dimensional array of doubles, packed by columns (ala Fortran).
         *       m - Number of rows. */

        // (2) Computing In the identity matrix nxn
       /******************************************************************
        Matrix In = Matrix.identity(n, n);
        ******************************************************************/
        /*Generate identity matrix (nxn)
         */
        // (3) Computing Q
        /*********************************************************************
        Matrix secondTerm = en.times(en.transpose());
        double un_sur_n = (double) 1 / n;
        Matrix Q = In.minus(secondTerm.times(un_sur_n));
        // (4) Computing Dc
        Matrix Dc = Q.times(D).times(Q);
        // (5) finally now Computing Sc
        double ter = (double) -0.5;
        Matrix Sc = Dc.times(ter);
        **********************************************************************/
        // Second STEP : objectif Dse : squared euclidean distances
         /* Dse = D - 2 * Lambda(Sc) * (en * (en)T - In)
         * Lambda(Sc) c'est le Minimal eigenvalue of Sc
         */
        
        /**********************************************************************
         double[] eigenvalues = Sc.eig().getRealEigenvalues();
        Arrays.sort(eigenvalues);

        double Lambda = eigenvalues[0]; // Minimal eigenvalue of Sc
        Matrix thirdTerm = secondTerm.minus(In);
        double deux_Lambda = (double) Lambda + Lambda;
        deux_Lambda *= 1000.0;
        deux_Lambda = Math.round(deux_Lambda);
        deux_Lambda /= 1000.0;
        Matrix Dse = D.minus(thirdTerm.times(deux_Lambda));
        ***********************************************************************/


        /**************************************************
         *                                                *
         * PART II : Reconstructing The Embedding Vectors *
         *                                                *
         **************************************************/
        // (1) First Step: Calculate the centralized dot product matrix Ssec
        /**********************************************************************
        Matrix Ssec1 = Q.times(Dse).times(Q);
        Matrix Ssec = Ssec1.times(ter);
        ***********************************************************************/

        // (2) Second Step: Express Ssec in its eigenbasis Ssec = V * delta * (V)T
        // V=(v_1,v_2...v_n) contains the eigenvectors vi
        // delta= diag(lamba_1,lambda_2....lambda_n) is a diagonal matrix of eigenvalues
        // Avec lamba_1>lambda_2>...>lambda_p > lambda_p+1 =0= .... lambda_n
        // Due to the centralization which introduces a linear dependecy between
        // vectors, at least one eigenvalue equals zero, i.e. p <= n-1.
        /**********************************************************************
        Matrix V = Ssec.svd().getV();
        Matrix delta = Ssec.svd().getS();

        double[] temp = Ssec.svd().getSingularValues();
        int p = -1;

        for (int i = 0; i < n; i++) {
            temp[i] *= 1000.0;
            temp[i] = Math.round(temp[i]);
            temp[i] /= 1000.0;
        }

        for (int i = 0; i < n; i++) {

            if (temp[i] == 0) {
                p = i - 1;
                break;
            }
        }
        ***********************************************************************/
        //System.out.println(p);

        /*****************************
         * LAST PART : Calculate the n x p map matrix
         * X_p = V_p (delta_p)^0.5
         * Avec V_p=(v_1,.....,v_p)
         * et   delta_p= diag(lambda_1,.....,lambda_p);
         */

        // P = 10% de dataset.size();
  /*      int poc=Math.round(dataset.size()/10)+1;       
        if (poc==1)
            p=2;
        else
            p=poc;
    */
        /**********************************************************************
        System.out.println("----------------------------------------------");
        System.out.println("------             Eigenvalues         -------");
        System.out.println("----------------------------------------------");
        p=0;
        double sum =0;
        for(int i = 0; i< delta.getRowDimension(); i++)
        {
            sum = sum + delta.get(i, i);
            System.out.println(delta.get(i, i)+" ");
        }
        System.out.println();
        System.out.println("----------------------------------------------");
        double pourcentage=0;
        double pourcentageExpected=participationRate;

        double sumlambdas = 0;
        for(int i = 0; i< delta.getRowDimension(); i++)
        {
            sumlambdas = sumlambdas + delta.get(i, i);
            pourcentage  = (sumlambdas/sum);
            if(pourcentage>= pourcentageExpected)
            {
                p = i;
                break;
            }
        }
        
        Matrix V_p= V.getMatrix(0, n-1, 0, p-1);
        Matrix delta_p=delta.getMatrix(0, p-1, 0, p-1);
        
        for (int i = 0; i < p-1; i++) {
            for (int j = 0; j < p-1; j++) {
                delta_p.set(i, j,(double) Math.sqrt(delta_p.get(i, j)));
            }
        }
        Matrix  X_p = V_p.times(delta_p);
        System.out.print("OK\n");


       System.out.print("Output file generation ..... ");
       WriteARFF Writefile = new WriteARFF();
       Writefile.writeCONTEST(outputFile,dataset,X_p);
       System.out.print("OK\n");
       System.out.println("Data set successfully embedded");
       ***********************************************************************/
//       System.out.println("----------------------------------------------");
//       System.out.println("Thank you !");
//       System.out.println("----------------------------------------------");



        }
    }

}
