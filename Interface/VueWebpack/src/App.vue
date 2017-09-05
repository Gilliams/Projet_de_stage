<template>
  <div id="app">
    <h1 class="cat">Categorie</h1>
    <router-link to="../Categorie1"><button class="col-md-2 btn btn-default">Categorie 1</button></router-link>
    <!--<router-link to="../Categorie2"><button class="col-md-2 btn btn-default">Categorie 2</button></router-link>-->
    <test></test>
    <router-view></router-view>
  </div>
</template>

<script>
  import Categorie1 from './components/Categorie_1.vue'
  import Categorie2 from './components/Categorie_2.vue'
  import test from './components/test.vue'
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap-vue/dist/bootstrap-vue.css'

export default {
    components: {Categorie1, Categorie2, test},
    name: 'app'
}
</script>

<style scoped="app">
.cat{
  background-color: cornflowerblue;}

</style>
