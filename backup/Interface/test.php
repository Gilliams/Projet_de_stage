<?php include 'header.php'; ?>
    <style>
        .row{
            margin-right: 0px;
            margin-left: 0px;
        }
        h2{
            text-align: center;
        }
        p{
            display: inline-block;
            margin: 0px 30px 0px 30px;

        }
        li{list-style: none}
        .col-md-8{
            padding-left: 0px;
            padding-right: 0px;
        ;
        }
        .col-md-6{
            padding-left: 0px;
            padding-right: 0px;
        }
        .folder{
            text-align: center;
            background-color: #ffe9e9;
            border: 1px solid black;
        }

    </style>
    <container-fluid id="apptest">
        <h1>Page de test ;)</h1>
        <div class="row" >
            <div class="col-md-8">
                <h2>Categorie</h2>
                <div class="row">
                    <router-link to="/Cate1"><button class="col-md-2 btn btn-default">Categorie 1</button></router-link>
                    <router-link to="/Cate2"><button class="col-md-2 btn btn-default">Categorie 2</button></router-link>

                    <button class="col-md-2 btn btn-default">Categorie 3</button>
                    <button class="col-md-2 btn btn-default">Categorie 4</button>
                </div>
            </div>
        </div>
        <affichage></affichage>
        <router-view></router-view>

    </container-fluid>

<?php include 'footer.php'; ?>