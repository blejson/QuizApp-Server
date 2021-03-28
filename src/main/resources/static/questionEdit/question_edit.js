import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayQuestion();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplayQuestion() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/quiz/' + getParameterByName('quiz') + '/questions/'
        + getParameterByName('question'), true);
    xhttp.send();
}

/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayQuestion();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/quiz/' + getParameterByName('quiz') + '/questions/'
        + getParameterByName('question'), true);

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