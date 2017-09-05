/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matching_node_signatures_ICPR_Contest;

import java.util.Vector;
import java.io.*;
import java.util.*;

public class Dist_Signatures {

    public double Dist_S;

    Dist_Signatures() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //_______________________Distance metric between node signature________________________________________//
    /* Dist_Signatures (Signature S1,Signature S2)
    {

    float d_sign=0;
    float dist= Dist_Vect_HEOM(S1.NodeAttr,S2.NodeAttr);
    //  System.out.println("Dist NODEEEE"+dist);
    d_sign=dist;
    Vector v= new Vector();
    //System.out.println("Tailleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+S1.EdgesAttr.size());
    // System.out.println("voilaaaa");
    if(S1.EdgesAttr.size()!=0)
    {

    for(int i=0;i<((Vector)S1.EdgesAttr.get(0)).size();i++)
    v.add(null);
    }
    else if(S2.EdgesAttr.size()!=0)
    {
    //  System.out.println("IM HERRREEEEE");
    for(int i=0;i<((Vector)S2.EdgesAttr.get(0)).size();i++)
    v.add(null);
    }


    if(S2.EdgesAttr.size()!=0||S1.EdgesAttr.size()!=0)
    { if(S1.EdgesAttr.size()>=S2.EdgesAttr.size())
    for(int i=0;i<S1.EdgesAttr.size();i++)
    {
    if(i<S2.EdgesAttr.size())
    {
    float c=Dist_Vect_HEOM((Vector)S1.EdgesAttr.get(i),(Vector)S2.EdgesAttr.get(i));
    d_sign+=c;
    //     System.out.println("dsign"+d_sign);
    }

    else
    {
    float c=Dist_Vect_HEOM((Vector)S1.EdgesAttr.get(i),v);
    d_sign+=c;
    }

    }
    else

    for(int i=0;i<S2.EdgesAttr.size();i++)
    {

    if(i<S1.EdgesAttr.size())
    {
    float c=Dist_Vect_HEOM((Vector)S2.EdgesAttr.get(i),(Vector)S1.EdgesAttr.get(i));

    d_sign+=c;
    }
    else
    {    float c=Dist_Vect_HEOM((Vector)S2.EdgesAttr.get(i),v);
    d_sign+=c;
    }
    }
    }
    float d_degr= Math.abs(S1.degre-S2.degre)/200;
    d_sign+=d_degr*d_degr;

    // System.out.println("SIIIIIIIIIIIIIIIIIIIIIIIIIIIIIGGGGGGGGGNNNNNNNNNNNNATUR"+Math.sqrt(d_sign));
    Dist_S=Math.sqrt(d_sign);

    }*/
    Dist_Signatures(Signature S1, Signature S2) {

        float d_sign = 0, d_sign1, d_sign2 = 0;
        float dist = Dist_Vect_HEOM(S1.NodeAttr, S2.NodeAttr);
        //  System.out.println("Dist NODEEEE"+dist);
        d_sign = dist;
        Vector v = new Vector();
        //System.out.println("Tailleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+S1.EdgesAttr.size());
        // System.out.println("voilaaaa");
        if (S1.EdgesAttr.length != 0) {

            for (int i = 0; i < ((Vector) S1.EdgesAttr[0]).size(); i++) {
                v.add(null);
            }
        } else if (S2.EdgesAttr.length != 0) {
            //  System.out.println("IM HERRREEEEE");
            for (int i = 0; i < ((Vector) S2.EdgesAttr[0]).size(); i++) {
                v.add(null);
            }
        }



        float[][] ld = new float[S1.EdgesAttr.length][S2.EdgesAttr.length];

        if (S2.EdgesAttr.length != 0 || S1.EdgesAttr.length != 0) {
            for (int i = 0; i < S1.EdgesAttr.length; i++) {
                for (int j = 0; j < S2.EdgesAttr.length; j++) {
                    float c = Dist_Vect_HEOM(S1.EdgesAttr[i], S2.EdgesAttr[j]);
                    ld[i][j] = c;

                    //       d_sign+=c;
                    //     System.out.println("dsign"+d_sign);
                }
            }
        }

        if (S2.EdgesAttr.length == 0 && S1.EdgesAttr.length == 0) {
            d_sign1 = 0;
        } else {
            if (S2.EdgesAttr.length == 0 && S1.EdgesAttr.length != 0) {
                d_sign1 = S1.EdgesAttr.length;
            } else {
                if (S2.EdgesAttr.length != 0 && S1.EdgesAttr.length == 0) {
                    d_sign1 = S2.EdgesAttr.length;
                } else {
                    if (S2.EdgesAttr.length == 1 || S1.EdgesAttr.length == 1) {
                        d_sign1 = ld[0][0];
                    } else {
                        Hungarian meH = new Hungarian(ld);
                        d_sign1 = (Float) meH.H_sum;
                    }
                }
            }
        }


        /*************  --  LES degrés des noeuds adjacents ------  ********************/
        float[][] ldN = new float[S1.NodeAdjacent.length][S2.NodeAdjacent.length];

        if (S2.NodeAdjacent.length != 0 || S1.NodeAdjacent.length != 0) {
            for (int i = 0; i < S1.NodeAdjacent.length; i++) {
                for (int j = 0; j < S2.NodeAdjacent.length; j++) {
                    float c = (float) Math.abs(S1.NodeAdjacent[i] - S2.NodeAdjacent[j]);
                    ldN[i][j] = c;

                    //       d_sign+=c;
                    //  System.out.println("c  "+c);
                }
            }
        }

        if (S2.NodeAdjacent.length == 0 && S1.NodeAdjacent.length == 0) {
            d_sign2 = 0;
        } else {
            if (S2.NodeAdjacent.length == 0 && S1.NodeAdjacent.length != 0) {
                d_sign2 = somme(S1.NodeAdjacent); ////////*
            } else {
                if (S2.NodeAdjacent.length != 0 && S1.NodeAdjacent.length == 0) {
                    d_sign2 = somme(S2.NodeAdjacent);
                } else {
                    if (S2.NodeAdjacent.length != 0 || S1.NodeAdjacent.length != 0) { 
                        d_sign2 = distanceTab(S1.NodeAdjacent, S2.NodeAdjacent);
                        //System.out.println( " ddd  " + d_sign2);
                    }
                }
            }
        }


        d_sign += d_sign1;
        d_sign += d_sign2;
        //System.out.println(d_sign2);
        float d_degr = Math.abs(S1.degre - S2.degre) / 20;
        d_sign += d_degr * d_degr;

        // System.out.println("SIIIIIIIIIIIIIIIIIIIIIIIIIIIIIGGGGGGGGGNNNNNNNNNNNNATUR"+Math.sqrt(d_sign));
        Dist_S = Math.sqrt(d_sign);

    }

