window.onload = function (event) {
    event.preventDefault()
    loadPeluqueros()
}

async function loadPeluqueros() {
    const url = '/peluqueros'
    const settings = {
        method: 'GET'
    }
    const response = await fetch(url, settings)
    const data = await response.json()
    renderResults(data)
}

function renderResults(data) {
    const peluquerosTabla = document.querySelector('tbody')
    peluquerosTabla.innerHTML = ''
    data.forEach(async element => {
        const info = {
            id: element.id,
            nombre: element.nombre,
            apellido: element.apellido,
            matricula: element.matricula
        }

        const row = document.createElement('tr')
        const id = document.createElement('td')
        const nombre = document.createElement('td')
        const apellido = document.createElement('td')
        const matricula = document.createElement('td')
        const acciones = document.createElement('td')

        const botonEditar = document.createElement('a')
        botonEditar.setAttribute("class", "btn btn-primary btn-edit")
        botonEditar.setAttribute("href", "modifPeluqueros.html?id=" + info.id )
        botonEditar.innerText = 'Editar'

        const botonEliminar = document.createElement('button')
        botonEliminar.setAttribute("class", "btn btn-danger")
        botonEliminar.setAttribute("onclick", "deletePeluqueros(" + info.id +")")
        botonEliminar.innerText = 'Eliminar'

        id.innerText = info.id
        nombre.innerText = info.nombre
        apellido.innerText = info.apellido
        matricula.innerText = info.matricula

        peluquerosTabla.appendChild(row)
        row.appendChild(id)
        row.appendChild(nombre)
        row.appendChild(apellido)
        row.appendChild(matricula)
        acciones.appendChild(botonEditar)
        acciones.appendChild(botonEliminar)
        row.appendChild(acciones)
    })
}

function deletePeluqueros(idNum) {
    const url = '/peluqueros/eliminar/' + idNum
    const formData = {
            id: idNum
        }
        const settings = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
        .then(response => console.log(response))
        .then(data => {
            let successAlert = `<div class='alert alert-success alert-dismissible'>` +
            `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
            `<strong>Peluquero eliminado con éxito.</strong></div>`
            $('#response').append(successAlert)
            $('#response').css({"display":"block"})
            loadPeluqueros();
        })
        .catch(error => {
            let errorAlert = `<div class='alert alert-danger alert-dismissible'>` +
            `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
            `<strong>Error. Intente nuevamente.</strong></div>`
            $('#response').append(errorAlert)
            $('#response').css({"display":"block"})
        })
}