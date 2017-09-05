Vue.use(VueRouter)

const Ca1 = {
    template: '<div>\
                    <h2>Doosier 1</h2>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link to="/Cate1/cateA"><button class="btn btn-primary">Dossier A</button></router-link>\
                    <router-link to="/Cate1/cateA"><button class="btn btn-primary">Dossier B</button></router-link>\
                    <router-link to="/Cate1/cateA"><button class="btn btn-primary">Dossier C</button></router-link>\
              </div>'
}
const Ca2 = {
    template: '<div>\
                    <h2>Doosier 2</h2>\
                    <?php include "JQFU/index.html" ;?>\
                    <router-link to="/"><button class="btn btn-default">Retour au début</button></router-link>\
                    <router-link to="/Cate2/cateB">CateA</router-link>\
              </div>'
}
const A = {
    template: '<div>\
                    <h2>Fichier</h2>\
                    <router-link to="/Cate1/"><button class="btn btn-danger">Retour</button></router-link>\
                    <router-link to="/Cate1/cateA/test"><button class="btn btn-warning">Fichier A</button></router-link>\
                    <router-link to="/Cate1/cateA/test"><button class="btn btn-warning">Fichier B</button></router-link>\
                    <router-link to="/Cate1/cateA/test"><button class="btn btn-warning">Fichier C</button></router-link>\
                    <router-link to="/Cate1/cateA/test"><button class="btn btn-warning">Fichier D</button></router-link>\
              </div>'
}
const B = {
    template: '<div>\
                     <h2>Fichier du Dossier B</h2>\
                     <router-link to="/">Retour au début</router-link>\
              </div>'
}
const C = {
    template: '<div>\
                     <h2>Fichier du Dossier C</h2>\
                     <router-link to="/">Retour au début</router-link>\
              </div>'
}
const D = {
    template: '<div>\
                     <h2>Fichier du Dossier D</h2>\
                     <router-link to="/">Retour au début</router-link>\
              </div>'
}
const test = {
    template: '<div>\
                    <h1>{{ $route.params.id }}</h1>\
                    <router-link to="/Cate1/cateA/"><button class="btn btn-default">Retour au dossier</button></router-link>\
                </div>'
}

const router = new VueRouter({
    routes: [
        {
            path: '/Cate1',
            component: Ca1
        },
        {
            path: '/Cate2',
            component: Ca2
        },
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
