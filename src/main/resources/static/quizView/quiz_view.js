import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayQuiz();
    fetchAndDisplayQuestions();
    let linkTable = document.getElementById('link');
    clearElementChildren(linkTable);
    let tr = document.createElement('tr');
    tr.appendChild(createLinkCell('Add new', '../questionCreate/question_create.html?quiz='
        + getParameterByName('quiz')));
    linkTable.appendChild(tr);
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayQuestions() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayQuestions(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/quiz/' + getParameterByName('quiz') + '/questions', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display characters.
 *
 * @param {{clients: {id: number, name:string}[]}} questions
 */
function displayQuestions(questions) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    questions.questions.forEach(question => {
        tableBody.appendChild(createTableRow(question));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} character
 * @returns {HTMLTableRowElement}
 */
function createTableRow(question) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(question.question));
    tr.appendChild(createTextCell(question.answer0));
    tr.appendChild(createTextCell(question.answer1));
    tr.appendChild(createTextCell(question.answer2));
    tr.appendChild(createTextCell(question.answer3));
    tr.appendChild(createTextCell(question.goodAnswer));
    tr.appendChild(createLinkCell('edit', '../questionEdit/question_edit.html?quiz='
        + getParameterByName('quiz') + '&question=' + question.id));
    tr.appendChild(createButtonCell('delete', () => deleteQuestion(question.id)));
  return tr;
}
function deleteQuestion(question) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayQuestions();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/quiz/' + getParameterByName('quiz')
        + '/questions/' + question, true);
    xhttp.send();
}
/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayQuiz() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayQuiz(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/quiz/' + getParameterByName('quiz'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{id: string, author: string}} quiz
 */
function displayQuiz(quiz) {
    setTextNode('idd', quiz.id);
    setTextNode('id', quiz.id);
    setTextNode('author', quiz.author);
}