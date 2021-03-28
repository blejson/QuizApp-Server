import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

});

/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/quiz/'+ getParameterByName('quiz') + '/questions/', true);

    const request = {
        'question': document.getElementById('question').value,
        'answer0': document.getElementById('answer0').value,
        'answer1': document.getElementById('answer1').value,
        'answer2': document.getElementById('answer2').value,
        'answer3': document.getElementById('answer3').value,
        'goodAnswer': parseInt(document.getElementById('goodAnswer').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}