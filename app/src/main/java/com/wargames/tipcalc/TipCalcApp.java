package com.wargames.tipcalc;

import android.app.Application;

/**
 * Created by Diego Campos on 3/06/2016.
 */
public class TipCalcApp extends Application {
    //modificar AndroidManifest para que funcione ( extends Application )


    private static String ABOUT_URL = "http://github.com/newoverflow";

    public String getAboutUrl()
    {
        return ABOUT_URL;
    }
}
