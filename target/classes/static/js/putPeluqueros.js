window.onload = async function (event) {
    let params = (new URL(document.location)).searchParams;
    let id = params.get("id");
    const url = '/peluqueros/get/' + id
        const settings = {
            method: 'GET'
        }
        const response = await fetch(url, settings)
        const data = await response.json()
        loadForm(data)
}

const formulario = document.querySelector('#modify_peluquero')

formulario.addEventListener('submit', function(event) {
    event.preventDefault()

    const dataId = document.querySelector('#id').value
    const dataNombre = document.querySelector('#nombre').value
    const dataApellido = document.querySelector('#apellido').value
    const dataMatricula = document.querySelector('#matricula').value

    const formDataModif = {
        id: dataId,
        nombre: dataNombre,
        apellido: dataApellido,
        matricula: dataMatricula
    }
    const url = '/peluqueros/actualizar'
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formDataModif)
    }

    fetch(url, settings)
    .then(response => response.json())
    .then(data => {
        let successAlert = `<div class='alert alert-success alert-dismissible'>` +
        `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
        `<strong>Peluquero actualizado con éxito.</strong></div>`
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
})

function loadForm(data){
    $("#id").val(data.id);
    $("#nombre").val(data.nombre);
    $("#apellido").val(data.apellido);
    $("#matricula").val(data.matricula);
}