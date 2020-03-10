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
 * BarcodeScanner
 *
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

    public void setConsumer(Consumer<BarcodeScannerResult> consumer) {
        this.consumer = consumer;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setLocate(boolean locate) {
        this.locate = locate;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    public void setStopAfterScan(boolean stopAfterScan) {
        this.stopAfterScan = stopAfterScan;
    }

    public BarcodeScannerResult getLastResult() {
        return lastResult;
    }

    public void setReaders(@NotNull DecoderEnum... decodersEnums){
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

