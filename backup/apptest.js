Vue.use(VueRouter)

const Categorie1 = {
    template: '<div>\
                    <h2>Dosier 1</h2>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier1\'}"><button class="btn btn-primary">Dossier A</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier2\'}"><button class="btn btn-primary">Dossier B</button></router-link>\
                    <router-view></router-view>\
              </div>'
}
const Categorie2 = {
    template: '<div>\
                    <h2>Dosier 2</h2>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier1\'}"><button class="btn btn-primary">Dossier A</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier2\'}"><button class="btn btn-primary">Dossier B</button></router-link>\
                    <router-view></router-view>\
              </div>'
}

const Dossier1 = {
    template: '<div>\
                    <h2>Fichier </h2>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier1.Fichier1\'}"><button class="btn btn-primary">Categorie1.Dossier1.Fichier1</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier1.Fichier2\'}"><button class="btn btn-primary">Categorie1.Dossier1.Fichier2</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier1.Fichier1\'}"><button class="btn btn-primary">Categorie2.Dossier1.Fichier1</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier1.Fichier2\'}"><button class="btn btn-primary">Categorie2.Dossier1.Fichier2</button></router-link>\
                    <router-view></router-view>\
              </div>'
}

const Dossier2 = {
    template: '<div>\
                    <h2>Fichier </h2>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier1.Fichier1\'}"><button class="btn btn-primary">Categorie1.Dossier2.Fichier1</button></router-link>\
                    <router-link :to="{name: \'Categorie1.Dossier1.Fichier2\'}"><button class="btn btn-primary">Categorie1.Dossier2.Fichier2</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier1.Fichier1\'}"><button class="btn btn-primary">Categorie2.Dossier2.Fichier1</button></router-link>\
                    <router-link :to="{name: \'Categorie2.Dossier1.Fichier2\'}"><button class="btn btn-primary">Categorie2.Dossier2.Fichier2</button></router-link>\
                    <router-view></router-view>\
              </div>'
}
const Fichier1 = {
    template: '<div>\
                    <h2>Fichier contenu </h2>\
                    <router-link to="./"><button class="btn btn-default">Retour </button></router-link>\
                    <p>Blabla</p>\
              </div>'
}

const Fichier2 = {
    template: '<div>\
                    <h2>Fichier contenu </h2>\
                    <router-link to="./"><button class="btn btn-default">Retour </button></router-link>\
                    <p>BlaBlaBla</p>\
              </div>'
}

// const test = {
//     template: '<div>\
//                     <h1>{{ $route.params.id }}</h1>\
//                     <router-link to="/Cate1/cateA/"><button class="btn btn-default">Retour au dossier</button></router-link>\
//                 </div>'
// }

const router = new VueRouter({
    routes: [
        {
            path: '/Categorie1',
            component: Categorie1,
            name: 'categorie1',
            children:[{
                path: 'Dossier1',
                component: Dossier1,
                name: 'Categorie1.Dossier1',
                children:[{
                    path: 'Fichier1',
                    component: Fichier1,
                    name: 'Categorie1.Dossier1.Fichier1'
                },{
                    path: 'Fichier2',
                    component: Fichier2,
                    name: 'Categorie1.Dossier1.Fichier2'
                }]
            },{
                path: 'Dossier2',
                component: Dossier2,
                name: 'Categorie1.Dossier2',
                children:[{
                    path: 'Fichier1',
                    component: Fichier1,
                    name: 'Categorie1.Dossier2.Fichier1'
                },{
                    path: 'Fichier2',
                    component: Fichier2,
                    name: 'Categorie1.Dossier2.Fichier2'
                }]
            }]
        },{
            path: '/Categorie2',
            component: Categorie2,
            name: 'categorie2',
            children:[{
                path: 'Dossier1',
                component: Dossier1,
                name: 'Categorie2.Dossier1',
                children:[{
                    path: 'Fichier1',
                    component: Fichier1,
                    name: 'Categorie2.Dossier1.Fichier1',
                },{
                    path: 'Fichier2',
                    component: Fichier2,
                    name: 'Categorie2.Dossier1.Fichier2',
                }]
            },{
                path: 'Dossier2',
                component: Dossier2,
                name: 'Categorie2.Dossier2',
                children:[{
                    path: 'Fichier1',
                    component: Fichier1,
                    name: 'Categorie2.Dossier2.Fichier1',
                },{
                    path: 'Fichier2',
                    component: Fichier2,
                    name: 'Categorie2.Dossier2.Fichier2',
                }]
            }]
        }

        ]
})

let vu = new Vue({
    el: '#apptest',
    router,
    data: {
        alert: false,
        message: "Voila mon text message",
    },
    methods: {
        showAlert: function () {
            this.alert = true
        },
        hideAlert: function () {
            this.alert = false
        },
    }
})
