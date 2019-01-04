function light_ctl(cmd) {
    var url = "/light/"+cmd;
    console.log(url);
    $.get(url);
}