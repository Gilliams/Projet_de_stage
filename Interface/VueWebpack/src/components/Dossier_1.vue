<template>
    <div class="dossier1">
        <h2 class="cat">Fichier du dossier 1</h2>
        <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>
        <router-link :to="{name: 'Categorie2.d1.f1'}">
            <button class="btn btn-primary">Categorie1.Dossier1.Fichier1</button>
        </router-link>
        <router-link :to="{name: 'Categorie2.d1.f2'}">
            <button class="btn btn-primary">Categorie1.Dossier1.Fichier1</button>
        </router-link>
        <!--<router-link :to="{name: 'Categorie1.Dossier1.Fichier2'}">-->
            <!--<button class="btn btn-primary">Categorie1.Dossier1.Fichier2</button>-->
        <!--</router-link>-->
        <!--<router-link :to="{name: 'Categorie2.Dossier1.Fichier1'}">-->
            <!--<button class="btn btn-primary">Categorie2.Dossier1.Fichier1</button>-->
        <!--</router-link>-->
        <!--<router-link :to="{name: 'Categorie2.Dossier1.Fichier2'}">-->
            <!--<button class="btn btn-primary">Categorie2.Dossier1.Fichier2</button>-->
        <!--</router-link>-->
        <router-view></router-view>
    </div>
</template>
<script>
    import Fichier1 from './Fichier_1.vue'
    import Fichier2 from './Fichier_2.vue'
    export default {
      components: {Fichier1, Fichier2},
      name: 'dossier1'
    }
</script>

<style scoped="">
    .cat{
        background-color: forestgreen;}
</style>