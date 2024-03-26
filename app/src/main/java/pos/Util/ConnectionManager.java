package pos.Util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import pos.Dto.UsersDto;

public class ConnectionManager {
    private static final String TAG = "logsConnectionPosServ";
    private String urlServer;
    private int portServer;
    private String posName;
    private Socket sock;
    private BufferedReader reader;
    private PrintWriter writer;
    private UsersDto usersDto;
    public ConnectionManager(String urlServer, int portServer) {
        setUpNetworking(urlServer, portServer);
    }

    public void setUpNetworking(String urlServer, int portServer){

        try{
            Log.d(TAG,"Создание сокета...");
            sock = new Socket(urlServer,portServer);

            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);

            OutputStreamWriter streamWriter = new OutputStreamWriter(sock.getOutputStream());
            writer = new PrintWriter(streamWriter);

        } catch (IOException e) {
            Log.d(TAG, e.toString()); }
    }


    public void turnOffNetworking (){
        reader = null;
        writer = null;
        sock = null;
    }


}
