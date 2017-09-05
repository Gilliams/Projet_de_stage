<?php include './php/header.php'; ?>
    <link rel="stylesheet" href="tree.css">
<h1>TreeView</h1>
<div class="col-md-3">
    <div id="tree">
        <ul>
            <input type="text"
                   placeholder="Ajout enfant"
                   @keyup.enter="add"
                   v-model="NewChild"
            >
            <li class="parent">
                <img class="icone" src="../Inc/img/icone/SVG/chevron_down.svg" alt="icone" v-if="show" @click="show = !show">
                <img class="icone" src="../Inc/img/icone/SVG/chevron_right.svg" alt="icone" v-else="show" @click="show = !show">
                <span>Dossier</span>
                <ul v-if="show" >
                    <li is="child" v-for="(item,index) in list" :key="item.id" :title="item.title" @remove="list.splice(index, 1)" ></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<?php if(!empty($_POST['envoi'])){echo $_POST['envoi'];} ?>
<?php
include './php/footer.php';
?>