package com;

// import static com.MainActivity.hostServer;
//import static com.MainActivity.portServer;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

class SendClass extends AsyncTask<Check, Void, Void> {



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Begin connection...");
    }

    @Override
    protected Void doInBackground(Check... checks) {
        try {
//            TimeUnit.SECONDS.sleep(1);

            System.out.println("making socket ...");

            Socket sock = new Socket(MainActivity.hostServer, MainActivity.portServer);
            if (sock.isConnected()){

                System.out.println("Connected...");

            }
            System.out.println("try to get low-level socket ...");

            OutputStreamWriter streamWriter = new OutputStreamWriter(sock.getOutputStream());

            System.out.println("popytka sozdaniya tsepnogo potoka ...");

            PrintWriter writer = new PrintWriter(streamWriter);

            System.out.println("try to send to server ...");

//          цикл для всех чеков передаваемых в асинхронный поток

            for (Check check: checks){
                for (int x = 0; x < check.getCheck().size(); x++) {
                    writer.println(check.getCheck().get(x).getTypeGoods() + "\\" + check.getCheck().get(x).getQuantityGoods() + "\\" + check.getCheck().get(x).getSumm());
                }

                writer.close();
            }


            System.out.println("закрытие цепного потока отправки");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        System.out.println("End connection ");
    }
}