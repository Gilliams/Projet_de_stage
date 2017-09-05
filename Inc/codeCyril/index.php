<?php
header('Content-type: application/json; charset=utf-8');

function init_params(){
	$params = array();
	if(isset($_SERVER['CONTENT_TYPE']) && strpos($_SERVER['CONTENT_TYPE'], "application/json") !== false){
		$params = json_decode(file_get_contents('php://input'), true);
	}
	else{
		parse_str(file_get_contents('php://input'), $params);
	}

	if(is_null($params)){ //parse_str peut retourner nul si la chaîne passée en paramètre est vide
		$params = array();
	}

	$params = filter_var_array($params, FILTER_SANITIZE_STRING);
	$_GET   = filter_input_array(INPUT_GET, FILTER_SANITIZE_STRING);
	$_POST  = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

	if(is_null($_POST)){
		$_POST = array();
	}

	if(is_null($_GET)){
		$_GET = array();
	}

	return array_merge($_POST, $params, $_GET);
}
if(isset($_GET['action'])){
	switch($_GET['action']){
		default:
			header("HTTP/1.0 404 Not Found");
			break;
		case 'lists'://retourner la liste des listes
			if( file_exists('./lists.json') ){
				$tab = json_decode(file_get_contents('./lists.json'), true);
				if(!empty($tab)){
					array_walk($tab, function(&$el, $id){
						$el['todos'] = array_values($el['todos']);
					});
				}

				echo !empty($tab) ? json_encode(array_values($tab)) : '[]';
			}
			else {
				echo '[]';
			}
			break;
		case 'newlist':
			$data = init_params();
			if(isset($data['list'])){

				$listname = $data['list'];
				if(!empty($listname)){
					$lists = [];
					if( file_exists('./lists.json') ){
						$lists = json_decode(file_get_contents('./lists.json'), true);
					}

					$newlist = ['id' => uniqid('',true), 'name' => $listname, 'todos' => []];
					$lists[$newlist['id']] = $newlist;

					file_put_contents('./lists.json', json_encode($lists));
					echo json_encode($newlist);
				}
				else{
					header("HTTP/1.0 422 Unprocessable Entity");
				}
			}
			else{
				header("HTTP/1.0 422 Unprocessable Entity");
			}
			break;
		case 'newtodo':
			$data = init_params();
			if(!empty($data['todo']) && !empty($data['id'])){
				$idlist = $data['id'];

				//on doit récupérer notre liste
				$lists = [];
				if( file_exists('./lists.json') ){
					$lists = json_decode(file_get_contents('./lists.json'), true);
				}

				if(! empty($lists) && isset($lists[$idlist])){
					$todo = ['id' => uniqid('', true), 'name' => $data['todo']];

					if( ! isset($lists[$idlist]['todos'])){
						$lists[$idlist]['todos'] = [];
					}
					$lists[$idlist]['todos'][$todo['id']] = $todo;

					file_put_contents('./lists.json', json_encode($lists));

					echo json_encode($todo);


				}
				else{
					header("HTTP/1.0 422 Unprocessable Entity");
				}


			}
			else{
				header("HTTP/1.0 422 Unprocessable Entity");
			}
			break;
		case 'deletelist':
			if(strtolower($_SERVER['REQUEST_METHOD']) == 'delete'){
				$idlist = $_GET['id'];
				$lists = [];
				if( file_exists('./lists.json') ){
					$lists = json_decode(file_get_contents('./lists.json'), true);
				}

				if(isset($lists[$idlist])){
					unset($lists[$idlist]);
					file_put_contents('./lists.json', json_encode($lists));
					echo json_encode($lists);
				}
				else{
					header("HTTP/1.0 404 Resource not found");
				}


			}
			else{
				header("HTTP/1.0 400 Bad Request Error");
			}
			break;
		case 'updatelist':
			if(strtolower($_SERVER['REQUEST_METHOD']) == 'put'){
				$data = init_params();
				$idlist = $data['id'];

				if(empty($data['name'])){
					header('HTTP/1.0 422 Unprocessable Entity');
					return;
				}
				$lists = [];
				if( file_exists('./lists.json') ){
					$lists = json_decode(file_get_contents('./lists.json'), true);
				}

				if(isset($lists[$idlist])){
					$lists[$idlist]['name'] = $data['name'];
					file_put_contents('./lists.json', json_encode($lists));
					echo json_encode($lists[$idlist]);
				}
				else{
					header("HTTP/1.0 404 Resource not found");
				}


			}
			else{
				header("HTTP/1.0 400 Bad Request Error");
			}
			break;
		case 'deletetodo':
			if(strtolower($_SERVER['REQUEST_METHOD']) == 'delete'){
				$idlist = $_GET['id'];
				$idtodo = $_GET['idtodo'];
				$lists = [];
				if( file_exists('./lists.json') ){
					$lists = json_decode(file_get_contents('./lists.json'), true);
				}

				if(isset($lists[$idlist])){
					if(isset($lists[$idlist]['todos'][$idtodo])){
						unset($lists[$idlist]['todos'][$idtodo]);
						file_put_contents('./lists.json', json_encode($lists));
						echo json_encode($lists);
					}
					else{
						header("HTTP/1.0 404 Resource not found");
					}
				}
				else{
					header("HTTP/1.0 404 Resource not found");
				}


			}
			else{
				header("HTTP/1.0 400 Bad Request Error");
			}
			break;
		case 'updatetodo':
			if(strtolower($_SERVER['REQUEST_METHOD']) == 'put'){
				$idlist = $_GET['id'];
				$idtodo = $_GET['idtodo'];

				$data = init_params();

				if(empty($data['name'])){
					header('HTTP/1.0 422 Unprocessable Entity');
					return;
				}
				$lists = [];
				if( file_exists('./lists.json') ){
					$lists = json_decode(file_get_contents('./lists.json'), true);
				}

				if(isset($lists[$idlist])){
					if(isset($lists[$idlist]['todos'][$idtodo])){
						$lists[$idlist]['todos'][$idtodo]['name'] = $data['name'];
						file_put_contents('./lists.json', json_encode($lists));
						echo json_encode($lists[$idlist]['todos'][$idtodo]);
					}
					else{
						header("HTTP/1.0 404 Resource not found");
					}
				}
				else{
					header("HTTP/1.0 404 Resource not found");
				}
			}
			else{
				header("HTTP/1.0 400 Bad Request Error");
			}
			break;
	}
}
else{
	header("HTTP/1.0 404 Not Found");
}
