function localDateToDate(localDate) {
    return `${localDate[0]}-${localDate[1]}-${localDate[2]}`
}

function localDateTimeToDate(localDate) {
    return `${localDate[0]}-${localDate[1]}-${localDate[2]} ${localDate[3]}:${localDate[4]}:${localDate[5]}`
}

function findAssigneeName(employees, id) {
    let filtered = employees.filter(emp => emp.id === id);
    return filtered.length > 0 ? filtered[0].name : "None"; 
}

module.exports.localDateToDate = localDateToDate;
module.exports.localDateTimeToDate = localDateTimeToDate;
module.exports.findAssigneeName = findAssigneeName;