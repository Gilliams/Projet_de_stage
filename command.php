<?php
    echo "Affiche le directory :  " .getcwd() . "<br>". "<br>";
        chdir('C:\Users\fc03\workspace\Stage\src');

    echo "Affiche le directory actuel :  ". getcwd() . "<br>". "<br>";
//    echo "Affiche le resultat du programme". "<br>"."<br>" .
    shell_exec('java graph_embedding/Main Alkane output3.txt 2');

$folder = 'C:\Users\fc03\workspace\Stage\src';
$result = scandir($folder);
//echo "<pre>";
var_dump($result);
//echo "</pre>";
echo "<br>"."<br>";
echo "Fichier ou dossier dans le dossier SRC"."<br>";
$handle = opendir('C:\Users\fc03\workspace\Stage\src');
while (false !== ($entry = readdir($handle))) {
//    echo "<pre>";
    echo "$entry\n";
//    echo "</pre>";
}

echo "<br>"."<br>";
closedir($handle);
$fichier = "\output3.txt";

echo "Ouvre le premier fichier"."<br>";
$monfichier = fopen('C:\Users\fc03\workspace\Stage\src'.$fichier, 'r+');
echo $monfichier;

$ligne = fgets($monfichier);
echo $ligne;
$fichierContenu = '\molecule013.ct';
$homepage = file_get_contents('C:\Users\fc03\workspace\Stage\src\Alkane'.$fichierContenu);

echo "<pre>";
echo $homepage;
echo "</pre>";
fclose($monfichier);
