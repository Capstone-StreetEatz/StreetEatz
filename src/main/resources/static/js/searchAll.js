// function searchTrucks() {
//     let search = document.getElementById('search-input').value;
//     let xhr = new XMLHttpRequest();
//     xhr.open('GET', '/search?search=' + search, true);
//     xhr.setRequestHeader('Content-Type', 'application/json');
//     xhr.onload = function() {
//         if (xhr.status === 200) {
//             let trucks = JSON.parse(xhr.responseText);
//             let searchResults = document.getElementById('search-results');
//             searchResults.innerHTML = '';
//             trucks.forEach(function(truck) {
//                 let card = document.createElement('div');
//                 card.className = 'card';
//                 card.innerHTML = 'Truck Name: ' + truck.truckName;
//                 searchResults.appendChild(card);
//             });
//         }
//     };
//     xhr.send(JSON.stringify({ search: search }));
// }
// document.getElementById('search').addEventListener('input', function() {
//     let searchTerm = this.value;
//     fetch(`/search?search=${searchTerm}`)
//         .then(response => response.json())
//         .then(data => {
//             // Clear the existing list of trucks
//             let truckList = document.getElementById('truckList');
//             while (truckList.firstChild) {
//                 truckList.removeChild(truckList.firstChild);
//             }
//
//             // Create a new list item for each truck and add it to the list
//             data.forEach(function(truck) {
//                 let listItem = document.createElement('li');
//                 listItem.textContent = truck.truckName;
//                 truckList.appendChild(listItem);
//             });
//         });
// });



let truckSearch = document.getElementById("truckSearch");
let storedTrucks=[];
let filteredTrucks=[];
function updateValue(event) {
    event.preventDefault();
    for(let i=0; i < storedTrucks.length; i++){
        if (storedTrucks[i].name.toLowerCase().includes(event.target.value.toLowerCase())){
            filteredTrucks.push(storedTrucks[i]);
        }
    }
    tbody.innerHTML = renderTrucks(filteredTrucks);
    filteredTrucks=[];
}
truckSearch.addEventListener('input', updateValue);