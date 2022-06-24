let startDate;
let endDate;
var calendar;
var globalrespanse;
var curfile;
var myInstance;

function removeAbsence() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/remove/absance");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(globalrespanse.uid);

    location.reload();
}

function loadFile(instance) {
    
}

document.addEventListener('DOMContentLoaded', function () {
    WebViewer({
        path: 'pdfjs/lib', // path to the PDF.js Express'lib' folder on your server
        licenseKey: 'Insert free license key here',
        //initialDoc: '/document/2',
        // initialDoc: '/path/to/my/file.pdf',  // You can also use documents on your server
    }, document.getElementById('viewer'))
        .then(instance => {
            myInstance=instance;
        });
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        select: function (start, end, jsEvent, view) {
            startDate = start;
            endDate = end;
            console.log(start);
        },
        events: {
            url: '/get/absance',
        },
        eventClick : function (info){
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/get/absance");

            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onload = function (){
                var emploeeInput = document.getElementById("inputEmployee");
                var absanceTypeInput = document.getElementById("absancetype");
                var descreptionInput = document.getElementById("Descreption");
                var absanceIdInput = document.getElementById("UID");
                globalrespanse = JSON.parse(this.response);
                emploeeInput.value = globalrespanse.emploeeID;
                absanceTypeInput.value = globalrespanse.absanceID;
                descreptionInput.value = globalrespanse.discreption;
                absanceIdInput.value = globalrespanse.uid;
                myInstance.loadDocument("/document/"+globalrespanse.uid);
            }
            xhr.send( info.event.id);
        }
    });
    calendar.render();

    document.getElementById("addAbsent").addEventListener("click", addAbsence);
    document.getElementById("removeAbsance").addEventListener("click", removeAbsence);
});

function saveDocument(respance) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/save/doc?id="+respance);
    let formData = new FormData();
    formData.append("file", curfile[0]);
    xhr.send(formData);

}

function addAbsence() {
    if (startDate == null && endDate == null) {
        alert("you forget to pick a date from callende")
        return;
    }
    var emploeeInput = document.getElementById("inputEmployee");
    var absanceTypeInput = document.getElementById("absancetype");
    var descreptionInput = document.getElementById("Descreption");
    var absanceIdInput = document.getElementById("UID");
    var absanceDocInput= document.getElementById("absanceFile");
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/add/absance");

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => saveDocument(xhr.response);


    let data = '{"uid" : "'+absanceIdInput.value +'" , "emploeeID" : "' + emploeeInput.value +'" , "absanceID" :"' + absanceTypeInput.value +'" ,"discreption" :"'+ descreptionInput.value+ '", "start" : "'+ startDate.startStr + '","end" : "' + startDate.endStr + '"}';
    xhr.send(data);


    location.reload();
}

function handleFiles(file){
    curfile= file;
    myInstance.loadDocument(file[0]);
}