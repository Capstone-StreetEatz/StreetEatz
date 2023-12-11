"use strict";

mapboxgl.accessToken = 'pk.eyJ1IjoiZ3dhbGtlcjkzIiwiYSI6ImNsbWY4ajdubzF4MDUzcHJxNjR2dDNrYWkifQ.cil4dmekFqmMRRq4LYEpqg';
const map1 = new mapboxgl.Map({
    container: 'map1',
// Choose from Mapbox's core styles, or make your own style with Mapbox Studio
    style: 'mapbox://styles/mapbox/streets-v12',
    center: [-98.45386, 29.51907],
    zoom: 8
});

// Create a default Marker and add it to the map.
const markerA = new mapboxgl.Marker()
    .setLngLat([-98.453986, 29.51907])
    .addTo(map1);
