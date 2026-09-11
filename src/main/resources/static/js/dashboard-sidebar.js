document.addEventListener("DOMContentLoaded", function () {

    const sidebar =
        document.getElementById("dashboardSidebar");

    const toggle =
        document.getElementById("sidebarToggle");

    const dashboardPage =
        document.querySelector(".dashboard-page");


    if (!sidebar || !toggle || !dashboardPage) {

        return;

    }


    toggle.addEventListener("click", function () {

        sidebar.classList.toggle("collapsed");

        dashboardPage.classList.toggle("sidebar-collapsed");


        const isCollapsed =
            sidebar.classList.contains("collapsed");


        toggle.setAttribute(
            "aria-label",
            isCollapsed
                ? "Expand sidebar"
                : "Collapse sidebar"
        );


        /*
         * Remember the user's preference.
         */

        localStorage.setItem(
            "dashboardSidebarCollapsed",
            isCollapsed
        );

    });


    /*
     * Restore the previous sidebar state.
     */

    const savedState =
        localStorage.getItem(
            "dashboardSidebarCollapsed"
        );


    if (savedState === "true") {

        sidebar.classList.add("collapsed");

        dashboardPage.classList.add("sidebar-collapsed");

        toggle.setAttribute(
            "aria-label",
            "Expand sidebar"
        );

    }

});