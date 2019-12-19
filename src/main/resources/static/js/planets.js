
const toString = ({id, name,  size, upgrades, incomePerTrip, rating }) => {
    let columns = `
    <td>${name}</td>
    <td>${size}</td>
    <td>${upgrades}</td>
    <td>${incomePerTrip}</td>
    <td>${rating}</td>
`
    return `<tr>${columns}</tr>>`
};


fetch('/api/planets')
    .then(response => response.json())
    .then(planets => {
        let result = '';
        planets.forEach(planet => {
            const planetString = toString(planet);
            result += planetString;
        });
        document.getElementById('planets-table')
            .innerHTML=result;
    });
