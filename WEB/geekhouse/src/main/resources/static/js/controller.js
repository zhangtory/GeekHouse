function light_ctl(cmd) {
    var url = "/light/"+cmd;
    $.get(url);
}

function ir_ctl(cmd) {
    var url = "/ir/"+cmd;
    $.get(url);
}