package com.wargames.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
     @Override
    public boolean onOptionsItemSelected(MenuItem item){
         //obtener el identificador del item seleccionado
         if ( item.getItemId() == R.id.action_about)
         {
             about();
         }
         return super.onOptionsItemSelected(item);
     }

    private void about()
    {

    }

}
