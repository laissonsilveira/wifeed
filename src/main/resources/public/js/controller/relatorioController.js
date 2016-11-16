app.controller('RelatorioCtrl', function ($scope, $http) {

    $scope.respostas = [];
    $scope.menuItems = [
        {id: 0, name: 'Resultados'},
        {id: 1, name: 'Perguntas'},
        {id: 2, name: 'Respostas'},
        {id: 3, name: 'Conexões'},
        {id: 4, name: 'Promoções'}
    ];
    $scope.activeMenu = $scope.menuItems[0];
    $scope.result = [];

    $scope.setActive = function (menuItem) {
        $scope.activeMenu = menuItem;
    };

    $scope.updateChart = function () {
        countResponses();
    };

    function makeChartConnections() {
        c3.generate({
            bindto: $('#connect')[0],
            data: {
                x: 'x',
                columns: [
                    ['x', '2016-11-01', '2016-11-02', '2016-11-03', '2016-11-04', '2016-11-05', '2016-11-06'],
                    ['Conexões', 30, 200, 100, 400, 150, 250]
                ]
            },
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {
                        format: '%d-%m-%Y'
                    }
                }
            }
        });
    }

    function makeChartUsers() {
        c3.generate({
            bindto: $('#chart_user_05_10_15')[0],
            data: {
                columns: [
                    ['Até 05', 80],
                    ['Entre 05 e 10', 45],
                    ['Entre 10 e 15', 33]
                ],
                type : 'donut'
            },
            donut: {
                title: "Até 15 vezes"
            }
        });

        c3.generate({
            bindto: $('#chart_user_15_20_25')[0],
            data: {
                columns: [
                    ['Entre 15 e 20', 30],
                    ['Entre 20 e 25', 22]
                ],
                type : 'donut'
            },
            donut: {
                title: "Entre 15 e 25 vezes"
            }
        });

        c3.generate({
            bindto: $('#chart_user_25_30_35')[0],
            data: {
                columns: [
                    ['Entre 25 e 30', 20],
                    ['Entre 30 e 35', 17]
                ],
                type : 'donut'
            },
            donut: {
                title: "Entre 25 e 35 vezes"
            }
        });

        c3.generate({
            bindto: $('#chart_user_35_40_45')[0],
            data: {
                columns: [
                    ['Entre 35 e 40', 13],
                    ['Entre 40 e 45', 6],
                    ['Mais que 45', 2]
                ],
                type : 'donut'
            },
            donut: {
                title: "Mais que 35 vezes"
            }
        });
    }

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
                $scope.result = res;
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
    makeChartConnections();
    makeChartUsers();
});
