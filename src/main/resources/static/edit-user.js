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
                    <label for="id" class="col-form-label">ID:</label>
                    <input type="text" class="form-control username" id="id" value=${id} readonly>
               </div>
               
                <div class="form-group">
                    <label for="firstname" class="com-form-label">Firstname:</label>
                    <input type="text" name="firstname" class="form-control" id="firstname" value="${user.firstname}">
                </div>

                
                Lastname:
                <input type="text" name="lastname"  class="form-control" id="lastname" value="${user.lastname}">
                
                
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
        let firstname = modal.find("#firstname").val().trim();
        let lastname = modal.find("#lastname").val().trim();
        let job = modal.find("#job").val().trim();
        let username = modal.find("#username").val().trim();
        let password = modal.find("#password").val().trim();
        let data = {
            id: id,
            firstname: firstname,
            lastname: lastname,
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