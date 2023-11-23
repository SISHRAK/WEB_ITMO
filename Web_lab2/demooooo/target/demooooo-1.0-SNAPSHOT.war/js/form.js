var patternFloatY = /^[+-]?[0-4](\.[0-9]+)|^5(\.0+)?$/;
var patternIntY = /^([+-]?[0-5])$/;
var patternFloatR = /^[1-3](\.[0-9]+)|^4(\.0+)?$/;
var patternIntR = /^[1-4]$/;

var outOfSpace = true;
document.getElementById('sendForm').addEventListener('submit', function (e) {
    e.preventDefault();
    check();
});

document.getElementById('svg').addEventListener('click', function (e) {

    let svg = document.querySelector('svg');
    let pt = svg.createSVGPoint();
    let r = 3;
    pt.x = e.clientX;
    pt.y = e.clientY;
    let svgP = pt.matrixTransform(svg.getScreenCTM().inverse());

    sendSVG(svgP.x, svgP.y, r);
    if (!outOfSpace) {
        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", svgP.x);
        circle.setAttribute("cy", svgP.y);
        circle.setAttribute("r", "3");
        circle.setAttribute("fill", "black");
        svg.appendChild(circle);
    }
});

function sendSVG(x, y, r) {
    let radius = 3;
    let svgX = Math.round((x - 150) / 120 * radius).toFixed(0);
    let svgY = ((150 - y) / 120 * radius).toFixed(5);
    outOfSpace = false;
    send(svgX, svgY, radius);
}

function check(){
    var r = document.getElementById('r_select').value;
    var y = document.getElementById('y_select').value;
    var radio = document.querySelectorAll('input[type="radio"]:checked')[0];
    var x = radio.value;
    //if (validateY(y) && validateR(r)) {
    send(x, y, r);
    //}

}

async function send(x, y, r, clicked) {
    let serverControllerUrl = new URL(document.URL + "/result");
    serverControllerUrl.searchParams.set("x", x);
    serverControllerUrl.searchParams.set("y", y);
    serverControllerUrl.searchParams.set("r", r);
    serverControllerUrl.searchParams.set("clicked", clicked);

    let response = await fetch(serverControllerUrl, {
        method: 'GET'
    });

    if (response.redirected) {
        window.location.href = response.url;
    }
}
