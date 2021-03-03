
    var ec_left2 = echarts.init(document.querySelector("#l2"), "dark");
    var ec_left2_option = {
        title: {
            text: "Newly trend ",
            textStyle: {

            },
            left: "left",
        },
        tooltip: {
            trigger: "axis",
            //指示器
            axisPointer: {
                type: "line",
                lineStyle: {
                    color: "#7171C6"
                }
            },
        },
        legend: {
            data: ["new_case","new_resolved","new_deceased"],
            left: "right"
        },
        //图形位置
        grid: {
            left: '4%',
            right: '6%',
            bottom: '4%',
            top: 50,
            containLabel: true
        },
        xAxis: [{
            type: "category",
            //x轴坐标点开始与结束点位置都不在最边缘
            data: []
        }],
        yAxis: [{
            type: 'value',
            //y轴字体设置
            axisLable: {
                show: true,
                color: "white"
            },
            //与x轴平行的线样式
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#17273B',
                    width: 1,
                    type: 'solid',
                }
            }
        }],
        series: [{
            name: "new_case",
            type: "line",
            smooth: true,
            data: []
        },
            {name: "new_resolved",
                type: "line",
                smooth: true,
                data: []
        }, {
                name: "new_deceased",
                type: 'line',
                smooth: true,
                data: []

        }]
    };

    ec_left2.setOption(ec_left2_option);
