window.onload = async function (event) {
    event.preventDefault()
    const url = '/pacientes'
    const settings = {
        method: 'GET'
    }
    const response = await fetch(url, settings)
    const data = await response.json()
    renderResultsPaciente(data)
    const url1 = '/odontologos'
    const response1 = await fetch(url1, settings)
    const data1 = await response1.json()
    renderResultsOdontologo(data1)
}

const pacientes = document.querySelector('#paciente')
function renderResultsPaciente(data) {
    newData = data
    for (let i = 0; i < data.length; i++) {
        const element = data[i];
        const info = {
            dni: element.dni
        }
        pacientes.innerHTML += `
        <option value=${info.dni}>${info.dni}</option> 
        `
    }
}

const odontologos = document.querySelector('#odontologo')
function renderResultsOdontologo(data) {
    newData = data

    for (let i = 0; i < data.length; i++) {
        const element = data[i];
        const info = {
            matricula: element.matricula
        }
        odontologos.innerHTML += `
        <option value=${info.matricula}>${info.matricula}</option> 
        `
    }
}

const formulario = document.querySelector('#add_new_turno')
formulario.addEventListener('submit', function(event) {
    event.preventDefault()

    const formData = {
        paciente: {dni: document.querySelector('#paciente').value},
        odontologo: {matricula: document.querySelector('#odontologo').value},
        date: document.querySelector('#fecha').value,
        time: document.querySelector('#hora').value
    }
    const url = '/turno/guardar'
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
        console.log(data);
        let successAlert = `<div class='alert alert-success alert-dismissible'>` + 
        `<button type='button' class='close' data-dismiss='alert'>&times;</button>` + 
        `<strong>Turno agregado con éxito.</strong></div>`
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
    $("#paciente").val("");
    $("#odontologo").val("");
    $("#fecha").val("");
    $("#hora").val("");
}