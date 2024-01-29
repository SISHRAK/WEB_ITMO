
let expandButton = document.getElementById("expandButton");
expandButton.addEventListener("click", function () {
        let block = document.getElementById("expanded");
        let arrow = document.getElementById("arrow");
        let splitPath = arrow.src.split("/");
        if (block.style.maxHeight){
            block.style.maxHeight = null;
            splitPath[splitPath.length - 1] = "arrow-down2.svg"
            arrow.src = splitPath.join("/");
        } else {
            splitPath[splitPath.length - 1] = "arrow-up2.svg"
            arrow.src = splitPath.join("/");
            block.style.maxHeight = block.scrollHeight + "px";
        }
    }
);