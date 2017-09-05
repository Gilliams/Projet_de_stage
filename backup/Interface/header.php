<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Florian">
    <title>ProjetStage</title>

    <link rel="stylesheet" href="../Inc/lib/bootstrap.min.css" >
    <link rel="stylesheet" href="../Inc/css/style.css" >
    <script type="text/javascript" src="../Inc/lib/vue.min.js"></script>
    <script type="text/javascript" src="../Inc/lib/vue-router.js"></script>
    <script type="text/javascript" src="../Inc/lib/axios.min.js"></script>
<!--    Affiche le temps de chargement de la page-->
    <script type="text/javascript" src="../Interface/js/chargement_page.js"></script>

</head>

<body onLoad="myFunction()">
   <div id="loader">
        <section class="section section-4">
            <span class="loadder loadder-bars"><span></span></span>
            Chargement...
        </section>
   </div>
    <div style="display:none;" id="myDiv" class="animate-bottom">
        <nav class="navbar navbar-inverse navbar-fixed-top ">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="home.php">ProjetStage</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="home.php">Home</a></li>
                        <li><a href="about.php">About</a></li>
                        <li><a href="calcul.php">Calcul</a></li>
                        <li><a href="resultat.php">Resultat</a></li>
                        <li><a href="contact.php">Contact</a></li>
                        <li><a href="test.php">Test</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        <div class="container" id="lavue">