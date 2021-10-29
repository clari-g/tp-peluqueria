window.onload = function (event) {
    event.preventDefault()
    loadPerros()
}

async function loadPerros() {
    const url = '/perros'
    const settings = {
        method: 'GET'
    }
    const response = await fetch(url, settings)
    const data = await response.json()
    renderResults(data)
}

function renderResults(data) {
    const perrosTabla = document.querySelector('tbody')
    perrosTabla.innerHTML = ''
    data.forEach(async element => {
        const info = {
            id: element.id,
            nombre: element.nombre,
            raza: element.raza,
            dueno: element.dueno
        }

        const row = document.createElement('tr')
        const id = document.createElement('td')
        const nombre = document.createElement('td')
        const raza = document.createElement('td')
        const dueno = document.createElement('td')
        const acciones = document.createElement('td')

        const botonEditar = document.createElement('a')
        botonEditar.setAttribute("class", "btn btn-primary btn-edit")
        botonEditar.setAttribute("href", "modifPerros.html?id=" + info.id )
        botonEditar.innerText = 'Editar'

        const botonEliminar = document.createElement('button')
        botonEliminar.setAttribute("class", "btn btn-danger")
        botonEliminar.setAttribute("onclick", "deletePerros(" + info.id +")")
        botonEliminar.innerText = 'Eliminar'

        id.innerText = info.id
        nombre.innerText = info.nombre
        raza.innerText = info.raza
        dueno.innerText = info.dueno

        perrosTabla.appendChild(row)
        row.appendChild(id)
        row.appendChild(nombre)
        row.appendChild(raza)
        row.appendChild(dueno)
        acciones.appendChild(botonEditar)
        acciones.appendChild(botonEliminar)
        row.appendChild(acciones)
    })
}

function deletePerros(idNum) {
    const url = '/perros/eliminar/' + idNum
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
            `<strong>Perro eliminado con éxito.</strong></div>`
            $('#response').append(successAlert)
            $('#response').css({"display":"block"})
            loadPerros();
        })
        .catch(error => {
            let errorAlert = `<div class='alert alert-danger alert-dismissible'>` +
            `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
            `<strong>Error. Intente nuevamente.</strong></div>`
            $('#response').append(errorAlert)
            $('#response').css({"display":"block"})
        })
}