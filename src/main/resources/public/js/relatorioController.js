app.controller('RelatorioCtrl', function ($scope, $http) {

    $scope.respostas = [];

    function montarRelatorio(data) {
        $.each(data, function (index, res) {
            // $.each(data, function(index, obj) {
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

    function countResponses() {
        $http.get('/countrespostas')
            .success(function (res) {
                montarRelatorio(res);
            })
            .error(function (err) {
                console.error(err);
            });
    }

    function findAllRes() {
        $http.get('/listarespostas')
            .success(function (res) {
                $scope.respostas = res;
            })
            .error(function (err) {
                console.error(err);
            });
    }

    findAllRes();
    countResponses();
});
