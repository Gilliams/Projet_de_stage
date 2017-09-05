<template>
    <div class="dossier2">
        <h2>Fichier du dossier 2</h2>
        <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>
        <router-link :to="{name: 'Categorie2.d2.f1'}">
            <button class="btn btn-primary">Categorie2.Dossier2.Fichier1</button>
        </router-link>
        <router-link :to="{name: 'Categorie2.d2.f2'}">
            <button class="btn btn-primary">Categorie2.Dossier2.Fichier2</button>
        </router-link>
        <router-view></router-view>
    </div>
</template>
<script>
    import Fichier1 from './Fichier_1.vue'
    import Fichier2 from './Fichier_2.vue'
    export default {
      components: {Fichier1, Fichier2},
      name: 'dossier2'
    }
</script>

<style>

</style>