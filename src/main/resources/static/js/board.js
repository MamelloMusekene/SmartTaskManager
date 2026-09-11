/* global Sortable */

document.addEventListener("DOMContentLoaded", function () {

    initializeKanban();

});


function initializeKanban() {

    const columns =
        document.querySelectorAll(".kanban-column");

    if (columns.length === 0) {
        return;
    }

    if (typeof Sortable === "undefined") {

        console.error("SortableJS is not loaded.");

        return;
    }


    columns.forEach(function (column) {

        new Sortable(column, {

            group: "tasks",

            animation: 150,

            ghostClass: "task-ghost",

            chosenClass: "task-chosen",

            dragClass: "task-dragging",

            onEnd: function (event) {

                const taskElement = event.item;

                const taskId = taskElement.dataset.id;

                const targetColumn = event.to;

                if (!taskId) {

                    console.error(
                        "No task ID found on dragged task."
                    );

                    return;
                }


                let status = null;


                if (targetColumn.id === "pending-column") {

                    status = "PENDING";

                } else if (
                    targetColumn.id === "progress-column"
                ) {

                    status = "IN_PROGRESS";

                } else if (
                    targetColumn.id === "completed-column"
                ) {

                    status = "COMPLETED";

                }


                if (!status) {

                    console.error(
                        "Could not determine task status."
                    );

                    return;
                }


                updateTaskStatus(taskId, status);

            }

        });

    });

}


function updateTaskStatus(taskId, status) {

    const url = `/tasks/${taskId}/status`;


    const formData = new URLSearchParams();

    formData.append("status", status);


    const csrfTokenElement =
        document.querySelector('meta[name="_csrf"]');

    const csrfHeaderElement =
        document.querySelector('meta[name="_csrf_header"]');


    const csrfToken =
        csrfTokenElement
            ? csrfTokenElement.getAttribute("content")
            : null;


    const csrfHeader =
        csrfHeaderElement
            ? csrfHeaderElement.getAttribute("content")
            : null;


    const headers = {

        "Content-Type":
            "application/x-www-form-urlencoded",

        "X-Requested-With":
            "XMLHttpRequest"

    };


    if (csrfToken && csrfHeader) {

        headers[csrfHeader] = csrfToken;

    }


    fetch(url, {

        method: "POST",

        headers: headers,

        body: formData.toString()

    })



        .then(function (response) {

            if (!response.ok) {

                throw new Error(
                    `Server returned ${response.status}`
                );

            }

            return response;

        })


        .then(function () {

            console.log(
                `Task ${taskId} successfully changed to ${status}`
            );

            /*
             * Reload the board so the task appears
             * in the correct column.
             */

            window.location.reload();

        })


        .catch(function (error) {

            console.error(
                "Failed to update task status:",
                error
            );

            alert(
                "The task status could not be updated. Please try again."
            );

        });

}