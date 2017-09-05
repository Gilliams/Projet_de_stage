<template>
    <div class="categorie1">
        <h2>Dosier de la categorie 1</h2>
        <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>
        <router-link :to="{name: 'Categorie1.d1'}"><button class="btn btn-primary">Acyclic</button></router-link>
        <router-link :to="{name: 'Categorie1.d2'}"><button class="btn btn-primary">Alkane</button></router-link>
        <router-link :to="{name: 'Categorie1.d2'}"><button class="btn btn-primary">MAO</button></router-link>
        <router-link :to="{name: 'Categorie1.d2'}"><button class="btn btn-primary">PAH</button></router-link>
        <router-link :to="{name: 'Categorie1.d2'}"><button class="btn btn-primary">PTC</button></router-link>
        <router-link :to="{name: 'Categorie1.d2'}"><button class="btn btn-primary">Regression</button></router-link>
        <router-view></router-view>
    </div>
</template>

<script>
    import Dossier1 from './Dossier_1.vue'
    import Dossier2 from './Dossier_2.vue'
    export default {
      components: {Dossier1, Dossier2},
      name: 'categorie1'
    }
</script>

<style>

</style>