
var ec_center2 = echarts.init(document.querySelector("#c2"), "dark");

var ec_center2_option = {
    tooltip: {
        triggerOn: "click",
        formatter: function(e, t, n) {
            return .5 === e.value ? e.name + "：有疑似病例" : e.seriesName + "<br />" + e.name + "：" + e.value
        }
    },
    visualMap: {
        min: 0,
        max: 1000,
        left: 26,
        bottom: 1,
        showLabel: !0,
        backgroundColor: 'gray',
        pieces: [{
            gt: 50000,
            label: "> 50000",
            color: "#7f1100"
        }, {
            gte: 1000,
            lte: 50000,
            label: "> 1000",
            color: "#ff5999"
        },{
            gte: 500,
            lte: 1000,
            label: "> 500",
            color: "#ff5414"
        }, {
            gte: 250,
            lt: 500,
            label: "> 250",
            color: "#ff8c71"
        }, {
            gt: 100,
            lt: 250,
            label: "> 100",
            color: "#ffd768"
        }, {
            gt: 0,
            lt: 100,
            label: "> 0",
            color: "#ffffff"
        }],
        show: !0
    },
    geo: {
        map: "加拿大",
        roam: !1,
        scaleLimit: {
            min: 1,
            max: 2
        },
        zoom: 1.1,
        label: {
            normal: {
                show: !0,
                fontSize: "10",
                color: "rgba(0,0,0,0.7)"
            }
        },
        itemStyle: {
            normal: {
                borderWidth: .5,
                //areaColor: "#009fe8",
                borderColor: "#ffefd5"
            },
            emphasis: {
                areaColor: "#fff",
                borderColor: "#4b0082",
                borderWidth: .5
            }
        }
    },
    series: [{
        name: "total_cases",
        type: "map",
        mapType: '加拿大',
        roam: true,
        geoIndex: 0,
        data: []
    }]

};

ec_center2.setOption(ec_center2_option);
