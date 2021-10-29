window.onload = function (event) {
    event.preventDefault()
    loadTurnos()
}

async function loadTurnos() {
    const url = '/turnos'
    const settings = {
        method: 'GET'
    }
    const response = await fetch(url, settings)
    const data = await response.json()
    renderResults(data)
}

function renderResults(data) {
    const turnos = document.querySelector('tbody')
    turnos.innerHTML = ''
    data.forEach(async element => {
        const info = {
            id: element.id,
            perro: element.perro.nombre,
            peluquero: element.peluquero.nombre + ' ' + element.peluquero.apellido,
            date: moment(element.date + ' ' + element.time).format('DD/MM/YYYY'),
            time: moment(element.date + ' ' + element.time).format('hh:mm')
        }

        const row = document.createElement('tr')
        const id = document.createElement('td')
        const perro = document.createElement('td')
        const peluquero = document.createElement('td')
        const date = document.createElement('td')
        const time = document.createElement('td')
        const acciones = document.createElement('td')

        const botonEditar = document.createElement('a')
        botonEditar.setAttribute("class", "btn btn-primary btn-edit")
        botonEditar.setAttribute("href", "modifTurnos.html?id=" + info.id )
        botonEditar.innerText = 'Editar'

        const botonEliminar = document.createElement('button')
        botonEliminar.setAttribute("class", "btn btn-danger")
        botonEliminar.setAttribute("onclick", "deleteTurnos(" + info.id +")")
        botonEliminar.innerText = 'Eliminar'

        id.innerText = info.id
        perro.innerText = info.perro
        peluquero.innerText = info.peluquero
        date.innerText = info.date
        time.innerText = info.time

        turnos.appendChild(row)
        row.appendChild(id)
        row.appendChild(perro)
        row.appendChild(peluquero)
        row.appendChild(date)
        row.appendChild(time)
        acciones.appendChild(botonEditar)
        acciones.appendChild(botonEliminar)
        row.appendChild(acciones)
    })
}

function deleteTurnos(idNum) {
    const url = '/turnos/eliminar/' + idNum
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
            `<strong>Turno eliminado con éxito.</strong></div>`
            $('#response').append(successAlert)
            $('#response').css({"display":"block"})
            loadTurnos();
        })
        .catch(error => {
            let errorAlert = `<div class='alert alert-danger alert-dismissible'>` +
            `<button type='button' class='close' data-dismiss='alert'>&times;</button>` +
            `<strong>Error. Intente nuevamente.</strong></div>`
            $('#response').append(errorAlert)
            $('#response').css({"display":"block"})
        })
}