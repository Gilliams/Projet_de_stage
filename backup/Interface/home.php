
<?php include "header.php"; ?>

    <div class="starter-template">
        <h1>Index</h1>

        <?php include "JQFU/index.html" ;?>

        <a href="folder.php">
            <button  class="btn btn-default">
                <img src="../Inc/img/icone/PNG/folder-plus.png" class="icon " alt="folder">
                Folder
            </button>
        </a>
        <section id="todosApp">
            <div id="lists">
                <form @submit.prevent="createList()">
                    Ajouter un dossier <input type="text" v-model="newlist" />
                </form>
                <ul v-if="lists.length > 0">
                    <li is="todo-list"
                        v-for="l in lists"
                        :list="l"
                        @deletelist="hideList($event)"
                        @childclicked="showList(l)"></li>
                </ul>
                <em v-else-if="initialized">Aucune liste pour l'instant, créez en une !</em>
            </div>
            <div id="todos" v-if="currentList">
                <h1 v-html="currentList.name"></h1>
                <form @submit.prevent="createTodo()">
                    <input type="text" v-model="newtodo" placeholder="nouveau todo" />
                </form>
                <ul>
                    <li v-for="t in currentList.todos">
                        <span v-html="t.name" @dblclick="editTodo(t)" v-if="!t.edited"></span>
                        <input v-else type="text" v-model="t.newname" @keyup.esc="cancelEditTodo(t)" @keyup.enter="updateTodo(t)" />
                        <a href="test.php"></a>
                        <button @click="supprimeTodo(t)">X</button>
                    </li>
                </ul>
            </div>
        </section>

    </div>
<?php include "footer.php"; ?>

