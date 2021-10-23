const formulario = document.querySelector('#add_new_peluquero')

formulario.addEventListener('submit', function(event) {
    event.preventDefault()
    const formData = {
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        matricula: document.querySelector('#matricula').value
    }
    const url = '/peluqueros/guardar'
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }
    fetch(url, settings)
    .then(response => response.json())
    .then(data => {
        let successAlert = `<div class='alert alert-success alert-dismissible'>` + 
        `<button type='button' class='close' data-dismiss='alert'>&times;</button>` + 
        `<strong>Peluquero agregado con éxito.</strong></div>`
        $('#response').append(successAlert)
        $('#response').css({"display":"block"})
    })
    .catch(error => {
        let errorAlert = `<div class='alert alert-danger alert-dismissible'>` + 
        `<button type='button' class='close' data-dismiss='alert'>&times;</button>` + 
        `<strong>Error. Intente nuevamente.</strong></div>`
        $('#response').append(errorAlert)
        $('#response').css({"display":"block"})
    })
    resetUploadForm();
})

function resetUploadForm(){
    $("#nombre").val("");
    $("#apellido").val("");
    $("#matricula").val("");
}