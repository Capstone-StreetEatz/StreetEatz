const popupImages = document.querySelectorAll('.gallery-img');
const closeButton = document.querySelector('.close-button');
for (const image of popupImages) {
    image.addEventListener('click', showImage);
}
closeButton.addEventListener('click', closeImage);

function showImage(e) {
    console.log('working');
    const popupImage = e.target.src;
    const imagePopupWrapper = document.querySelector("#imagePopup");
    const imagePopup = imagePopupWrapper.querySelector('img');
    imagePopup.src = popupImage;
    imagePopupWrapper.style.display = "block";
    document.body.style.overflow = "hidden";
}
// function to hide the image when we click on cross button
function closeImage() {
    const imagePopup = document.querySelector("#imagePopup");
    imagePopup.style.display = "none";
    document.body.style.overflow = "auto";
}