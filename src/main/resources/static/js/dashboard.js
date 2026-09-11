/* global Chart */


/* =========================================
   DASHBOARD INITIALIZATION
========================================= */

document.addEventListener("DOMContentLoaded", function () {

    initializeTaskChart();

});


/* =========================================
   TASK COMPLETION CHART
========================================= */

function initializeTaskChart() {

    const chartElement =
        document.getElementById("taskChart");


    if (!chartElement) {
        return;
    }


    const completed =
        Number(chartElement.dataset.completed || 0);

    const pending =
        Number(chartElement.dataset.pending || 0);


    new Chart(chartElement, {

        type: "doughnut",

        data: {

            labels: [
                "Completed",
                "Pending"
            ],

            datasets: [{

                data: [
                    completed,
                    pending
                ],

                borderWidth: 0,

                hoverOffset: 6

            }]

        },

        options: {

            responsive: true,

            maintainAspectRatio: false,

            cutout: "72%",

            plugins: {

                legend: {

                    position: "bottom",

                    labels: {

                        usePointStyle: true,

                        padding: 20,

                        font: {
                            size: 12
                        }

                    }

                }

            }

        }

    });

}