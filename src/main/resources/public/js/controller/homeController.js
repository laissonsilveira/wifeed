app.controller('HomeCtrl', function ($scope, $http, $timeout) {

    var quantidadePerguntas = 3;
    
    var perguntas = [
    	{
	        texto: "Como você soube do nosso estabelecimento?",
	        opcoes: [
	            "Redes Sociais",
	            "Amigos",
	            "Outros"
	        ]
	    }, {
	        texto: "Você gosta de música ao vivo?"
	    }, {
	        texto: "Você vem sempre aqui?",
	        opcoes: [
	        	"Nunca",
	        	"Todos os dias",
	        	"As vezes"
	        ]
	    }
    ];

    var perguntasJaFeitas = [];

    var getRandomNumber = function () {
        return Math.floor((Math.random() * perguntas.length) + 1) - 1;
    };

    var trocaPergunta = function () {

        var numero = getRandomNumber();
        
        while(perguntasJaFeitas.indexOf(numero) != -1){
        	numero = getRandomNumber();
        }

        $scope.pergunta = perguntas[numero];

        perguntasJaFeitas.push(numero);

    };

    trocaPergunta();

    var liberaAcesso = function () {
        window.location = "http://www.acipsc.com.br/";
    };

    $scope.responde = function (resposta, pergunta) {

        $http.post('/salvarresposta', {
            pergunta: pergunta,
            resposta: resposta
        }).success(function (res) {
            console.info(res);
        }).error(function (err) {
            console.error(err);
        });

        if (quantidadePerguntas === 1) {
            liberaAcesso();
            return;
        }

        trocaPergunta();

        quantidadePerguntas = quantidadePerguntas - 1;

    };

});
