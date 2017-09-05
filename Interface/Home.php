<?php
include "./php/header.php";
include './jqup/index.html' ;
?>
    <div class="starter-template">
        <h1>Index</h1>
        <form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
            <!-- Redirect browsers with JavaScript disabled to the origin page -->
            <noscript><input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/"></noscript>
            <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
            <div class="row fileupload-buttonbar">
                <div class="col-lg-7">
                    <!-- The fileinput-button span is used to style the file input field as button -->
                    <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                    <button type="submit" class="btn btn-primary start">
                        <i class="glyphicon glyphicon-upload"></i>
                        <span>Start upload</span>
                    </button>
                    <button type="reset" class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel upload</span>
                    </button>
                    <button type="button" class="btn btn-danger delete">
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                    <input type="checkbox" class="toggle">
                    <!-- The global file processing state -->
                    <span class="fileupload-process"></span>
                    <div class="input-group">
                        <input list="dossier" name="choix">
                        <datalist id="dossier">
                            <option value="/test/" label="Dossier Test">
                            <option value="/files/" label="Dossier Files">
                            <option value="../../../../../Stage/src/Alkane/" label="Test Alkane">
                            <option value="C:\xampp\htdocs\ProjetStage/" label="Test">
                        </datalist>
                    </div>
                </div>
                <!-- The global progress state -->
                <div class="col-lg-5 fileupload-progress fade">
                    <!-- The global progress bar -->
                    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                        <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                    </div>
                    <!-- The extended global progress state -->
                    <div class="progress-extended">&nbsp;</div>
                </div>
            </div>
            <!-- The table listing the files available for upload/download -->
            <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
        </form>
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
                        <button @click="supprimeTodo(t)">X</button>
                    </li>
                </ul>
            </div>
        </section>


    </div>
<!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<!-- The template to display files available for upload -->

    </div>
<?php include "./php/footer.php"; ?>

