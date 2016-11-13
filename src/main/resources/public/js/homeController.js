app.controller('HomeCtrl', function ($scope, $http, $timeout) {

    var propagandas = [{
        tipo: 1,
        texto: "Como você soube do nosso estabelecimento?",
        imagem: "",
        opcoes: [
            "Redes Sociais",
            "Amigos",
            "Outros"
        ]
    }, {
        tipo: 1,
        texto: "Você gosta de música ao vivo?",
        imagem: ""
    }, {
        tipo: 1,
        texto: "Você vem sempre aqui?",
        imagem: ""
    }
        
    ];

    propagandas = propagandas;

    var quantidadePropagandas = 3;

    var ultimaPropaganda = null;

    var getRandomNumber = function () {
        return Math.floor((Math.random() * propagandas.length) + 1) - 1;
    };

    var trocaPropaganda = function () {

        var numero = getRandomNumber();

        if (ultimaPropaganda !== null) {
            while ((numero === ultimaPropaganda || propagandas[numero].tipo !== propagandas[ultimaPropaganda].tipo)) {
                numero = getRandomNumber();
            }
        }

        $scope.propaganda = propagandas[numero];

        ultimaPropaganda = numero;

    };

    trocaPropaganda();

    $scope.tempo = 7;

    var liberaAcesso = function () {
        window.location = "http://www.google.com/";
    };

    var chamaTimer = function () {
        var tick = function () {
            if ($scope.tempo < 1) {
                liberaAcesso();
                return;
            }
            $scope.tempo = $scope.tempo - 1;
            $timeout(tick, 1000);
        };
        $timeout(tick, 1000);
    };

    if ($scope.propaganda.tipo === 2) {
        chamaTimer();
    }

    $scope.responde = function (resposta, pergunta) {

        var respostas = {
            pergunta: pergunta,
            resposta: resposta
        };

        $http.post('/salvarresposta', respostas)
            .success(function (res) {
                console.info(res);
            }).error(function (err) {
            console.error(err);
        });

        if (quantidadePropagandas === 1) {
            liberaAcesso();
            return;
        }

        trocaPropaganda();

        quantidadePropagandas = quantidadePropagandas - 1;

        if ($scope.propaganda.tipo === 2) {
            chamaTimer();
        }

    };

});
