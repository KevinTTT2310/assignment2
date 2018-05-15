function postDataAirport() {
    console.log("posting data...");

    // Get values from html.
    var location = $("#location").val();


    // Create JS object with data.
    var newAirport = {
        location: location
    };
    console.log(newAirport);

    // Convert JS object to JSON.
    var validJsonAirport = JSON.stringify(newAirport);
    console.log(validJsonAirport);

    // Post JSON to endpoint.
    $.ajax({
        url: "/api/airport/add",
        type: "post",
        data: validJsonAirport,
        contentType: "application/json",
        success: function (result) {
            // On successful post, reload data to get the added one as well.
            console.log("success post data!");
            getDataAirport();
        }
    });
}

function getDataAirport() {
    console.log("getting data...");
    // Get the data from endpoint.
    $.ajax({
        url: "/api/airport/",
        type: "get",
        success: function (airports) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: " + airports);
            $('#airports').DataTable().clear();
            $('#airports').DataTable().rows.add(airports);
            $('#airports').DataTable().columns.adjust().draw();
        }
    });
}

function setupAirport() {
    // Modal submit.
    $("#saveAirport").on('submit', function (e) {
        console.log("Submitted new menu item form");

        // Post the data from the modal.
        postDataAirport();

        // Reset modal to hide and no values.
        $('#newAirportModal').modal('hide');
        $("#location").val("");
    });

    // Load DataTable with data format.
    $('#airports').DataTable({
        columns: [
            {"data": "location"}
        ]
    });

    // Load first data.
    getDataAirport();
}