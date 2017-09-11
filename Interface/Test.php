<?php include './php/header.php'; ?>
<?php //include './save/categorie_test.html'; ?>

<h2>Test Matrice</h2>
<?php
// Initialisation
$n=5; $p=5; 	 	 	   //nb lignes, nb colonnes
$mat=array($n);                   // $mat est un tableau de lignes
for ($i=0; $i<$n; $i++) {         // les indices de lignes vont de 0 à 4
    $mat[$i]=array($p);           // chaque ligne est tableau de nombres ou de chaines
    for ($j=0; $j<$p; $j++)
        $mat[$i][$j]="($i,$j)";   // initialisation avec les n° de ligne et colonne
        array_multisort($mat[$i], SORT_DESC);
    // à l'intérieur des guillemets on peut mettre des variables
} // fin de for $i
print "<pre>";
print_r($mat);                    // affichage rapide du tableau $mat
print "</pre>";


// Affichage sous forme de table  :
/*print "<table>";
for ($i=0; $i<$n; $i++) {
    print "<tr>";
    for ($j=0; $j<$p; $j++)
        print "<td width=50>" . $mat[$i][$j] . "</td>";
    print "</tr>";
}
print "</table>";*/

?>
<?php include './php/footer.php'; ?>