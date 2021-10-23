const search = document.getElementById('btn-search-odontologo')
const searchInput = document.getElementById('matricula')

search.addEventListener('click', (event) => {
    event.preventDefault()
    const search = {
        search_value: searchInput.value
    }
    const settings = {
        method: 'GET'
    }
    const url = `http://localhost:8081/peluqueros/search/${searchInput.value}`
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
    $("#matricula").val("");
}

const odonto = document.querySelector('tbody')

function renderResults(data) {
    const info = {
        id: data.id,
        nombre: data.nombre,
        apellido: data.apellido,
        matricula: data.matricula
    }

    const row = document.createElement('tr')
    const id = document.createElement('td')
    const nombre = document.createElement('td')
    const apellido = document.createElement('td')
    const matricula = document.createElement('td')

    id.innerText = info.id
    nombre.innerText = info.nombre
    apellido.innerText = info.apellido
    matricula.innerText = info.matricula

    odonto.appendChild(row)
    row.appendChild(id)
    row.appendChild(nombre)
    row.appendChild(apellido)
    row.appendChild(matricula)
}