
$(document).ready(function () {

    visualizeTraderDetails();

    setInterval(visualizeTraderDetails, 1000 * 10);

});



function visualizeTraderDetails() {

    console.debug(" -> :: visualizeTraderDetails()");

    $.ajax({

        type: "GET",

        url: apiURL + "/trader/" + traderName(),

        contentType: "text/plain",

        crossDomain: true,

        success: function (data, status, jqXHR) {
//
            console.warn(data);
//
//alert("worsssk");

            //   insertTraderDetail(data);

            insertTraderHoldings(data);

            //      insertTraderOrders(data);

        },

        error: function (exception, status) {

            alert("eorrrrrrrrrrrrrr?");

            console.error("error fetting trader details / ", exception);

        }

    });

}

function insertTraderHoldings(holdings) {

    console.debug(" -> :: insertTraderHoldings()");

    var html = '';

    for (var i = 0; i < holdings.stocksOwned.length; i++) {

        html += '<tr>';

        html += '<td>' + holdings.stocksOwned[i].ticker + '</td>';

        html += '<td>' + holdings.stocksOwned[i].amountOwned + '</td>';

        html += '<td>' + holdings.stocksOwned[i].costBasis + '</td>';

        html += '<td>' + holdings.stocksOwned[i].value + '</td>';

        html += '</tr>';

    }

    console.warn("html / ", html);

    $('#holdings > tbody').html(html);


}
