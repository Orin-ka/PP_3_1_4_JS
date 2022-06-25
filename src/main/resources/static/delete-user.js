async function deleteUser(modal, id) {
    let oneUser = await userFetch.findOneUser(id);
    let user = oneUser.json();

    modal.find('.modal-title').html('Delete user');

    let deleteButton = `<button  class="btn btn-danger" id="deleteButton">Delete</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
    modal.find('.modal-footer').append(deleteButton);
    modal.find('.modal-footer').append(closeButton);

    user.then(user => {

        let bodyForm = `
            <form class="form-group text-center" id="deleteUser">
               <div class="form-group">
                    <label for="id" class="col-form-label">ID</label>
                    <input type="text" class="form-control username" id="id" value="${user.id}" readonly>
               </div>
               
               <div class="form-group">
                    <label for="firstname" class="com-form-label">Firstname:</label>
                    <input type="text" class="form-control" id="firstname" value="${user.firstname}" readonly>
                </div>

                <div class="form-group">
                    <label for="lastname" class="com-form-label">Lastname:</label>
                    <input type="text" class="form-control" id="lastname" value="${user.lastname}" readonly>
                </div>
                   
               <div class="form-group">
                    <label for="username" class="col-form-label">Username:</label>
                    <input type="text" class="form-control username" id="username" value="${user.username}" readonly>
               </div>
               
               <div class="form-group">
                    <label for="job" class="col-form-label">Job:</label>
                    <input type="text" class="form-control " id="job" value="${user.job}" readonly>
               </div>

                <div class="form-group">
                    <label for="roles" class="com-form-label">Roles:</label>
                    <select id="roles" class="form-control select" size="2" name="roles" style="max-height: 100px" disabled>
                        <option>${user.roles.map(role => " " + role.name.substr(5))}</option>
                    </select>
                </div>

            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    })

    $("#deleteButton").on('click', async () => {
        const response = await userFetch.deleteUser(id);

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