app.controller('RelatorioCtrl', function ($scope, $http) {

    $scope.respostas = [];
    var respostaCount = [];

    function montarRelatorio(data) {
        $.each(respostaCount, function(index, obj) {
        // $.each(data, function(index, obj) {
            var res = obj[index];
            $("#rel").append("<label style='color: white;padding-right: 25px'>" + res._id + "<div id='rel_" + index + "'></div></label>");

            var _resultado = [];

            $.each(res.respostas, function (i, obj) {
                _resultado.push([obj.resposta, obj.count]);
            });

            c3.generate({
                bindto: $('#rel_' + index)[0],
                data: {
                    columns: _resultado,
                    type: 'pie'
                }
            });
        });
    }

    function findAllRes() {
        $http.get('/listarespostas')
            .success(function (res) {
                // $scope.respostas = res;
                respostaCount = res;
            }).error(function (err) {
                console.error(err);
        });
    }

    findAllRes();
    montarRelatorio(
    // [
    //     {
    //         "_id" : "Você gosta de música ao vivo?",
    //         "respostas" : [
    //             {
    //                 "resposta" : "Sim",
    //                 "count" : 2
    //             },
    //             {
    //                 "resposta" : "Não",
    //                 "count" : 50
    //             }
    //         ],
    //         "count" : 52
    //     },
    //     {
    //         "_id" : "Como você soube do nosso estabelecimento?",
    //         "respostas" : [
    //             {
    //                 "resposta" : "Amigos",
    //                 "count" : 1
    //             },
    //             {
    //                 "resposta" : "Redes Sociais",
    //                 "count" : 2
    //             }
    //         ],
    //         "count" : 3
    //     }
    // ]
    );
});
