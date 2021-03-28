import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    getParameterByName
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayQuizzes();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayQuizzes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayQuizzes(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/quiz', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display users.
 *
 * @param {{users: string[]}} quizzes
 */
function displayQuizzes(quizzes) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    quizzes.quizzes.forEach(quiz => {
        tableBody.appendChild(createTableRow(quiz.id));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} quiz
 * @returns {HTMLTableRowElement}
 */
function createTableRow(quiz) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(quiz));
    tr.appendChild(createLinkCell('view', '../quizView/quiz_view.html?quiz=' + quiz));
    tr.appendChild(createButtonCell('delete', () => deleteQuiz(quiz)));
    return tr;
}

function deleteQuiz(quiz) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayQuizzes();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/quiz/' + quiz, true);
    xhttp.send();
}
