package pos.Connection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SendClass extends AsyncTask <ConnectionSettingsObj,Void, String>{

    //    @Getter(AccessLevel.NONE)
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private static final String TAG="logsSendClass";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "Начало работы AsyncTask - отправка информации на сервер");

    }
    @Override
    protected String doInBackground(ConnectionSettingsObj ... connectionSettingsObjs){
        String strMessage;
        String strServAswer="";
        String urlServer;
        int portServer;

        for (ConnectionSettingsObj connSettingsObj: connectionSettingsObjs){
            urlServer=connSettingsObj.getUrlServer();
            portServer=connSettingsObj.getPortServer();
            strMessage=connSettingsObj.getStrMessage();
            try {
                socket = new Socket(urlServer, portServer);
                Log.d(TAG, "Подключение к серверу по адресу "
                        + urlServer + " и порту " + portServer);
                if (socket.isConnected()){

                    try {
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        out.write(strMessage + "\n"); // отправляем на сервер
                        Log.d (TAG, "Отправляемые данные: " + strMessage);
                        out.flush(); // чистим
                        strServAswer = in.readLine(); //получаем ответ от сервера
//                        SendClass.this.downService();

                    } catch (IOException e) {
                        // Сокет должен быть закрыт при любой
                        // ошибке, кроме ошибки конструктора сокета:
                        SendClass.this.downService();
                        Log.d(TAG, "Соединение в классе SendClass сброшено.");
                    }

                }

            } catch (IOException e) {
                Log.d(TAG, "Ошибка. Socket не создан.");
            }
        }
        return strServAswer;

    }

    @Override
    protected void onPostExecute (String strServAnswer){
        super.onPostExecute(strServAnswer);
        Log.d(TAG,"Получен ответ от сервера: " + strServAnswer);
        SendClass.this.downService();
    }

    /**
     * закрытие сокета
     */
    public void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }
}
