<?php include './php/header.php'; ?>
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
        li{
            /*list-style: none;*/
            margin-top: 20px;
        }
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
                    <ul>
                        <li><router-link to="/Categorie1"><button >Categorie 1</button></router-link></li>
                        <li><router-link to="/Categorie2"><button >Categorie 2</button></router-link></li>
                    </ul>
            </div>
        </div>
        <router-view></router-view>
    </container-fluid>

<?php include './php/footer.php'; ?>