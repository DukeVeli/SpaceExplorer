const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    planet: '/api/planet',
};

const toString = ({ id, name, planet, size, upgrades, incomePerTrip, rating }) => {
    let columns = `
    <td>${name}</td>
    <td>${planet}</td>
    <td>${size}</td>
    <td>${upgrades}</td>
    <td>${incomePerTrip}</td>
    <td>${rating}</td>
    
`
    columns += rating
        ? '<td></td>'
        : `<td>
            <form class="rate-planet-form" data-id=${planet.id} action="/api/planet/add-rating-user/${planet.id}" method="post">
                <button class="btn btn-info">Rate</button>
            </form>
           </td>`
    return `<tr>${columns}</tr>`
};

loader.show();
fetch(URLS.planet)
    .then(response => response.json())
    .then(planet => {
        let result = '';
        planet.forEach(planet => {
            const planetString = toString(planet);
            result += planetString;
        });

        $('#planet-table').html(result);
        loader.hide();
    });

$('#planet-table').on('submit', '.rate-planet-form', function (ev) {
    const url = $(this).attr('action');

    loader.show();
    fetch(url, { method: 'post' })
        .then(data => {
            console.log(data)
            loader.hide();
            window.location = '/planet/planets';
        });

    ev.preventDefault();
    return false;
});

const API_KEY = 'vOj2aP4GDvKMetSD490cdgCEOM8X5Pc35R7ipLgd';

fetch(`https://api.nal.usda.gov/fdc/v1/search?api_key=${API_KEY}`, {
    method: 'post',
    body: JSON.stringify({
        generalSearchInput: 'pizza',
    })
})
    .then(resp => resp.json())
    .then(x => window.location.reload());