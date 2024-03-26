package pos.Connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxyConnectionServ implements ConnectionPosServ{
    private final static String TAG = "logsProxyConnectionServ";
    private Socket sock;
    private BufferedReader reader;
    private PrintWriter writer;

    public ProxyConnectionServ (String urlServer, int portServer){
        try{
        Log.d(TAG,"Creating socket...");
        sock = new Socket(urlServer,portServer);

        InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamReader);

        OutputStreamWriter streamWriter = new OutputStreamWriter(sock.getOutputStream());
        writer = new PrintWriter(streamWriter);

        } catch (IOException e) {
        Log.d(TAG, e.toString()); }

    }
    @Override
    public void sendCheck() {

    }

    @Override
    public Boolean checkAutontification() {
        return null;
    }
}
