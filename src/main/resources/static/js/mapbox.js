"use strict";


    mapboxgl.accessToken = STREETEATZ_MAPBOX_API_KEY;
    const map = new mapboxgl.Map({
    container: 'map',
// Choose from Mapbox's core styles, or make your own style with Mapbox Studio
    style: 'mapbox://styles/mapbox/streets-v12',
    center: [-98.453985, 29.518593],
    maxZoom: 16,
    minZoom: 9,
    zoom: 9.68,

        interactive: false
});

    const title = document.getElementById('location-title');
    const description = document.getElementById('location-description');

    const locations = [
        {
            'title': 'San Antonio',
            'description':
                'The best place EVER!!!',
            'camera': {
                center: [-98.453985, 29.518593],
                zoom: 9.68,
                // bearing: 0,
                // pitch: 0
            }
        },
    {

        'title': 'Maipe Foodies',
        'description':
        "Join us at the Table! Its MAIPE good! Family Owned, Family recipes, fusion island and asian flavors.",
        'camera': {
        center: [-98.59434, 29.09653],
        zoom: 12,
        // pitch: 50
    }
    },
        {

            'title': 'Blessie’s Indian Fusion',
            'description':
                "A husband and wife owned Indian Fusion Food Truck.",
            'camera': {
                center: [-98.71062, 29.52159],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'Masshole Food Truck',
            'description':
                "Serving the best east coast classic to the 210",
            'camera': {
                center: [-98.453985, 29.518593],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'El Remedio',
            'description':
                "Come out and try some of the best birria tacos and mariscos is San Antonio.",
            'camera': {
                center: [-98.58372, 29.53790],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'El Sueño De Lola',
            'description':
                "Good Food.",
            'camera': {
                center: [-98.56597, 29.48850],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'Grunts Grill',
            'description':
                "Veteran-Owned, American Made.",
            'camera': {
                center: [-98.59434, 29.09653],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'Bull Gogi Boys',
            'description':
                "Korean Fusion food truck as seen on Elder Eats and Eat Migos. Voted #3 Korean Restaurant 2023.",
            'camera': {
                center: [-98.62477, 29.57585],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'Satisfried the Truck',
            'description':
                "From-scratch food truck with a healthy-ish spin on our favorite fat kid food!",
            'camera': {
                center: [-98.42013, 29.50049],
                // bearing: -8.9,
                zoom: 12
            }
        },
        {

            'title': 'Baba Shawarma Food Truck',
            'description':
                "From-scratch food truck with a healthy-ish spin on our favorite fat kid food!",
            'camera': {
                center: [-98.56741, 29.51029],
                // bearing: -8.9,
                zoom: 12
            }
        },
    ];

const Maipe_Foodies = new mapboxgl.Marker({})
    .setLngLat([-98.59434, 29.09653])
    .addTo(map);

const Blessies_Indian_Fusion = new mapboxgl.Marker({ color: 'light blue', rotation: 25 })
    .setLngLat([-98.71062, 29.52159])
    .addTo(map);

const Masshole_Food_Truck = new mapboxgl.Marker({ color: 'dark blue', rotation: 45 })
    .setLngLat([-98.453985, 29.518593])
    .addTo(map);

const El_Remedio = new mapboxgl.Marker({ color: 'black', rotation: 65 })
    .setLngLat([-98.58372, 29.53790])
    .addTo(map);

const El_Sueño_De_Lola = new mapboxgl.Marker({ color: 'blue', rotation: 85 })
    .setLngLat([-98.56597, 29.48850])
    .addTo(map);

const Grunts_Grill = new mapboxgl.Marker({ color: 'blue', rotation: 105 })
    .setLngLat([-98.59434, 29.09653])
    .addTo(map);

const Bull_Gogi_Boys = new mapboxgl.Marker({ color: 'red', rotation: 125 })
    .setLngLat([-98.62477, 29.57585])
    .addTo(map);

const Satisfried_the_Truck = new mapboxgl.Marker({ color: 'green', rotation: 145 })
    .setLngLat([-98.42013, 29.50049])
    .addTo(map);

const Baba_Shawarma_Food_Truck = new mapboxgl.Marker({ color: 'silver', rotation: 165 })
    .setLngLat([-98.56741, 29.51029])
    .addTo(map);

    function highlightBorough(code) {
// Only show the polygon feature that corresponds to `borocode` in the data.
    map.setFilter('highlight', ['==', 'borocode', code]);
}

    function playback(index) {
    title.textContent = locations[index].title;
    description.textContent = locations[index].description;

    highlightBorough(locations[index].id ? locations[index].id : '');

// Animate the map position based on camera properties.
    map.flyTo(locations[index].camera);

    map.once('moveend', () => {
// Duration the slide is on screen after interaction.
    window.setTimeout(() => {
// Increment index, looping back to the first after the last location.
    index = (index + 1) % locations.length;
    playback(index);
}, 3000); // After callback, show the location for 3 seconds.
});
}

    // Display the last title/description first.
    title.textContent = locations[locations.length - 1].title;
    description.textContent = locations[locations.length - 1].description;

    map.on('load', () => {
    map.addSource('boroughs', {
        'type': 'vector',
        'url': 'mapbox://mapbox.8ibmsn6u'
    });
    map.addLayer(
{
    'id': 'highlight',
    'type': 'fill',
    'source': 'boroughs',
    'source-layer': 'original',
    'paint': {
    'fill-color': '#fd6b50',
    'fill-opacity': 0.25
},
    'filter': ['==', 'borocode', '']
},
    'road-label' // Place polygon under labels.
    );

// Start the playback animation for each borough.
    playback(0);
});
