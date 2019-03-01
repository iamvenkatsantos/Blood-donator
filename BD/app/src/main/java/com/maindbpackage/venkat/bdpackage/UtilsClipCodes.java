package com.maindbpackage.venkat.bdpackage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by venkat on 7/29/2018.
 */

public class UtilsClipCodes {
    final static String FileName="MyFileName";

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue){
        SharedPreferences spref=ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        return  spref.getString(settingName,defaultValue);
    }
    public static void saveSharedSetting(Context ctx,String settingName,String settingValue){
        SharedPreferences spref=ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=spref.edit();
        edit.putString(settingName,settingValue);
        edit.apply();
    }
    public static void SharedPrefesSAVE(Context ctx,String Name){
        SharedPreferences prefs=ctx.getSharedPreferences("NAME",0);
        SharedPreferences.Editor prefEDIT=prefs.edit();
        prefEDIT.putString("NAME",Name);
        prefEDIT.commit();
    }

}
