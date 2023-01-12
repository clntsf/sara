import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        String[] cmds = {
            "git add --all",
            "git commit -m 'timezone update write'",
            "echo aaa"
        };
        for ( String cmd: cmds ){
            Runtime.getRuntime().exec( cmd );
        }
    }
}
