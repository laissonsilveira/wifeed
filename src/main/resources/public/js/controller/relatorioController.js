app.controller('RelatorioCtrl', function ($scope, $http) {

    $scope.respostas = [];
    $scope.menuItems = ['Perguntas / Respostas', 'Gráficos'];
    $scope.activeMenu = $scope.menuItems[0];
    $scope.isChart = false;

    $scope.setActive = function(menuItem) {
        $scope.activeMenu = menuItem;
        $scope.isChart = menuItem == $scope.menuItems[1];
    };

    $scope.updateChart = function () {
      countResponses();
    };

    function montarRelatorio(data) {
        $('#rel').empty();

        $.each(data, function (index, res) {
            var html = "<label style='color: gray;padding-right: 25px' class='text-center'>"
                + res._id +
                "<div id='rel_" + index + "'></div>" +
                "</label>";

            $("#rel").append(html);

            var _resultado = [];

            $.each(res.respostas, function (i, obj) {
                _resultado.push([obj.resposta, obj.count]);
            });

            c3.generate({
                bindto: $('#rel_' + index)[0],
                data: {
                    columns: _resultado,
                    type: 'pie'
                },
                size: {
                    width: 250,
                    height: 250
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
