window.onload = async function (event) {
    let params = (new URL(document.location)).searchParams;
    let id = params.get("id");
    const url = '/perros/get/' + id
        const settings = {
            method: 'GET'
        }
        const response = await fetch(url, settings)
        const data = await response.json()
        loadForm(data)
}

const formulario = document.querySelector('#modify_perro')

formulario.addEventListener('submit', function(event) {
    event.preventDefault()

    const dataId = document.querySelector('#id').value
    const dataNombre = document.querySelector('#nombre').value
    const dataRaza = document.querySelector('#raza').value
    const dataDueno = document.querySelector('#dueno').value

    const formDataModif = {
        id: dataId,
        nombre: dataNombre,
        raza: dataRaza,
        dueno: dataDueno
    }
    const url = '/perros/actualizar'
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
        `<strong>Perro actualizado con éxito.</strong></div>`
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
    $("#raza").val(data.raza);
    $("#dueno").val(data.dueno);
}