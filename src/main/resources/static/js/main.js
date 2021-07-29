$(document).ready(getAllUsers())

function getAllUsers() {
    let table = $("#mainTableWithUsers");
    table.empty();
    fetch('api/users')
        .then(res => res.json())
        .then(data => {
            $.each(data, function (i, user) {
                let userRole = "";
                for (let i = 0; i < user.userRoles.length; i++) {
                    userRole += " " + user.userRoles[i].role;
                }
                $("#mainTableWithUsers").append(`<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td> 
                    <td>${user.age}</td> 
                    <td>${user.email}</td> 
                    <td>${userRole}</td> 
                    
                    <td><button onclick="getModalUpdate(${user.id})" class="btn btn-primary">Edit</button></td>
                    
                    <td><button onclick="getModalDelete(${user.id})" class="btn btn-danger">Delete</button></td> 
                    </tr>`
                );
            });
        })
}

// __________________________________updateModal__________________________

function getModalUpdate(id) {
    fetch('api/users/' + id)
        .then(response => response.json())
        .then(user => {
            let modal = document.getElementById('modalWindow');
            modal.innerHTML =
                '<div id="modalUpdate" class="modal fade" aria-hidden="true">' +
                '    <div class="modal-dialog">' +
                '        <div class="modal-content">' +
                '            <div class="modal-header">' +
                '                <h5 class="modal-title">Edit user</h5>' +
                '                <button type="button" class="btn-close btn-sm" data-bs-dismiss="modal" aria-label="Close"></button>' +
                '                </button>' +
                '            </div>' +
                '            <div class="modal-body">' +
                '                <div class="row justify-content-center">' +
                 '                   <div class="col-sm-6 text-center">' +
                        '                <form id="form_update_user">' +
                        '                        <label class="form-label mb-0 fw-bold">ID</label>' +
                        '                        <input class="form-control form-control-sm mb-3" type="number"' +
                        '                               id="update_Id" name="id" value="' +  user.id + '" readonly>' +

                        '                        <label class="form-label mb-0 fw-bold">Name</label>' +
                        '                        <input class="form-control form-control-sm mb-3" type="text"' +
                        '                               id="update_Name" value="' + user.name + '"' +
                        '                               placeholder="Name" required>' +

                        '                        <label class="form-label mb-0 fw-bold">Age</label>' +
                        '                        <input class="form-control form-control-sm" type="number"' +
                        '                               id="update_Age" value="' + user.age + '" ' +
                        '                               placeholder="Age" required>' +

                        '                        <label class="form-label mb-0 fw-bold">Email</label>' +
                        '                        <input class="form-control form-control-sm" type="email"' +
                        '                               id="update_Email" value="' + user.email + '"' +
                        '                               placeholder="Email" required>' +

                        '                        <label class="form-label mb-0 fw-bold">Password</label>' +
                        '                        <input class="form-control form-control-sm" type="password"' +
                        '                               id="update_Password" value="' + user.password + '"' +
                        '                               placeholder="Password" required>' +

                        '                        <label class="form-label mb-0 fw-bold">Role</label>' +
                        '                        <select required id="update_Roles" name="roles" multiple size="2"' +
                        '                               class="form-control form-control-sm"' +
                        '                               required> ' +
                        '                            <option value="ROLE_ADMIN"' + '>ADMIN</option>' +
                        '                            <option value="ROLE_USER"' + '>USER</option>' +
                        '                        </select>' +
                        '                    </p>' +
                        '                </form>' +
                '                     </div>' +
                '                 </div>' +
                '            </div>' +
                '            <div class="modal-footer">' +
                '                <button type="button" class="btn btn-secondary"' +
                '                        data-bs-dismiss="modal">Close</button>' +
                '                <button type="submit" class="btn btn-primary" data-bs-dismiss="modal"' +
                '                        onclick="updateUser()">Edit</button>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';
            $("#modalUpdate").modal('show');

        });

}

function updateUser(){
    let id = document.getElementById("update_Id").value;
    let name = document.getElementById("update_Name").value;
    let age = document.getElementById("update_Age").value;
    let email = document.getElementById("update_Email").value;
    let password = document.getElementById("update_Password").value;
    let roles = getUserRole(Array.from(document.getElementById("update_Roles").selectedOptions).map(options => options.value))


    if (name.length === 0){
        return alert("заполните поле Name")
    }else if(age.length === 0){
        return alert("заполните поле Age")
    }else if(email.length === 0){
        return alert("заполните поле Email")
    }else if(password.length === 0){
        return alert("заполните поле Password")
    }else if(roles.length === 0){
        return alert("заполните поле Role")
    }


    fetch('api/users/' + id, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            name: name,
            age: age,
            email: email,
            password: password,
            userRoles: roles
        }),
    }).then(() => getAllUsers())
}

