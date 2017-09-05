<?php include "./php/header.php"; ?>

<h1>Resultat</h1>
<div>
    <h2>Trie du tableau</h2>
    <form action="#" method="post">
        <input type="checkbox" name="option[]" value="Croissant" title="decroi" >Croissant
        <input type="checkbox" name="option[]" value="Decroissant" title="croi">Decroissant

        <input type="checkbox" name="files" value="0" title="0" >Fichier 0
        <input type="checkbox" name="files" value="1" title="1">Fichier 1
        <input type="checkbox" name="files" value="2" title="2">Fichier 2
        <input type="submit">
    </form>

<?php
//echo "Affiche le directory :  " . "<pre>" . getcwd() . "</pre>" . "<br>";
chdir('C:\xampp\htdocs\ProjetStage\Stage\src');
//echo "Affiche le directory actuel :  ". "<pre>" . getcwd() . "</pre>" . "<br>";
/**
 * Variables de dossiers/fichiers
 */
$folderCode = "Alkane";
$fichier = "output2.txt";
$folder = '\Acyclic';

echo "<br>"."<br>" ;
echo "Affiche le resultat du programme (S'il n'y a pas de résultat: echo à la ligne 53 :)". "<br>"."<br>" ;

echo "<pre>";
/**
 * Execute avec l'invite de commande le code en Java avec 3 arguments en paramétres ( [1: le dossier à lire] [2: le fichier de sortie] [3: pourcentage de succes] )
 */
echo shell_exec('java -Xmx1000m graph_embedding.Main '.$folderCode. ' '. $fichier.' 0.98');
echo "</pre>";
echo "<br>" ;
echo "Affiche le resultat du fichier :".$fichier. "<br>"."<br>" ;
/**
 * Lit le fichier cible et renvoie le résultat dans un tableau
 */
$names=file('C:\xampp\htdocs\ProjetStage\Stage\src\output2.txt');

/**
 * Déclarations des variables/tableaux
 */
$nb = count($names);
$longueur = '';
$premier = '';
$deuxieme = '';
$num = '';
$allFiles = array();
$selection = array();

/**
 * Lit le tableau $names qui contient chaque ligne du dossier Ct.
 * $longueur :: Correspond à la longueur en int, en partant du début de la chaine de char jusqu'au symbole ' = '
 * $premier :: Recupere la chaine de char du début jusqu'à $longueur -1 ( soit la partie qui nous interesse File[0] and File[1] )
 * $deuxieme :: Recupere la chaine de char à partir de $longueur +1 jusqu'à la fin de la chaine soit ( 2.732456684112549 )
 * Pour chaque ligne lu, le tableau $allFiles recoit $premier en tant que Cle et $deuxieme en tant que Valeur
 * Ce qui permet de faire un tri cle/valeur et non index/valeur
 */

foreach($names as $key => $value){

    $longueur = strpos($value, '=');
    $premier  = substr($value, 0, $longueur-1);
    $deuxieme = substr($value, $longueur +1);
    $allFiles += array($premier => $deuxieme);

}
/**
 * Trie les tableaux selon les choix : croissant ou décroissant
 */

foreach($_POST["option"] as $checkoption){
    if( $checkoption == 'Decroissant'){
        arsort($allFiles);
        arsort($selection);
    }else{
        asort($allFiles);
        asort($selection);
    }
}
/**
 * Affiche le tableau $allFiles trié
 */
/*echo "<pre>";
print_r($allFiles);
echo"</pre>"."<br>"."<br>";*/


/**
 * Les choix de fichiers
 */
    switch($_POST['files']){
        case '0':
            $num = '0';
            break;
        case '1':
            $num = '1';
            break;
        case 2:
            $num = '2';
            break;
        default:
            $num = '0';
            break;

    }
/**
 * Envoi dans le tableau $selection les éléments séléctionner, cela permet de faire un tri juste et plus facile.
 * Affiche le tableau $selection trié
 */
    foreach($allFiles as $key => $value) {
        $arret = substr($key, 5, 1);
        if ($arret == $num) {
            $selection += array($key => $value);
        }
    }

    echo "<pre>";
    print_r($selection);
    echo"</pre>";



?>
</div>
<?php include "php/footer.php"; ?>