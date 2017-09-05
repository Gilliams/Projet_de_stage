var url = 'http://localhost/ProjetStage/Interface/index.php';

//variable qui va permettre de gérer la distinction entre le clic et le double-clic
var timer = null;
Vue.config.devtools = true

var List = {
	template: '<div v-if="list"><h1 v-html="list.name"></h1></div>',
	created: function(){
		var _this = this;
		axios.get(url+'?action=getlist&id='+this.$route.params.id).then(function(response){
			_this.list = response.data;
		});
	},
	data: {
		list: null
	}
}


// @click="$emit(\'childclicked\', list)"\
Vue.component('todo-list', {
	template: '<li>\
					<button class="btn btn-default" \
					@click="$emit(\'childclicked\', list)"\
					@dblclick="editList(list)" v-html="list.name" v-if="! list.edited">\
					</button>\
					<input type="text" v-model="list.newname" v-else @keyup.esc="cancelEdit(list)" @keyup.enter="updateList(list)"/>\
					<button @click="supprimeList(list)" class="btn btn-default">X</button>\
					<a href="Test.php"><button class="btn btn-default">Click-moi</button></a>\
					<p>\
    </p>\
				</li>',
	props: ['list'],
	methods: {
		supprimeList: function(list){
			/*
			* axios.delete permet d'exécuter une requête Ajax de type DELETE
			* splice est une fonction des tableaux qui permet de supprimer un ou des éléments à partir d'une position données.
			* le premier param de splice est donc l'index du premier élément à supprimer
			* le second param de splice est le nombre d'élément(s) à supprimer
			*/
			var _this = this;
			axios.delete(url+'?action=deletelist&id='+list.id).then(function(response){
				_this.$emit('deletelist', list);
			});
		},
		cancelEdit: function(list){
			/*
			* Vue.delete permet de faire l'inverse de Vue.set
			*/
			Vue.delete(list, 'edited');
			Vue.delete(list, 'newname');
		},
		updateList: function(list){
			if(list.newname.trim() != '' && list.newname != list.name){
				/*
				* axios.put permet
				*/
				var _this = this;
				axios.put(url+'?action=updatelist&id='+list.id, {name: list.newname}).then(function(response){
					list.name = list.newname;
					_this.cancelEdit(list);
				});
			}
		},
		editList: function(list){
        //permet d'annuler le timer déclenché par le setTimeout précédent
        clearTimeout(timer);
        /*
        * Vue.set permet d'ajouter dynamiquement une propriété
        * à un objet réactif (= bindé sur l'html) de façon à
        * l'actualiser dynamiquement. Sans ça, on est obligé t'attendre le prochain tour de rafraîchissement de Vue.js
        */
        Vue.set(list, 'edited', true);
        Vue.set(list, 'newname', list.name);
    }
	}
});


var v = new Vue({
	data: {
		initialized: false,
		newlist: "",
		lists: [],
		currentList: null, // évalué comme false dans le v-if
		newtodo: "",
		// editedList: null,
	},
	created: function(){
		axios.get(url+'?action=lists').then(function(response){
			v.lists = response.data;
			v.initialized = true;
		});
	},
	methods: {
		createList: function(){
			/*
			* axios.post -> exécute une requête Ajax de type POST
			* le second paramètre correspond aux données qu'on envoie au serveur
			* push est une fonction permettant à un tableau d'ajouter à la fin de celui-ci un élément
			*/
			axios.post(url+'?action=newlist', {list: this.newlist}).then(function(response){
				var list = response.data;
				v.lists.push(list);
				v.showList(list, true);//permet d'afficher la liste immédiatement après l'avoir ajoutée
				v.newlist = "";//permet de vider l'input une fois que l'ajout est fait

			});
		},

		showList: function(list, fromcreation){
			//permet d'annuler le timer déclenché par le setTimeout précédent
			clearTimeout(timer);
			/*
			* lance un timer, la fonction de callback sera exécutée
			* dans 300 millisecondes sauf si quelqu'un annule le timer
			* d'ici là avec un clearTimeout
			*/

			if(fromcreation == undefined || ! fromcreation){
				timer = setTimeout(function(){
					/*
					* En affectant list à currentList on change la liste affichée dans le div todos
					*/
					v.currentList = list;
				}, 300);
			}
			else{
				v.currentList = list;
			}
		},
		createTodo: function(){
			/*
			* ne pas oublier qu'un todo appartient à une liste, il faut donc indiquer l'id de la liste dans laquelle on veut ajouter le todo
			*/
			axios.post(url+'?action=newtodo&id='+this.currentList.id, {todo: this.newtodo}).then(function(response){
					v.currentList.todos.push(response.data);
					v.newtodo = "";
			});
		},
		supprimeTodo: function(todo){
			/*
			* Pour pouvoir supprimer un todo, il ne faut pas oublier d'indiquer l'id de la liste à laquelle appartient le todo qu'on veut supprimer + l'id du todo lui même pour que le serveur sache quel todo on veut supprimer
			*/
			axios.delete(url+'?action=deletetodo&id='+this.currentList.id+'&idtodo='+todo.id).then(function(response){
				v.currentList.todos.splice(v.currentList.todos.indexOf(todo), 1);
			});
		},
		editTodo: function(todo){
			Vue.set(todo, 'edited', true);
			Vue.set(todo, 'newname', todo.name);
		},
		cancelEditTodo: function(todo){
			Vue.delete(todo, 'edited');
			Vue.delete(todo, 'newname');
		},
		updateTodo: function(todo){
			if(todo.newname.trim() != '' && todo.newname != todo.name){
				axios.put(url+'?action=updatetodo&id='+v.currentList.id+'&idtodo='+todo.id, {name: todo.newname}).then(function(response){
					todo.name = todo.newname;
					v.cancelEditTodo(todo);
				});
			}
		},
		catchYouhou: function(msg){
			this.newlist = msg;
		},
    	hideList: function(list){
    		v.lists.splice(v.lists.indexOf(list), 1);
      		if(v.currentList == list){
          		v.currentList = null;//permet de masquer la liste supprimée si elle était affichée
      		}
    	},
    	


	}
}).$mount('#todosApp');


