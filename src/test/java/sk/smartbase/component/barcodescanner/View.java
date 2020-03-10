package sk.smartbase.component.barcodescanner;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

    public View() {
        BarcodeScanner barcodeScanner = new BarcodeScanner();
        Label lastScannedLabel = new Label("Last scanned value: unknown");
        barcodeScanner.setConsumer(event ->{
            System.out.println("data: " + event.toString());
            lastScannedLabel.setText("Last scanned value: "+event.getCodeResult().getCode());
        });
        add(barcodeScanner);
        add(lastScannedLabel);
        barcodeScanner.setReaders(DecoderEnum.CODE_39_READER);

        barcodeScanner.setStopAfterScan(false);
        add(new Button("stop",e->{
            barcodeScanner.stop();
        }));
        add(new Button("start",e->{
            barcodeScanner.initAndStart();
        }));
    }


}
