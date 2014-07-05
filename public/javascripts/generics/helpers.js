const ALERTA_AMARILLO = 1;
const ALERTA_AZUL = 2;
const ALERTA_GRIS = 3
const ALERTA_ROJO = 0;
const ALERTA_VERDE = 4;

function toHHMMSS ( strsec ) {
    var sec_num = parseInt(strsec, 10); // don't forget the second param
    var hours   = Math.floor(sec_num / 3600);
    var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
    var seconds = sec_num - (hours * 3600) - (minutes * 60);

    if (hours   < 10) {hours   = "0"+hours;}
    if (minutes < 10) {minutes = "0"+minutes;}
    if (seconds < 10) {seconds = "0"+seconds;}
    var time    = hours+':'+minutes+':'+seconds;
    return time;
}

function checkTime(i) {
    if (i<10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function getHHMMSS( longTime ) {
    var date = new Date(longTime);
    var h=date.getHours();
    var m=date.getMinutes();
    var s=date.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    return h + ":" + m + ":" + s;
}

function getColorByAlerta(alerta) {
	switch(alerta) {
		case ALERTA_AMARILLO	: return "bg-warning";
		case ALERTA_AZUL		: return "bg-info";
		case ALERTA_GRIS		: return "bg-active";
		case ALERTA_ROJO		: return "bg-danger";
		case ALERTA_VERDE		: return "bg-success";
		default					: return "";
	}
/*
    switch(alerta) {
        case ALERTA_AMARILLO    : return "warning";
        case ALERTA_AZUL        : return "info";
        case ALERTA_GRIS        : return "active";
        case ALERTA_ROJO        : return "danger";
        case ALERTA_VERDE       : return "success";
        default                 : return "";
    }*/
}