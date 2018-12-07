
function inicializar() {
    f1();
}
function f1() {
    actualizar();
    setTimeout(f1, 61000);
}
function actualizar() {
    loadText(function (textoTabla) {
        var reftabla = document.getElementById("refComment");
        if (reftabla) {
            reftabla.innerHTML = textoTabla;

        }
    }, "ServicioObtenerChat");
}



function obtenerUsuariosConectados() {
    fusuarios();
}
function fusuarios() {
    actualizarUsuarios();
    setTimeout(fusuarios, 61000);
}
function actualizarUsuarios() {
    loadText(function (textoTabla) {
        var reftabla = document.getElementById("refUserConneted");
        if (reftabla) {
            reftabla.innerHTML = textoTabla;

        }
    }, "ServicioUsuariosConectados");
    loadText(function (textoTabla) {
        var reftabla = document.getElementById("refUserDisconneted");
        if (reftabla) {
            reftabla.innerHTML = textoTabla;

        }
    }, "ServicioUsuarioDes");
}