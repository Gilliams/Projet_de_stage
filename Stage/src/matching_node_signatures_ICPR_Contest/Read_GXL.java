/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matching_node_signatures_ICPR_Contest;

import java.io.File;
import java.io.FilenameFilter;
import net.sourceforge.gxl.*;
import java.io.File;
import java.util.Vector;
import java.io.*;
import java.util.Enumeration;

public class Read_GXL {

    public int NbreNode;
    public int NbreEdge;
    public GXLGraph graph;
    public Signature[] Sign_Vect;

    public void RecupereGrf (GXLGraph g) {
        graph=g;
        int tailleN=0;
        int tailleA=0;
        if (graph != null) {
            //System.out.println("getChildCount: "+graph.getChildCount());
            for (int i = 0; i < graph.getChildCount(); i++) {
                GXLElement b = graph.getChildAt(i);

                if (b instanceof GXLNode) {
                    tailleN++;
                } else {
                    tailleA++;
                }
            }
            NbreNode = tailleN;
            NbreEdge = tailleA;
            //System.out.println("nb of node :"+NbreNode);
            //System.out.println("nb of edge :"+NbreEdge);
        }


    }

    public void RecupereGr(File f) {

        GXLDocument gxldocument;

        GXLGXL g;
        GXLGraph gr;
        try {
            gxldocument = new GXLDocument(f);
            String s;
            s = f.getName();
            g = gxldocument.getDocumentElement();
            gr = g.getGraphAt(0);
            graph = gr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(" ERROR !!!!! \n  ");
            graph = null;
        }
        int tailleN = 0;
        int tailleA = 0;

        if (graph != null) {
           
            for (int i = 0; i < graph.getChildCount(); i++) {
                GXLElement b = graph.getChildAt(i);

                if (b instanceof GXLNode) {
                    tailleN++;
                } else {
                    tailleA++;
                }
            }
            NbreNode = tailleN;
            NbreEdge = tailleA;
        }

    }

