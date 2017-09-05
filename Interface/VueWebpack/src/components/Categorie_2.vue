<template>
    <div class="categorie2 ">
        <h2 class="cat">Dosier de la categorie 2</h2>
        <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>
        <router-link :to="{name: 'Categorie2.d1'}"><button class="btn btn-primary">Dossier A</button></router-link>
        <router-link :to="{name: 'Categorie2.d2'}"><button class="btn btn-primary">Dossier B</button></router-link>
        <router-view></router-view>
    </div>
</template>

<script>
    import Dossier1 from './Dossier_1.vue'
    import Dossier2 from './Dossier_2.vue'
    export default {
      components: {Dossier1, Dossier2},
      name: 'categorie2'
    }
</script>

<style scoped="">
.cat{
    background-color: indianred;}
</style>