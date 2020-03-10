package sk.smartbase.component.barcodescanner;

/**
 * DecoderEnum
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
 * @author Michal Hampel
 * @since 10. 03. 2020
 */
public enum DecoderEnum {
    CODE_128_READER("code_128_reader"),
    EAN_READER("ean_reader"),
    EAN_8_READER("ean_8_reader"),
    CODE_39_READER("code_39_reader"),
    CODE_39_VIN_READER("code_39_vin_reader"),
    CODABAR_READER("codabar_reader"),
    UPC_READER("upc_reader"),
    UPC_E_READER("upc_e_reader"),
    I2OF5_READER("i2of5_reader"),
    TWO_OF_FIVE_READER("2of5_reader"),
    CODE_93_READER("code_93_reader");

    private String value;

    DecoderEnum(String value) {
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
