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
    window.dispatchEvent(new Event('resize'));
}

(()=>{
    let truckLocation =document.getElementById("truck_location").value;
    document.getElementById("truck_location").dispatchEvent(new Event('click'))

    mapboxgl.accessToken = STREETEATZ_MAPBOX_API_KEY;

    const map1 = new mapboxgl.Map({
        container: 'map1',
// Choose from Mapbox's core styles, or make your own style with Mapbox Studio
        style: 'mapbox://styles/mapbox/streets-v12',
        center: [-98.45386, 29.51907],
        zoom: 8,
        interactive: false
    });
    geocode(truckLocation, mapboxgl.accessToken ).then(function(result) {
        console.log(result);
        map1.setCenter(result);
        map1.setZoom(12);

        const markerA = new mapboxgl.Marker()
            .setLngLat(result)
            .addTo(map1)
        // location.replace();

    });

})();