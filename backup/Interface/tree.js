var child = {
    template: '<li class="child">\
                    <img class="icone" src="../Inc/img/icone/SVG/chevron_down.svg" alt="icone-ouvert" v-if="showChild" @click.stop="showChild = !showChild">\
                    <img class="icone" src="../Inc/img/icone/SVG/chevron_right.svg" alt="icone-fermé" v-else="showChild" @click.stop="showChild = !showChild">\
                    <span>{{title}}</span>\
                    <img class="icone" src="../Inc/img/icone/SVG/close.svg" alt="icone-close" @click="$emit(\'remove\')">\
                    <ul v-if="showChild" >\
                        <li class="grand-child" v-for="(name, value) in listChild" :name="listChild.name" :value="listChild.value">\
                            <form action="#" method="post">\
                                <input type="radio" name="files" :value="listChild.value" title="0"/>{{listChild.name}}\
                            </form>\
                        </li>\
                    </ul>\
               </li>',
    data: function (){
        return {
            showChild: false,
            listChild: [
                { name: 'Fichier0.ct', value: '0'},
                { name: 'Fichier1.ct', value: '1'},
                { name: 'Fichier2.ct', value: '2'},
                { name: 'Fichier3.ct', value: '3'},
            ]
        }
    },
    props:{
        title: String,
        name: String,
        value: String
    }
};

var vm = new Vue({
    el: '#tree',
    components: {
        child
    },
    data:{
        show: false,
        NewChild: '',
        list:[
            {
                id: 0,
                title: 'MAO'
            },{
                id: 1,
                title: 'Alkane'
            }
        ],
        nextId: 2,
        name: 'test',
        value: 'valuetest'
    },
    methods:{
        add: function(){
            this.list.push({
                id: this.nextId++,
                title: this.NewChild
            })
            this.NewChild = ''
        }
    }
});