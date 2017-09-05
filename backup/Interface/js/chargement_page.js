var date = new Date;
var charg_bef = date.getTime();
var myVar;

function Enter() {
    alert(ch);
}

function myFunction() {
    date = new Date;
    charg_aft = date.getTime();
    ch = "La page a été chargée en " + (charg_aft-charg_bef)/1000 + " seconde(s)";
    // alert(ch);
    charg = (charg_aft-charg_bef)/1000;
    if(charg <= 2){
        showPage();
    }
    myVar = setTimeout(showPage, charg);
}

function showPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("myDiv").style.display = "block";
}
