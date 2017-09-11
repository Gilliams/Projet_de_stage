let component_1 = {
    template: `
        <div>
            <h2>TreeView</h2>
            <ul>
                <li v-for="l in list">
                {{l.name}}
                <router-link :to="{name: l.name}">{{l.name}}</router-link>
                </li>
                <input type="text" v-model="newList" @keyup.enter="addList">
            </ul>
                <button @click="consolog">console</button>
                <router-link to="/" tag="button">Retour</router-link>
                <router-view></router-view>
        </div>
    `,
    props: ['message'],
    data () {
        return {
            list: [
                {name: 'Mao'},
                {name: 'Alkane'},
                {name: 'Acyclic'},
            ],
            newList: ''
        }
    },
    methods: {
        addList() {
            this.list.push({name: this.newList})
            router.push({path: '/' + this.newList + '/', component:'Parents', name: this.newList})
            this.newList = ''
            console.log(this.$router)
            console.log(this.list)
        },
        consolog(){
            console.log(this.$router)
            console.log(this.list)
        }
    }
}
let Parents = {
    template: `<div>
        <h2>Parents</h2>
        <ul>
        <li v-for="e in parentlist">{{e.name}}</li>
        </ul>
        {{msg}}
    </div>`,
    data () {
        return {
            parentlist: [
                {name: 'fichier_ct1'},
                {name: 'fichier_ct2'},
                {name: 'fichier_ct3'}
            ],
            msg: 'Hello'
        }
    },
     beforeRouteEnter(to, from, next) {
        if(from === 'Mao'){
            console.log("I'm Mao")
        }
    }
};

let Alkane = {
  template: `
    <div>
        <p>Enfant</p>
    </div>
  `
};

const router = new VueRouter({
    routes: [
        { path: '/Mao/', component: Parents, name: 'Mao'},
        { path: '/Alkane/', component: Parents, name: 'Alkane'},
        { path: '/Acyclic/', component: Parents, name: 'Acyclic'},
    ]
});


const v = new Vue({
    el: '#treeview',
    components: {
        'component_1': component_1
    },
    data: {
        message: 'hello'
    },
    router
});
