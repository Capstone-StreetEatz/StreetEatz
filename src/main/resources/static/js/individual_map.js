"use strict";


// console.log(userLocation);
// const watchID = navigator.geolocation.watchPosition((position) => {
//     // doSomething(position.coords.latitude, position.coords.longitude);
// });


let truckLocation =document.getElementById("truck_location").value;
mapboxgl.accessToken = 'pk.eyJ1IjoiZ3dhbGtlcjkzIiwiYSI6ImNsbWY4ajdubzF4MDUzcHJxNjR2dDNrYWkifQ.cil4dmekFqmMRRq4LYEpqg';
const map1 = new mapboxgl.Map({
    container: 'map1',
// Choose from Mapbox's core styles, or make your own style with Mapbox Studio
    style: 'mapbox://styles/mapbox/streets-v12',
    center: [-98.45386, 29.51907],
    zoom: 8,

});

function geocode(search, token) {
    let baseUrl = 'https://api.mapbox.com';
    let endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
            // to get all the data from the request, comment out the following three lines...
        }).then(function(data) {
            return data.features[0].center
        });
}

function myFunction() {
    document.getElementById("refresh").innerHTML = "YOU CLICKED ME!";

geocode(truckLocation, mapboxgl.accessToken ).then(function(result) {
    console.log(result);
    map1.setCenter(result);
    map1.setZoom(12);

        const markerA = new mapboxgl.Marker()
            .setLngLat(result)
            .addTo(map1)
            location.replace();

}
);


}
