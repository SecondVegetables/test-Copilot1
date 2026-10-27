public enum MessageType {
    SEND(689), RECV(690);

    private short code;

    MessageType(int code) {
        this.code = (short) code;
    }

    public short getCode() {
        return code;
    }

    public static MessageType of(int code) {
        for (MessageType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
