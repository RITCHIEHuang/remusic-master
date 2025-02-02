//package com.wm.remusic.net;
//
//import android.util.SparseArray;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.wm.remusic.MainApplication;
//import com.wm.remusic.json.MusicFileDownInfo;
//import com.wm.remusic.json.NeteaseMusicFileDownInfo;
//
//public class NeteaseMusicFileDownInfoGet implements Runnable {
//    String id;
//    int p;
//    SparseArray<NeteaseMusicFileDownInfo> arrayList;
//    int downloadBit;
//
//    public NeteaseMusicFileDownInfoGet(String id, int position, SparseArray<NeteaseMusicFileDownInfo> arrayList , int bit) {
//        this.id = id;
//        p = position;
//        this.arrayList = arrayList;
//        downloadBit = bit;
//    }
//
//    @Override
//    public void run() {
//        try {
//            JsonArray jsonArray = HttpUtil.getResposeJsonObject(BMA.Song.songInfo(id)).get("songurl")
//                    .getAsJsonObject().get("url").getAsJsonArray();
//            int len = jsonArray.size();
//
//            MusicFileDownInfo musicFileDownInfo = null;
//            for (int i = len - 1; i > -1; i--) {
//                int bit = Integer.parseInt(jsonArray.get(i).getAsJsonObject().get("file_bitrate").toString());
//                if (bit == downloadBit) {
//                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
//                } else if (bit < downloadBit && bit >= 64) {
//                    musicFileDownInfo = MainApplication.gsonInstance().fromJson(jsonArray.get(i), MusicFileDownInfo.class);
//                }
//            }
//
//            synchronized (this) {
//                if (musicFileDownInfo != null) {
//                    arrayList.put(p, musicFileDownInfo);
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//}