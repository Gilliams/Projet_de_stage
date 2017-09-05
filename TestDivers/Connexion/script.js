Vue.component('todo-item', {
  template: `
        <div class="col-xs-6 col-md-3 ">
          <img :src="title"/>
          <button v-on:click="$emit('remove')">X</button>
        </div>
  `,
  props: ['title']
})
new Vue({
  el: '#lavue',
  data: {
    newTodoText: '',
    todos: [
      './img/forest.jpg',
      './img/male.jpg',
      './img/male.jpg',
      './img/book.jpg',
      './img/natural.jpg',
      './img/landscape.jpg',
      './img/waves.jpg',
      './img/waves.jpg',
    ],
    image: '',
    imagelist: []
  },
  methods: {
    addNewTodo: function () {
      this.todos.push(this.newTodoText)
      this.newTodoText = ''
    },
    randome (min, max){
      min = Math.ceil(0);
      max = Math.floor(this.todos.length);
      return Math.floor(Math.random() * (max - min)) + min;
    },
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createImage(files[0]);
    },
    createImage(file) {
      var image = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.image = e.target.result;
      };
      reader.readAsDataURL(file);
      
    },
    removeImage: function (e) {
      this.image = '';
    },
    envoie (){
      this.todos.push(this.image);
    }

  },
  computed: {
    alea () {
      return this.todos.sort(this.randome);
    },

  },

})
