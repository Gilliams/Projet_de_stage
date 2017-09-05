<?php include "header.php"; ?>

<h1>Resultat</h1>
<div>
<?php
//echo "Affiche le directory :  " . "<pre>" . getcwd() . "</pre>" . "<br>";
chdir('C:\Users\fc03\workspace\Stage\src');

//echo "Affiche le directory actuel :  ". "<pre>" . getcwd() . "</pre>" . "<br>";



$folder = "bdd/Alkane";
$fichier = "\output2.txt";

echo "Affiche le resultat du programme". "<br>"."<br>" ;

echo "<pre>";
echo shell_exec('java -Xmx1000m graph_embedding.Main '.$folder. ' '. $fichier.' 0.98');
echo "</pre>";



echo "<br>";
    echo "Fichier ou dossier dans le dossier Stage"."<br>";
$handle = opendir('C:\Users\fc03\workspace\Stage');
while (false !== ($entry = readdir($handle))) {
    echo "<pre>" . $entry . "</pre>";
}
closedir($handle);
echo "<br>";

$monfichier = fopen('C:\Users\fc03\workspace\Stage\src'.$fichier, 'r+');

$homepage = file_get_contents('C:\Users\fc03\workspace\Stage\src'.$fichier);
    echo "Ouverture du fichier : ".$fichier."<br>";
echo "<pre>";
echo $homepage;
echo "</pre>";

$homepaage = file_get_contents('C:\Users\fc03\workspace\Stage\src\graph_embedding\Main.class');
    echo "Ouverture du fichier : Main.java."."<br>";
echo "<pre>";
echo $homepaage;
echo "</pre>";

?>
</div>
<?php include "footer.php"; ?>

