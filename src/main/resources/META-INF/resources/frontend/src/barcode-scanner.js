import Quagga from 'quagga'; // ES6


function create(element,readers,locate = true,frequency = 10,debug=false) {
    Quagga.init({
        inputStream: {
            name: "Live",
            type: "LiveStream",
            target: document.querySelector('barcode-reader')
        },
        decoder: {
            readers: [readers]
        },
        locate: locate,
        frequency: frequency,
        debug: debug,
        drawBoundingBox: true,
        drawScanline: true
    }, function (err) {
        if (err) {
            console.log(err);
            return
        }
        console.log("Initialization finished. Ready to start");
        Quagga.start();
    });
    Quagga.onDetected((data => {
        if(debug)
            console.log(data)
        element.$server.calbackdata(JSON.stringify(data));
    }))
}


function stop(){
    Quagga.stop();
}

window.BarcodeReaderInitialize = create;
window.BarcodeReaderStop = stop;