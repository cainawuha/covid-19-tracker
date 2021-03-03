

function getTime() {
    $.ajax({
        url:"/time",
        timeout: 10000,
        success:function(data){
            $("#time").html(data)
        },
        error:function (xhr,type,errorThrown) {
        }
    });

}

function getTimeUpdate() {
    $.ajax({
        url:"/timeUpdate",
        timeout: 10000,
        success:function(data){
            $("#timeUpdate").html(data)
        },
        error:function (xhr,type,errorThrown) {
        }
    });
}



function get_c1_data() {
    $.ajax({
        url: "/c1",
        dataType: 'json',
        success: function (data) {
            $(".num h1").eq(0).text(data.cases_today);
            $(".num h1").eq(1).text(data.recovered_today);
            $(".num h1").eq(2).text(data.deaths_today);
        }
    })
}



// function get_r2_data() {
//     $.ajax({
//         url: "/r2",
//         success: function (response) {
//            // console.log(response);
//             ec_right2.setOption(ec_right2_option.series[0].data= response);
//         }
//     });
// }


function get_c2_data() {
    $.ajax({
        url: "/c2",
        dataType: 'json',
        success: function (response) {
            console.log(response);
            console.log(ec_center2_option.series[0].data);
            ec_center2_option.series[0].data=response;
            ec_center2.setOption(ec_center2_option);
        }
    });
}


function get_l1_data() {
   $.ajax({
        url: "/l1",
        success: function (response) {
          console.log(response);
            ec_left1_option.xAxis[0].data=response.update_time;
            ec_left1_option.series[0].data=response.cases;
            ec_left1_option.series[1].data=response.recovered;
            ec_left1_option.series[2].data=response.deaths;
            ec_left1.setOption(ec_left1_option);
        }
    });
}

function get_l2_data() {
    $.ajax({
        url: "/l2",
        success: function (response) {
          //  console.log(response);
            ec_left2_option.xAxis[0].data=response.day;
            ec_left2_option.series[0].data=response.Active_cases_change;
            ec_left2_option.series[1].data=response.Recovered_change;
            ec_left2_option.series[2].data=response.Dead_add;

            ec_left2.setOption(ec_left2_option);
        }
    });
}

function get_r2_data() {
    $.ajax({
        url: "/r2",
        success: function (response) {
            ec_right2_option.series[0].data=response;
            // console.log(response);
            // console.log(ec_center2_option.series[0].data);
            ec_right2.setOption(ec_right2_option);
        }
    });
}

// function get_r1_data() {
//     $.ajax({
//         url: "/r1",
//         success: function (response) {
//             console.log(response);
//             ec_right1_option.xAxis[0].data=response.city;
//             ec_right1_option.series[0].data=response.cityValue;
//             ec_right1.setOption(ec_right1_option);
//             console.log(ec_right1_option.xAxis[0].data[1]);
//         }
//     });
// }

setInterval(getTime,1000);
setInterval(get_c1_data,1000);
get_c1_data();
get_l1_data();
get_l2_data();
getTimeUpdate();
//get_r1_data();
get_r2_data();
get_c2_data();
getTime();