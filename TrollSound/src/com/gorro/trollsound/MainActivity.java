package com.gorro.trollsound;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btn;
	SoundPool sp;
	int flujo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button1);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "lolas", Toast.LENGTH_SHORT).show();
				flujo = sp.load(getApplicationContext(), R.raw.wrong, 1);
				sp.play(flujo, 1, 1, 1, 0, 1);
			}
		});
        
    }

}
