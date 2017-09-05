/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_embedding;

import Jama.Matrix;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import net.sourceforge.gxl.GXLGraph;

/**
 *
 * @author salim
 */
public class WriteARFF {

    /*public float ClassificationRate(Matrix Diss, int NbreClasseTrain, int ElmentParClasseTrain, int NbreClasseTest, int ElmentParClasseTest) {
        float Rate;
        int [] classified = new int[NbreClasseTrain];

        double[][] ligne = Diss.

        for (int i = 0; i < NbreClasseTrain; i++) {


            for (int j = 0; j < ElmentParClasseTrain; j++) {

                for(int ii=0;ii<ElmentParClasseTest;ii++)
                {
                    
                }


            }
        }


        return Rate;
    }*/
    public void writeMatrix(String FileName, Matrix Data) throws IOException
    {
        int p = Data.getColumnDimension();
        int datum = Data.getRowDimension();
        BufferedWriter fichier = new BufferedWriter(new FileWriter(FileName));

        for (int i = 0; i < datum; i++) {
            for (int j = 0; j < p; j++) {
                fichier.write(String.valueOf(Data.get(i, j))+" ");
            }

            fichier.newLine();
        }
        fichier.close();

    }

    public void writearff(String FileName, Matrix Data, int NbreClasse, int ElmentParClasse) throws IOException {
        BufferedWriter fichier = new BufferedWriter(new FileWriter(FileName));
        int p = Data.getColumnDimension();
        int datum = Data.getRowDimension();
        fichier.write("@RELATION Logo");
        fichier.newLine();
        for (int i = 0; i < p; i++) {
            fichier.write("@ATTRIBUTE  attr" + String.valueOf(i) + "  REAL");
            fichier.newLine();
        }
        fichier.write("@ATTRIBUTE  class  {");
        for (int i = 0; i < NbreClasse - 1; i++) {
            fichier.write("class" + String.valueOf(i + 1) + ",");
        }
        fichier.write("class" + String.valueOf(NbreClasse) + "}");
        fichier.newLine();
        fichier.write("@DATA");
        fichier.newLine();
        for (int i = 0; i < datum; i++) {
            for (int j = 0; j < p; j++) {
                fichier.write(String.valueOf(Data.get(i, j)) + ",");
            }
            fichier.write(String.valueOf("class") + (int) Math.ceil((double) (i + 1) / ElmentParClasse));
            fichier.newLine();
        }
        fichier.close();

    }

    public void writeCONTEST(String FileName, Vector data,Matrix Data) throws IOException {
        BufferedWriter fichier = new BufferedWriter(new FileWriter(FileName));
        int p = Data.getColumnDimension();
        int datum = Data.getRowDimension();
        
        

        for (int i = 0; i < datum; i++) {
            GXLGraph g = (GXLGraph) data.get(i);
            fichier.write(String.valueOf(g.getID()));
            for (int j = 0; j < p; j++) {
                float fll = (float) Data.get(i, j);
                fll *= 100000.0;
                fll= Math.round(fll);
                fll /= 100000.0;
                fichier.write(" "+String.valueOf(fll));
            }
            fichier.newLine();
        }
        fichier.close();

    }
    public void writeCONTESTRiesen(String FileName, Vector T,Matrix Data) throws IOException {
        BufferedWriter fichier = new BufferedWriter(new FileWriter(FileName));
        int p = Data.getColumnDimension();
        int datum = Data.getRowDimension();
        System.out.print("Loading input file ..... ");

         System.out.print("Reading input data ");

        for (int i = 0; i < datum; i++) {
            fichier.write(String.valueOf(Integer.parseInt((String) T.get(i))));
            for (int j = 0; j < p; j++) {
                float fll = (float) Data.get(i, j);
                fll *= 100000.0;
                fll= Math.round(fll);
                fll /= 100000.0;
                fichier.write(" "+String.valueOf(fll));
            }
            fichier.newLine();
        }
        fichier.close();

    }

}
