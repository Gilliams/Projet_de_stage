import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    }, {
      path: '/Categorie1',
      component: require('../components/Categorie_1.vue'),
      name: 'Categorie1',
      children: [{
        path: 'd1',
        component: require('../components/Dossier_1.vue'),
        name: 'Categorie1.d1',
        children: [{
          path: 'f1',
          component: require('../components/Fichier_1.vue'),
          name: 'Categorie1.d1.f1'
        }, {
          path: 'f2',
          component: require('../components/Fichier_2.vue'),
          name: 'Categorie1.d1.f2'
        }]
      }, {
        path: 'd2',
        component: require('../components/Dossier_2.vue'),
        name: 'Categorie1.d2',
        children: [{
          path: 'f1',
          component: require('../components/Fichier_1.vue'),
          name: 'Categorie1.d2.f1'
        }, {
          path: 'f2',
          component: require('../components/Fichier_2.vue'),
          name: 'Categorie1.d2.f2'
        }]
      }]
    }, {
      path: '/Categorie2',
      component: require('../components/Categorie_2.vue'),
      name: 'Categorie2',
      children: [{
        path: 'd1',
        component: require('../components/Dossier_1.vue'),
        name: 'Categorie2.d1',
        children: [{
          path: 'f1',
          component: require('../components/Fichier_1.vue'),
          name: 'Categorie2.d1.f1'
        }, {
          path: 'f2',
          component: require('../components/Fichier_2.vue'),
          name: 'Categorie2.d1.f2'
        }]
      }, {
        path: 'd2',
        component: require('../components/Dossier_2.vue'),
        name: 'Categorie2.d2',
        children: [{
          path: 'f1',
          component: require('../components/Fichier_1.vue'),
          name: 'Categorie2.d2.f1'
        }, {
          path: 'f2',
          component: require('../components/Fichier_2.vue'),
          name: 'Categorie2.d2.f2'
        }]
      }]
    }
  ]
})