// __________________________________deleteModal__________________________

function getModalDelete(id) {

    fetch('api/users/' + id)
        .then(response => response.json())
        .then(user => {
            let modal = document.getElementById('modalWindow');
            modal.innerHTML =
                '<div id="modalDelete" class="modal fade" aria-hidden="true"> ' +
                '    <div class="modal-dialog">' +
                '        <div class="modal-content">' +
                '            <div class="modal-header">' +
                '                <h5 class="modal-title">Delete user</h5>' +
                '                <button type="button" class="btn-close btn-sm" data-bs-dismiss="modal" aria-label="Close"></button>' +
                '                </button>' +
                '            </div>' +
                '            <div class="modal-body">' +
                '                <div class="row justify-content-center">' +
                '                   <div class="col-sm-6 text-center">' +
                '                <form id="form_delete_user">' +
                '                        <label class="form-label mb-0 fw-bold">ID</label>' +
                '                        <input class="form-control form-control-sm mb-3" type="number"' +
                '                               name="id" value="' +  user.id + '" readonly>' +

                '                        <label class="form-label mb-0 fw-bold">Name</label>' +
                '                        <input class="form-control form-control-sm mb-3" type="text"' +
                '                               value="' + user.name + '"' +
                '                               placeholder="Name" readonly>' +

                '                        <label class="form-label mb-0 fw-bold">Age</label>' +
                '                        <input class="form-control form-control-sm" type="number"' +
                '                               value="' + user.age + '" ' +
                '                               placeholder="Age" readonly>' +

                '                        <label class="form-label mb-0 fw-bold">Email</label>' +
                '                        <input class="form-control form-control-sm" type="email"' +
                '                               value="' + user.email + '"' +
                '                               placeholder="Email" readonly>' +

                '                        <label class="form-label mb-0 fw-bold">Password</label>' +
                '                        <input class="form-control form-control-sm" type="password"' +
                '                               value="' + user.password + '"' +
                '                               placeholder="Password" readonly>' +

                '                        <label class="form-label mb-0 fw-bold">Role</label>' +
                '                        <select name="roles" multiple size="2"' +
                '                               class="form-control form-control-sm" readonly>' +
                '                            <option value="ROLE_ADMIN"' + '>ADMIN</option>' +
                '                            <option value="ROLE_USER"' + '>USER</option>' +
                '                        </select>' +
                '                    </p>' +
                '                </form>' +
                '                     </div>' +
                '                 </div>' +
                '            </div>' +
                '            <div class="modal-footer">' +
                '                <button type="button" class="btn btn-secondary"' +
                '                        data-bs-dismiss="modal">Close</button>' +
                '                <button type="submit" class="btn btn-danger" data-bs-dismiss="modal"' +
                '                        onclick="deleteUser(' +user.id+ ')">Delete</button>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';

            $("#modalDelete").modal('show');
        });
}


function deleteUser(id) {
    fetch('api/users/' + id, {
        method: 'DELETE',
        headers: {"Content-type": "application/json; charset=UTF-8"}
    }).then(() => getAllUsers())

}

// __________________________________addNewUser__________________________

function getUserRole(setRole) {
    let roles = []

    if (setRole.indexOf("ROLE_ADMIN") !== -1) {
        roles.push({id: 2, role: "ROLE_ADMIN", authority: "ROLE_ADMIN"})
    }else if (setRole.indexOf("ROLE_USER") !== -1) {
        roles.push({id: 1, role: "ROLE_USER", authority: "ROLE_USER"})
    }
    return roles
}

function addNewUser(){
    let name = document.getElementById("create_Name").value;
    let age = document.getElementById("create_Age").value;
    let email = document.getElementById("create_Email").value;
    let password = document.getElementById("create_Password").value;
    let roles = getUserRole(Array.from(document.getElementById("create_Role").selectedOptions).map(options => options.value))

    if (name.length === 0){
        return ("заполните поле Name")
    }else if(age.length === 0){
        return ("заполните поле Age")
    }else if(email.length === 0){
        return ("заполните поле Email")
    }else if(password.length === 0){
        return ("заполните поле Password")
    }else if(roles.length === 0){
        return ("заполните поле Role")
    }

    let user = {
        name: name,
        age: age,
        email: email,
        password: password,
        userRoles: roles
    }
    console.log(user)

    fetch("api/users", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user), })
        .then(response => {
            console.log(response.text());
        }).then(() => getAllUsers())

}