    //_______________________________Calcul de la distance entre deux vecteurs_____________________________//
    public int somme(int[] tab) {
        int sun = 0;
        for (int i = 0; i < tab.length; i++) {
            sun += tab[i];
        }
        return sun;
    }

    public float distanceTab(int[] t1, int[] t2) {
        int[] tab1 = t1;
        int[] tab2 = t2;
        float d = 0;
        Arrays.sort(tab2);
        Arrays.sort(tab2);

        if (tab1.length > tab2.length) {
            for (int i = 0; i < tab2.length; i++) {
                d += Math.abs(tab1[i] - tab2[i]);
            }
            for (int i = tab2.length; i < tab1.length; i++) {
                d += tab1[i];
            }
        }
        if (tab1.length < tab2.length) {
            for (int i = 0; i < tab1.length; i++) {
                d += Math.abs(tab1[i] - tab2[i]);
            }
            for (int i = tab1.length; i < tab2.length; i++) {
                d += tab2[i];
            }
        }

        if (tab1.length == tab2.length) {
            for (int i = 0; i < tab1.length; i++) {
                d += Math.abs(tab1[i] - tab2[i]);
            }

        }

        d = (float) Math.sqrt(d);
        return d;

    }

    public float Dist_Vect_HEOM(Vector v1, Vector v2) {
        int max;
        float d = 0;
        float Dist = 0;
        if (v1.size() < v2.size()) {
            max = v2.size();
            for (int i = v1.size(); i < v2.size(); i++) {
                v1.add(i, null);
            }
        } else {
            max = v1.size();
            for (int i = v2.size(); i < v1.size(); i++) {
                v2.add(i, null);
            }
        }


        // System.out.println("size v1 ="+v1.size()+" size v2 = "+v2.size()+" max = "+max);
        for (int i = 0; i < max; i++) {

            if (v1.get(i) == null || v2.get(i) == null) {
                d = 1;

            } else if (v1.get(i) instanceof String) {
                if (!((String) v2.get(i)).equals((String) v1.get(i))) {
                    d = 1;
                }

            } else if (v1.get(i) instanceof Integer) {

                d = (float) (Math.abs((Integer) v1.get(i) - (Integer) v2.get(i))) / 20;
                //   System.out.println("HERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRE"+(Integer)v1.get(i)+"- "+(Integer)v2.get(i));
            } else if (v1.get(i) instanceof Float) {

                d = (Float) (Math.abs((Float) v1.get(i) - (Float) v2.get(i))) / 20;

            }
            Dist += d * d;
        }


        return (Dist);
    }

    public float Dist_Vect_HEOM1(Vector v1, Vector v2) {
        int max;
        float d = 0;
        float Dist = 0;
        if (v1.size() < v2.size()) {
            max = v2.size();
            for (int i = v1.size(); i < v2.size(); i++) {
                v1.add(i, null);
            }
        } else {
            max = v1.size();
            for (int i = v2.size(); i < v1.size(); i++) {
                v2.add(i, null);
            }
        }


        // System.out.println("size v1 ="+v1.size()+" size v2 = "+v2.size()+" max = "+max);
        for (int i = 0; i < max; i++) {

            if (v1.get(i) == null || v2.get(i) == null) {
                d = 1;

            } else if (v1.get(i) instanceof String) {
                if (!((String) v2.get(i)).equals((String) v1.get(i))) {
                    d = 1;
                }

            } else if (v1.get(i) instanceof Integer) {

                d = (float) (Math.abs((Integer) v1.get(i) - (Integer) v2.get(i))) / 20;
                //   System.out.println("HERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRE"+(Integer)v1.get(i)+"- "+(Integer)v2.get(i));
            } else if (v1.get(i) instanceof Float) {

                d = (Float) (Math.abs((Float) v1.get(i) - (Float) v2.get(i))) / 20;

            }
            Dist += d * d;
        }


        return (Dist);
    }
    /*
    ///pas impoortant mnt
    public float Distance_nodes(Vector V1,Vector V2)
    {
    float Dman=0,Dsym=0;
    for(int i=0;i<V1.size();i++)
    {


    if(V1.get(i)instanceof String)
    {
    ///////////////////////////////////////////////////////////////////////////Overlap
    if(!((String)V1.get(i)).equals(((String)V2.get(i))))
    Dsym++;
    }

    else if(V1.get(i)instanceof Integer)
    {
    Dman+=Math.abs((Integer)V1.get(i)-(Integer)V2.get(i));
    }
    else if(V1.get(i)instanceof Float)
    {
    Dman+=Math.abs((Float)V1.get(i)-(Float)V2.get(i));
    }

    }
    return (float)(Dman+Dsym)/2;
    }

     */
}
