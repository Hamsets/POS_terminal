package pos.Connection;

import android.content.SharedPreferences;
import android.util.Log;

public class ConnectionServManager {
    private final static String TAG = "logsConnectionManager";
    private static final String PREF_URL_SERVER = "urlServer";
    private static final String PREF_PORT_SERVER = "portServer";
    private static final String PREF_POS_NAME = "posName";
    private String urlServer;
    private int portServer;
    private String posName;
    private final ProxyConnectionServ connection = null;

    public ConnectionServManager(SharedPreferences settings) {
        this.urlServer = settings.getString(PREF_URL_SERVER,"");
        Log.d(TAG, "Получен IP адрес: " + urlServer);

        this.portServer = settings.getInt(PREF_PORT_SERVER, 0);
        Log.d(TAG, "Получен номер порта: " + portServer);

        this.posName = settings.getString(PREF_POS_NAME, "");
        Log.d(TAG, "Получено название кассы: " + posName);
//        try {
//            Log.d(TAG, "Создаем ProxyConnection");
////            this.connection= new ProxyConnectionServ(urlServer,portServer);
//
//        } catch (IOException e){
//            Log.d(TAG, e.toString());
        }

//        try{
//            Log.d(TAG,"Creating socket...");
//            sock = new Socket(urlServer,portServer);
//
//            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
//            reader = new BufferedReader(streamReader);
//
//            OutputStreamWriter streamWriter = new OutputStreamWriter(sock.getOutputStream());
//            writer = new PrintWriter(streamWriter);
//
//        } catch (
//                IOException e) {
//            Log.d(TAG, e.toString()); }
//    }
}
