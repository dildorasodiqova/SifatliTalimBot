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