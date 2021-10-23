const search = document.getElementById('btn-search-paciente')
const searchInput = document.getElementById('dni')

search.addEventListener('click', (event) => {
    event.preventDefault()
    const search = {
        search_value: searchInput.value
    }
    const settings = {
        method: 'GET'
    }
    const url = `http://localhost:8080/perro/${searchInput.value}`
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            renderResults(data)
        })
        .catch(error => {
            let errorAlert = `<div class='alert alert-danger alert-dismissible'>` +
                `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
                `<strong>Error. Intente nuevamente.</strong></div>`
            $('#response').append(errorAlert)
            $('#response').css({ "display": "block" })
        })
    resetUploadForm()
})

function resetUploadForm() {
    $("#dni").val("");
}

const paciente = document.querySelector('tbody')

function renderResults(data) {
    const info = {
        id: data.id,
        nombre: data.nombre,
        apellido: data.apellido,
        domicilio: data.domicilio,
        dni: data.dni,
        fechaAlta: data.fechaAlta,
    }

    const row = document.createElement('tr')
    const id = document.createElement('td')
    const nombre = document.createElement('td')
    const apellido = document.createElement('td')
    const domicilio = document.createElement('td')
    const dni = document.createElement('td')
    const fechaAlta = document.createElement('td')

    id.innerText = info.id
    nombre.innerText = info.nombre
    apellido.innerText = info.apellido
    domicilio.innerText = info.domicilio
    dni.innerText = info.dni
    fechaAlta.innerText = info.fechaAlta

    paciente.appendChild(row)
    row.appendChild(id)
    row.appendChild(nombre)
    row.appendChild(apellido)
    row.appendChild(domicilio)
    row.appendChild(dni)
    row.appendChild(fechaAlta)
}