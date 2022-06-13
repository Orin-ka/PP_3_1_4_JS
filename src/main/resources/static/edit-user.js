async function editUser(modal, id) {
    let oneUser = await userFetch.findOneUser(id);
    let user = oneUser.json();

    modal.find('.modal-title').html('Edit user');

    let editButton = `<button  class="btn btn-info" id="editButton">Edit</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(editButton);
    modal.find('.modal-footer').append(closeButton);

    user.then(user => {
        let bodyForm = `
            <form class="form-group text-center" id="editUser">
               <div class="form-group">
                    <label for="userId" class="col-form-label">ID:</label>
                    <input type="text" class="form-control username" id="userId" value="${user.id}" readonly>
               </div>
               
               <div class="form-group">
                    <label for="firstName" class="com-form-label">FirstName:</label>
                    <input type="text" class="form-control" id="firstName" value="${user.firstName}">
                </div>

                <div class="form-group">
                    <label for="lastName" class="com-form-label">LastName:</label>
                    <input type="text" class="form-control" id="lastName" value="${user.lastName}">
                </div>
                
               <div class="form-group">
                    <label for="job" class="com-form-label">Job:</label>
                    <input type="text" class="form-control" id="job" value="${user.job}">
                </div> 
                   
               <div class="form-group">
                    <label for="username" class="col-form-label">Username:</label>
                    <input type="text" class="form-control username" id="username" value="${user.username}">
               </div>

                <div class="form-group">
                    <label for="password" class="com-form-label">Password:</label>
                    <input type="password" class="form-control" id="password" value="${user.password}">
                </div>

                <div class="form-group">
                    <label for="roles" class="com-form-label">Roles:</label>
                    <select multiple id="roles" size="2" class="form-control" style="max-height: 100px">
                    <option value="ROLE_USER">USER</option>
                    <option value="ROLE_ADMIN">ADMIN</option>
                    </select>
                </div>
            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    })

    $("#editButton").on('click', async () => {
        let checkedRoles = () => {
            let array = []
            let options = document.querySelector('#roles').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    array.push(roleList[i])
                }
            }
            return array;
        }
        let id = modal.find("#id").val().trim();
        let firstName = modal.find("#firstName").val().trim();
        let lastName = modal.find("#lastName").val().trim();
        let job = modal.find("#job").val().trim();
        let username = modal.find("#username").val().trim();
        let password = modal.find("#password").val().trim();
        let data = {
            id: id,
            firstName: firstName,
            lastName: lastName,
            job: job,
            username: username,
            password: password,
            roles: checkedRoles()

        }
        const response = await userFetch.updateUser(data, id);

        if (response.ok) {
            await getUsers();
            modal.modal('hide');
        } else {
            let body = await response.json();
            let alert = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="messageError">
                            ${body.info}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>`;
            modal.find('.modal-body').prepend(alert);
        }
    })
}