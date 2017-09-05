<?php include './php/header.php' ?>
    <h1>Graph</h1>
<div id="container"></div>

<script>
    sigma.parsers.json('data.json', {
        container: 'container',
        settings: {
            defaultNodeColor: '#ec5148'
        }
    });
</script>
<?php include './php/footer.php' ?>



