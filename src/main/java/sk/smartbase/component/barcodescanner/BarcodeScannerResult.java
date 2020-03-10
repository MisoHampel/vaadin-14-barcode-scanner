package sk.smartbase.component.barcodescanner;

import java.util.List;

/**
 * BarcodeScannerResponse
 *
 * @author mhampel
 * @since 3/10/2020
 */
public class BarcodeScannerResult {

    private CodeResult codeResult;
    private List<Line> line;
    private Double angle;
    private List<Integer> pattern;

    public class CodeResult{
        private String code;
        private String format;
        private Double start;
        private Double end;
        private Double codeset;
        private Info startInfo;
        private Object decodedCodes;
        private Double direction;

        public class Info{
            private Double error;
            private Double code;
            private Double start;
            private Double end;
            private Correction correction;

            public class Correction{
                private Double bar;
                private Double space;

                public Double getBar() {
                    return bar;
                }

                public void setBar(Double bar) {
                    this.bar = bar;
                }

                @Override
                public String toString() {
                    return "Correction{" +
                            "bar=" + bar +
                            ", space=" + space +
                            '}';
                }
            }

            public Double getError() {
                return error;
            }

            public void setError(Double error) {
                this.error = error;
            }

            public Double getCode() {
                return code;
            }

            public void setCode(Double code) {
                this.code = code;
            }

            public Double getStart() {
                return start;
            }

            public void setStart(Double start) {
                this.start = start;
            }

            public Double getEnd() {
                return end;
            }

            public void setEnd(Double end) {
                this.end = end;
            }

            public Correction getCorrection() {
                return correction;
            }

            public void setCorrection(Correction correction) {
                this.correction = correction;
            }

            @Override
            public String toString() {
                return "Info{" +
                        "error=" + error +
                        ", code=" + code +
                        ", start=" + start +
                        ", end=" + end +
                        ", correction=" + correction +
                        '}';
            }
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Double getStart() {
            return start;
        }

        public void setStart(Double start) {
            this.start = start;
        }

        public Double getEnd() {
            return end;
        }

        public void setEnd(Double end) {
            this.end = end;
        }

        public Double getCodeset() {
            return codeset;
        }

        public void setCodeset(Double codeset) {
            this.codeset = codeset;
        }

        public Info getStartInfo() {
            return startInfo;
        }

        public void setStartInfo(Info startInfo) {
            this.startInfo = startInfo;
        }

        public Object getDecodedCodes() {
            return decodedCodes;
        }

        public void setDecodedCodes(Object decodedCodes) {
            this.decodedCodes = decodedCodes;
        }

        public Double getDirection() {
            return direction;
        }

        public void setDirection(Double direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "CodeResult{" +
                    "code='" + code + '\'' +
                    ", format='" + format + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    ", codeset=" + codeset +
                    ", startInfo=" + startInfo +
                    ", decodedCodes=" + decodedCodes +
                    ", direction=" + direction +
                    '}';
        }
    }
    public class Line{
        private Double x;
        private Double y;

        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public CodeResult getCodeResult() {
        return codeResult;
    }

    public void setCodeResult(CodeResult codeResult) {
        this.codeResult = codeResult;
    }

    public List<Line> getLine() {
        return line;
    }

    public void setLine(List<Line> line) {
        this.line = line;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public List<Integer> getPattern() {
        return pattern;
    }

    public void setPattern(List<Integer> pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "BarcodeScannerResult{" +
                "codeResult=" + codeResult +
                ", line=" + line +
                ", angle=" + angle +
                ", pattern=" + pattern +
                '}';
    }
}
