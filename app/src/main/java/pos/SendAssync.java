package pos;

public class SendAssync {

    //    public class SendClass extends AsyncTask<CheckDto, Void, Boolean> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            System.out.println("Begin connection...");
//        }
//
//        @Override
//        protected Boolean doInBackground(CheckDto... checkDtos) {
////            TimeUnit.SECONDS.sleep(1);
//            PrintWriter writer;
//            Boolean checkSentAccepted = false;
//            try {
//                Socket sock = new Socket(HOST_SERVER, PORT_SERVER);
//                if (sock.isConnected()){
//                    System.out.println("Connected...");
//
//                    writer = new PrintWriter(sock.getOutputStream());
//
////          цикл для всех чеков передаваемых в асинхронный поток
////            format of packet:
////            check_id(0 - for new sent to server check)#name_of_cafe#goods1_id\quantity_of_goods1#...
////              ...(all other goods)#time#deleted(false for new check)
//                    for (CheckDto checkDto : checkDtos){
//                        String strFullCheck = checkDto.getId().toString() + "#" + checkDto.getPos() + "#" +
//                                checkDto.getCashierId().toString() + "#";
//                        for (int x = 0; x < checkDto.getGoodsDtoList().size(); x++) {
//                            strFullCheck = strFullCheck + checkDto.getGoodsDtoList().get(x).getGoodsType() + "\\"
//                                    + checkDto.getGoodsDtoList().get(x).getQuantityGoods();
//                            if (x != checkDto.getGoodsDtoList().size()-1){
//                                strFullCheck = strFullCheck + "|";
//                            }
//                        }
//                        strFullCheck = strFullCheck + "#" + checkDto.getSum() + "#" +
//                                checkDto.getDateStamp() + "#" +
//                                checkDto.getDeleted().toString();
//                        writer.println(strFullCheck);
//                        writer.flush();
//                    }
//                }
//            } catch (Exception e) {e.printStackTrace();}
////            writer.close();
//            return checkSentAccepted;
//        }
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            System.out.println("End connection ");
//        }
//    }



}
