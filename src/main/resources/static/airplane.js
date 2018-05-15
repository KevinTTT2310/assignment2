function postDataAirplane() {
    console.log("posting data...");

    // Get values from html.
    var name = $("#name").val();
    var fuel = $("#fuel").val();
    var location = $("#location").val();


    // Create JS object with data.
    var newAirplane = {
        name: name,
        fuel: fuel,
        location: location
    };
    console.log(newAirplane);

    // Convert JS object to JSON.
    var validJsonAirplane = JSON.stringify(newAirplane);
    console.log(validJsonAirplane);

    // Post JSON to endpoint.
    $.ajax({
        url: "/api/airplane/add",
        type: "post",
        data: validJsonAirplane,
        contentType: "application/json",
        success: function (result) {
            // On successful post, reload data to get the added one as well.
            console.log("success post data!");
            getDataAirplane();
        }
    });
}

function getDataAirplane() {
    console.log("getting data...");

    // Get the data from endpoint.
    $.ajax({
        url: "/api/airplane/",
        type: "get",
        success: function (airplanes) {
            // On successful get, reload the datatable with new data.
            console.log("This is the data: " + airplanes);
            $('#airplanes').DataTable().clear();
            $('#airplanes').DataTable().rows.add(airplanes);
            $('#airplanes').DataTable().columns.adjust().draw();
        }
    });
}

function setupAirplane() {
    // Modal submit.
    $("#saveAirplane").on('submit', function (e) {
        console.log("Submitted new menu item form");

        // Post the data from the modal.
        postDataAirplane();

        // Reset modal to hide and no values.
        $('#newAirplaneModal').modal('hide');
        $("#name").val("");
        $("#fuel").val("");
        $("#location").val("");
    });

    // Load DataTable with data format.
    $('#airplanes').DataTable({
        columns: [
            {"data": "name"},
            {"data": "fuel"},
            {"data": "location"}
        ]
    });

    // Load first data.
    getDataAirplane();
}