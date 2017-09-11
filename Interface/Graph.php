<?php include './php/header.php' ?>
    <h1>Graph</h1>
<div id="graph" style="width: 1200px;height:800px;"></div>
<script type="text/javascript">
    // based on prepared DOM, initialize echarts instance
    var myChart = echarts.init(document.getElementById('graph'));

    // specify chart configuration item and data
    var xAxisData = [];
    var data1 = [];
    var data2 = [];
    for (var i = 0; i < 500; i++) {
        xAxisData.push('Fichier ct' + i);
        data1.push((Math.sin(i / 5) * (i / 5 -10) + i / 6) * 5);
        data2.push((Math.cos(i / 5) * (i / 5 -10) + i / 6) * 5);
    }

    option = {
        title: {
            text: 'Tri des fichiers '
        },
        legend: {
            data: ['Aruna', 'Salim'],
            align: 'left'
        },
        toolbox: {
            // y: 'bottom',
            feature: {
                magicType: {
                    type: ['stack', 'tiled']
                },
                dataView: {},
                saveAsImage: {
                    pixelRatio: 2
                }
            }
        },
        tooltip: {},
        xAxis: {
            data: xAxisData,
            silent: false,
            splitLine: {
                show: false
            }
        },
        yAxis: {
        },
        series: [{
            name: 'Aruna',
            type: 'bar',
            data: data1,
            animationDelay: function (idx) {
                return idx * 10;
            }
        }, {
            name: 'Salim',
            type: 'bar',
            data: data2,
            animationDelay: function (idx) {
                return idx * 10 + 100;
            }
        }],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function (idx) {
            return idx * 5;
        }
    };

    // use configuration item and data specified to show chart
    myChart.setOption(option);
</script>

<div id="graph2" style="width: 1000px;height:600px;"></div>
<script type="text/javascript">
    // based on prepared DOM, initialize echarts instance
    var myChart = echarts.init(document.getElementById('graph2'));

    // specify chart configuration item and data
    var option = {
        title: {
            text: 'Exemple de graph'
        },
        tooltip: {},
        legend: {
            data:['Signature']
        },
        xAxis: {
            data: ["Fichier1","Fichier2","Fichier3 Fichier4","Fichier5","Fichier6","Fichier7"]
        },
        yAxis: {},
        series: [{
            name: 'Signature',
            type: 'bar',
            data: [0.259, 1, 2, 3, 0.768, 1.4]
        }]
    };

    // use configuration item and data specified to show chart
    myChart.setOption(option);
</script>
<?php include './php/footer.php' ?>



