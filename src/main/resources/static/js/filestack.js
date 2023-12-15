let uploadDoc= document.getElementById("upload")
let userInput = document.getElementById("img")
let uploadForm=document.getElementById("imgForm")

uploadDoc.addEventListener("click", () => {
    const client = filestack.init(STREET_EATZ_API_KEY);
    const options = {
        onUploadDone:
            function (response) {
                console.log(response.filesUploaded[0].url);
                userInput.value = response.filesUploaded[0].url;
                uploadForm.submit();
            }
    };
    client.picker(options).open();
})