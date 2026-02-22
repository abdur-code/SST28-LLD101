public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errorMessage;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = true;
        this.errorMessage = null;
    }

    private ExportResult(String contentType, byte[] bytes, boolean success, String errorMessage) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static ExportResult failure(String message) {
        return new ExportResult(null, new byte[0], false, message);
    }
}
