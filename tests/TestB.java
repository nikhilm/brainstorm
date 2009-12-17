public class TestB {
    private void testEncode() {
        assert (new BInt(5)).encode() == "i5e";
        assert (new BInt(-456)).encode() == "i-456e";
        
        assert (new BString("hello:world")).encode() == "11:hello:world";
        assert (new BString("")).encode() == "";

        BList l = new BList();
        l.add( new BInt(42) );
        l.add( new BString("Shrdlu") );
        l.add( new BInt(-9) );

        assert l.encode() == "li42e6:Shrdlui-9ee";


        BDict d = new BDict();
        d.add( new BString("key"), new BString("value") );
        d.add( new BString("key2"), l );
        assert d.encode() == "d3:key5:value4:key2li42e6:Shrdlui-9eee";
    }

    private void testDecode() {
        String dict = "d3:key5:value4:key2li42e6:Shrdlui-9eee";
        BDict d = (BDict)( new BDecoder(dict) ).parse();
        assert d.encode() == dict;

        assert d.contains( "key" );
        assert d.get( new BString("key") ).value() == "value";

        BList l = (BList)d.get( "key2" );
        assert (Integer)(l.value().get(2).value()) == -9;
    }

    private void run() {
        testEncode();
        testDecode();
    }
    public static void main(String[] args) {
        (new TestB()).run();
    }
}
