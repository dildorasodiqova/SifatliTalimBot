function check() {
    let elements = document.getElementsByClassName('user-check');
    let count = 0;
    for (let i = 0; i < elements.length; i++) {
        let element = elements.item(i);
        if (document.getElementById(element.id).checked) {
            count++;
        }
    }
    document.getElementById('head-user-check').checked = count === elements.length;

}

function checkAllUsers() {
    let elements = document.getElementsByClassName('user-check');
    for (let i = 0; i < elements.length; i++) {
        let element = elements.item(i);
        document.getElementById(element.id).checked = document.getElementById('head-user-check').checked;
    }
}

function unCheckAllUsers() {

}

function showEditPaidForm(e) {
    console.log(e.target.id);
    document.getElementById('edit-paid-form').style.display = 'block';
}

document.getElementById('edit-paid2').addEventListener('click', function (event) {


    const relatedTarget = event.relatedTarget;
    const groupId = parseInt(relatedTarget.getAttribute('data-user-id'));
    console.log(groupId);
})
$(function () {
    $('[data-toggle="tooltip"]').tooltip()
})