package sk.smartbase.component.barcodescanner;

import com.google.gson.Gson;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.NpmPackage;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <h2>BarcodeScanner - wrapper of QuaggaJS for Vaadin</h2>
 *
 * <b>What is QuaggaJS?</b>
 * <br><br>
 * QuaggaJS is a barcode-scanner entirely written in JavaScript supporting real- time localization and decoding of various types of barcodes such as EAN, CODE 128, CODE 39, EAN 8, UPC-A, UPC-C, I2of5, 2of5, CODE 93 and CODABAR. The library is also capable of using getUserMedia to get direct access to the user’s camera stream. Although the code relies on heavy image-processing even recent smartphones are capable of locating and decoding barcodes in real-time.
 *<br><br>
 * @author Michal Hampel
 * @since 10. 03. 2020
 */
@Tag("barcode-reader")
@JavaScript(value="./src/barcode-scanner.js")
@NpmPackage(value = "quagga", version = "0.12.1")
public class BarcodeScanner extends Component{

    private Consumer<BarcodeScannerResult> consumer;
    private boolean debug = false;
    private boolean locate = true;
    private boolean stopAfterScan = true;
    private int frequency = 10;
    private String readers = DecoderEnum.CODE_128_READER.getValue();
    private BarcodeScannerResult lastResult;

    public BarcodeScanner() {
        setId("barcode-reader");
    }

    public BarcodeScanner(Consumer<BarcodeScannerResult> consumer){
        this();
        this.consumer=consumer;
    }

    @ClientCallable
    public void calbackdata(String data){
        Gson gson = new Gson();
        lastResult = gson.fromJson(data, BarcodeScannerResult.class);
        if(consumer!=null)
            consumer.accept(lastResult);
        if(stopAfterScan)
            stop();
    }


    public void initAndStart(){
        getElement().executeJs("window.BarcodeReaderInitialize($0,$1,$2,$3,$4);",getElement(),readers,locate,frequency,debug);
    }
    public void stop(){
        getElement().executeJs("window.BarcodeReaderStop();");
    }

    public Consumer<BarcodeScannerResult> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<BarcodeScannerResult> consumer) {
        this.consumer = consumer;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLocate() {
        return locate;
    }

    /**
     * <b>locate</b>
     * One of the main features of QuaggaJS is its ability to locate a barcode in a given image. The locate property controls whether this feature is turned on (default) or off.<br>
     *<br>
     * Why would someone turn this feature off? Localizing a barcode is a computationally expensive operation and might not work properly on some devices. Another reason would be the lack of auto-focus producing blurry images which makes the localization feature very unstable.<br>
     *<br>
     * However, even if none of the above apply, there is one more case where it might be useful to disable locate: If the orientation, and/or the approximate position of the barcode is known, or if you want to guide the user through a rectangular outline. This can increase performance and robustness at the same time.<br>
     * @param locate
     */
    public void setLocate(boolean locate) {
        this.locate = locate;
    }

    public boolean isStopAfterScan() {
        return stopAfterScan;
    }

    /**
     * after succesfull scan, stop QuaggaJS
     * @param stopAfterScan
     */
    public void setStopAfterScan(boolean stopAfterScan) {
        this.stopAfterScan = stopAfterScan;
    }

    public int getFrequency() {
        return frequency;
    }

    /**
     * <b>frequency</b>
     * This top-level property controls the scan-frequency of the video-stream. It’s optional and defines the maximum number of scans per second. This renders useful for cases where the scan-session is long-running and resources such as CPU power are of concern.
     * @param frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getReaders() {
        return readers;
    }

    /**
     * Use this method, if you know what you do
     * For basic setup you should use setReadersEnum instead<br>
     * <b>decoder</b><br>
     * QuaggaJS usually runs in a two-stage manner (locate is set to true) where, after the barcode is located, the decoding process starts. Decoding is the process of converting the bars into its true meaning. Most of the configuration options within the decoder are for debugging/visualization purposes only.
     *
     * <br>
     * {<br>
     * &nbsp;&nbsp;  readers: [<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;    'code_128_reader'<br>
     * &nbsp;&nbsp;  ],<br>
     * &nbsp;&nbsp;  debug: {<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;      drawBoundingBox: false,<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;      showFrequency: false,<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;      drawScanline: false,<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;      showPattern: false<br>
     * &nbsp;&nbsp;  }<br>
     * &nbsp;&nbsp;  multiple: false<br>
     * }<br>
     * The most important property is readers which takes an array of types of barcodes which should be decoded during the session. Possible values are:
     *
     * <ul>
     *     <li>code_128_reader (default)</li>
     *     <li>ean_reader</li>
     *     <li>ean_8_reader</li>
     *     <li>code_39_reader</li>
     *     <li>code_39_vin_reader</li>
     *     <li>codabar_reader</li>
     *     <li>upc_reader</li>
     *     <li>upc_e_reader</li>
     *     <li>i2of5_reader</li>
     *     <li>2of5_reader</li>
     *     <li>code_93_reader</li>
     * </ul>
     *
     * Why are not all types activated by default? Simply because one should explicitly define the set of barcodes for their use-case. More decoders means more possible clashes, or false-positives. One should take care of the order the readers are given, since some might return a value even though it is not the correct type (EAN-13 vs. UPC-A).
     * @param readers
     */
    public void setReaders(String readers) {
        this.readers = readers;
    }

    public BarcodeScannerResult getLastResult() {
        return lastResult;
    }

    public void setLastResult(BarcodeScannerResult lastResult) {
        this.lastResult = lastResult;
    }

    public void setReadersEnums(@NotNull DecoderEnum... decodersEnums){
        if(decodersEnums ==null)
            throw new RuntimeException("Need to call with decoder argument");
        List<String> decoders = new ArrayList<>();
        for (DecoderEnum decodersEnum : decodersEnums) {
            decoders.add(decodersEnum.getValue());
        }
        String strings = decoders.stream().collect(Collectors.joining(","));
        readers = strings;
    }

}