    public void Remplir_signature() {
        Sign_Vect = new Signature[NbreNode];
        int indice_nodes_encours = 0;
        Vector[] nodesAdj = new Vector[NbreNode];
        Vector nod = new Vector();

        for (int k = 0; k < graph.getChildCount(); k++) {
            GXLNode node;
            GXLElement b = graph.getChildAt(k);
            //System.out.println("k: "+k+" indice_nodes_encours: "+indice_nodes_encours);
            if (b instanceof GXLNode) {
                
                node = (GXLNode) graph.getChildAt(indice_nodes_encours);
                //System.out.println(node.getID());
                nod.add(node.getID());
                //System.out.println(nod.indexOf(node.getID()));
                nodesAdj[indice_nodes_encours] = new Vector();

                Sign_Vect[indice_nodes_encours] = new Signature();
                //System.out.println("Elements of node (b.getChildCount()): "+b.getChildCount());
                for (int j = 0; j < b.getChildCount(); j++) {

                    GXLAttr GA = (GXLAttr) node.getChildAt(j);


                    if (GA.getValue() instanceof GXLInt) {
                        GXLInt fx = (GXLInt) GA.getValue();

                        Sign_Vect[indice_nodes_encours].NodeAttr.add(fx.getIntValue());
                    } else if (GA.getValue() instanceof GXLFloat) {
                        GXLFloat fy = (GXLFloat) GA.getValue();

                        Sign_Vect[indice_nodes_encours].NodeAttr.add(fy.getFloatValue());
                    } else if (GA.getValue() instanceof GXLString) {

                        GXLString fz = (GXLString) GA.getValue();

                        Sign_Vect[indice_nodes_encours].NodeAttr.add(fz.getValue());

                    } else {
                        GXLFloat fy = (GXLFloat) GA.getValue();

                        Sign_Vect[indice_nodes_encours].NodeAttr.add(fy.getFloatValue());

                    }
                }
                //to display all the attributes of the node stored in NodeAttr vector
                //System.out.println(node.getChildCount());
                //System.out.println();
               // System.out.println("Node Attributs: "+Sign_Vect[indice_nodes_encours].NodeAttr);
                
                int m = 0;

                for (int l = 0; l < graph.getChildCount(); l++) {
                    GXLElement b1 = graph.getChildAt(l);
                    if (b1 instanceof GXLEdge) {
                        GXLEdge bb = (GXLEdge) b1;
                        //System.out.println("Edge sourceID: "+bb.getSourceID());
                        //System.out.println("node getID: "+node.getID());
                        if ((bb.getSourceID().equals(node.getID())) || ((bb.getTargetID().equals(node.getID())))) {
                            {
                                Sign_Vect[indice_nodes_encours].degre++;
                            }
                        }
                    }
                }
                 /*to get the degree of a node*/
                //System.out.println("Node num "+node.getID()+" and its degree: "+Sign_Vect[indice_nodes_encours].degre);
                
                Sign_Vect[indice_nodes_encours].EdgesAttr = new Vector[Sign_Vect[indice_nodes_encours].degre];

                ///  Ajout de degrés des nodes adjacent
                Sign_Vect[indice_nodes_encours].NodeAdjacent = new int[Sign_Vect[indice_nodes_encours].degre];

                // System.out.println("fd"+k+Sign_Vect[k].degre);
                for (int l = 0; l < Sign_Vect[indice_nodes_encours].degre; l++) {
                    Sign_Vect[indice_nodes_encours].EdgesAttr[l] = new Vector();
                }
                for (int l = 0; l < graph.getChildCount(); l++) {
                    GXLElement b1 = graph.getChildAt(l);
                    if (b1 instanceof GXLEdge) {
                        GXLEdge bb = (GXLEdge) b1;
                        //System.out.println("Edge ID "+bb.getSourceID()+" - "+bb.getTargetID());
                        if ((bb.getSourceID().equals(node.getID())) || ((bb.getTargetID().equals(node.getID())))) {
                            if (bb.getSourceID().equals(node.getID())) {
                                nodesAdj[indice_nodes_encours].add(bb.getSourceID());
                            }
                            if (bb.getTargetID().equals(node.getID())) {
                                nodesAdj[indice_nodes_encours].add(bb.getTargetID());
                            }
                            
                            Vector vec = new Vector();
                            //System.out.println("Edge attribut count: "+bb.getChildCount());
                            for (int j = 0; j < bb.getChildCount(); j++) {
                                GXLAttr GAT = (GXLAttr) bb.getChildAt(j);
                                
                                if (GAT.getValue() instanceof GXLInt) {
                                    GXLInt fx = (GXLInt) GAT.getValue();
                                    vec.add(fx.getIntValue());
                                    //System.out.println(fx.getIntValue());
                                } else if (GAT.getValue() instanceof GXLFloat) {
                                    GXLFloat fy = (GXLFloat) GAT.getValue();
                                    vec.add(fy.getFloatValue());
                                    //System.out.println(fy.getFloatValue());
                                } else if (GAT.getValue() instanceof GXLString){
                                    GXLString fz = (GXLString) GAT.getValue();
                                    vec.add(fz.getValue());
                                    //vec.add((GXLString) GAT.getValue());
                                    
                                    //System.out.println(j+" ieme attribut of "+bb.getSourceID()+" ieme edge "+fz.getValue());
                                }
                                //vec.add(bb.getChildAt(j));
                               
                                
                            }
                            // System.out.println("fd"+k+m);

                            Sign_Vect[indice_nodes_encours].EdgesAttr[m].add(vec);
                            //System.out.println(Sign_Vect[indice_nodes_encours].EdgesAttr[m]);
                            m++;
                        }
                    }
                }
                indice_nodes_encours++;
            }
        }
        //System.out.println(NbreNode);
        //System.out.println();
        for (int k = 0; k < NbreNode; k++) {

            Vector Temp1 = nodesAdj[k];
           // System.out.println(Temp1.size());
           //System.out.println(nodesAdj[k].size());
            for (int i = 0; i < Temp1.size(); i++) {
                String nn = (String) Temp1.get(i);
                int ind = nod.indexOf(nn);
                //System.out.println(nod.indexOf(nn));
                int ss = nodesAdj[ind].size();
                Sign_Vect[k].NodeAdjacent[i] = ss;
                //System.out.println(Sign_Vect[k].NodeAdjacent[i]);
            }

           

        }
         /*for (int i = 0; i < NbreNode; i++){
                System.out.println("node: "+(i+1)+" degree of node adjacent: "+Sign_Vect[i].NodeAdjacent[0]);
             }*/

    }
    /*
     *
     *
     *              Vector Temp1 = nodesAdj[k];
    String strTemp1 = (String) Temp1.get(i);
    for (int j = 0; j < NbreNode; j++) {
    for (int h = 0; h < Sign_Vect[j].degre; h++) {
    Vector Temp3 = nodesAdj[h];
    String strTemp3 = (String) Temp3.get(i);
    }
    Vector Temp2 = nodesAdj[j];
    String strTemp2 = (String) Temp2.get(j);
    if (strTemp1.equalsIgnoreCase(strTemp)) {
    }




    }*/
    /* public void Remplir_signature2()
    {
    Sign_Vect=new Signature[NbreNode];

    for (int k= 0; k < graph.getChildCount(); k++)
    {
    GXLNode node;
    GXLElement b = graph.getChildAt(k);
    if (b instanceof GXLNode) {
    node = (GXLNode) graph.getChildAt(k);
    Sign_Vect[k]=new Signature();

    for(int j=0;j< b.getChildCount();j++)
    {

    GXLAttr GA=(GXLAttr)node.getChildAt(j);


    if(GA.getValue() instanceof GXLInt)
    {
    GXLInt fx=(GXLInt)GA.getValue();

    Sign_Vect[k].NodeAttr.add(fx.getIntValue());}
    else if(GA.getValue()instanceof GXLFloat)
    {
    GXLFloat fy=(GXLFloat)GA.getValue();

    Sign_Vect[k].NodeAttr.add(fy.getFloatValue());
    }
    else if(GA.getValue() instanceof GXLString)
    {
    GXLString fz=(GXLString)GA.getValue();

    Sign_Vect[k].NodeAttr.add(fz.getValue());

    }

    else
    {
    GXLFloat fy=(GXLFloat)GA.getValue();

    Sign_Vect[k].NodeAttr.add(fy.getFloatValue());

    }
    }
    for (int l= 0;  l< graph.getChildCount(); l++) {

    GXLElement b1 = graph.getChildAt(l);

    if (b1 instanceof GXLEdge) {

    GXLEdge bb=(GXLEdge)b1;



    if((bb.getSourceID().equals(node.getID()))||((bb.getTargetID().equals(node.getID()))))
    {
    Sign_Vect[k].degre++;

    Vector vec=new Vector();
    for(int j=0;j< bb.getChildCount();j++)
    {
    GXLAttr GAT=(GXLAttr)bb.getChildAt(j);
    if(GAT.getValue() instanceof GXLInt)
    {

    GXLInt fx=(GXLInt)GAT.getValue();

    Sign_Vect[k].EdgesAttr.add(fx.getIntValue());}
    else if(GAT.getValue()instanceof GXLFloat)
    {
    GXLFloat fy=(GXLFloat)GAT.getValue();

    Sign_Vect[k].EdgesAttr.add(fy.getFloatValue());
    }
    else if(GAT.getValue() instanceof GXLString)
    {
    GXLString fz=(GXLString)GAT.getValue();

    Sign_Vect[k].EdgesAttr.add(fz.getValue());

    }
    Sign_Vect[k].EdgesAttr.add(bb.getChildAt(j));
    }
    // Sign_Vect[k].EdgesAttr.add(vec);
    }
    }


    }
    }
    }}*/
}
