window.onload = async function (event) {
    event.preventDefault()
    const url = '/perros'
    const settings = {
        method: 'GET'
    }
    const response = await fetch(url, settings)
    const data = await response.json()
    renderResultsPerro(data)

    const url1 = '/peluqueros'
    const response1 = await fetch(url1, settings)
    const data1 = await response1.json()
    renderResultsPeluquero(data1)
}

function renderResultsPerro(data) {
    const perros = document.querySelector('#perro');
    perros.innerHTML = '';
    for (let i = 0; i < data.length; i++) {
        const element = data[i];
        perros.innerHTML += `
        <option value=${element.id}>${element.nombre}</option>
        `
    }
}

function renderResultsPeluquero(data) {
    const peluqueros = document.querySelector('#peluquero');
    peluqueros.innerHTML = '';
    for (let i = 0; i < data.length; i++) {
        const element = data[i];
        peluqueros.innerHTML += `
        <option value=${element.id}>${element.nombre} ${element.apellido}</option>
        `
    }
}

const formulario = document.querySelector('#add_new_turno')
formulario.addEventListener('submit', function(event) {
    event.preventDefault()

    const formData = {
        perro: {id: document.querySelector('#perro').value},
        peluquero: {id: document.querySelector('#peluquero').value},
        date: document.querySelector('#fecha').value,
        time: document.querySelector('#hora').value
    }
    const url = '/turnos/guardar'
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
        `<strong>Turno guardado con éxito.</strong></div>`
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
    $("#perro").val("");
    $("#peluquero").val("");
    $("#fecha").val("");
    $("#hora").val("");
}