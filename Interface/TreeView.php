<?php include './php/header.php'; ?>
    <link rel="stylesheet" href="tree.css">
<h1>TreeView</h1>
<div class="col-md-3">
    <div id="treeview">
        <component_1></component_1>
    </div>
</div>

<?php if(!empty($_POST['envoi'])){echo $_POST['envoi'];} ?>
<?php
include './php/footer.php';
?>