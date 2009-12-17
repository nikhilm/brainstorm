package bencode;
public abstract class BType {
    public enum Type {
        INT,
        STRING,
        LIST,
        DICT
    }
    Type _type;

    public abstract Object value();
    public abstract String encode();

    public Type type() {
        return _type;
    }
}
