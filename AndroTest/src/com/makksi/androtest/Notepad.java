package com.makksi.androtest;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// classe di tipo attivitá
public class Notepad extends Activity {
	
    @Override
// ridefinisco il metodo onCreate    
    public void onCreate(Bundle savedInstanceState) {
// eredito anzitutto tutti i metodi e proprietá della classe padre    	
        super.onCreate(savedInstanceState);
// uso tale metodo per impostare il layout dell'attivitá        
        setContentView(R.layout.main);
// genero un oggetto di tipo Button con le proprietá definite nella classe autogenerata R        
        Button saveButton = (Button) findViewById(R.id.saveButton);
// contemporaneamente creo un nuovo oggetto della classe OnClickListener e lo assegno come argomento
// del metodo setOnClickListener        
        saveButton.setOnClickListener(new View.OnClickListener(){
        	public void OnClick(View v){
        		save("testo.txt");
        	}
        });
        Button loadButton = (Button) findViewById(R.id.loadButton);      
        loadButton.setOnClickListener(new View.OnClickListener(){
        	public void OnClick(View v){
        		load("testo.txt");
        	}
        });   
        Log.i("Notepad", "Directory: " + getFilesDir().getAbsolutePath());
    }
    private void save (String filename){
    	EditText textarea = (EditText) findViewById(R.id.textarea);
    	String text = textarea.getText().toString();
    	Writer writer = null;
    	try {
    		writer = new OutputStreamWriter (openFileOutput(filename,MODE_PRIVATE));
    		writer = write (text);
    		Toast.makeText(this, "Testo salvato", Toast.LENGTH_SHORT).show();
    	}catch (TOException e) {
    			Log.e("Notepad", "Impossibile salvare il file", e);
    			Toast.makeText(this,"Errore",Toast.LENGTH_SHORT).show();
    	}finally {
    		if (writer != null){
    			try{
    				writer.close();
    			}catch (Throwable t) {
    			}
    			
    		}
    	}
    }
    
    private void load (String filename){
    	String text;
    	Reader reader = null;
    	try {
    		reader = new InputStreamReader (openFileInput(filename));
    		StringBuffer aux = new StringBuffer();
    		char[] buf = new char[1024];
    		int len;
    		while ((len = reader))
    		
    		Toast.makeText(this, "Testo salvato", Toast.LENGTH_SHORT).show();
    	}catch (TOException e) {
    			Log.e("Notepad", "Impossibile salvare il file", e);
    			Toast.makeText(this,"Errore",Toast.LENGTH_SHORT).show();
    	}finally {
    		if (writer != null){
    			try{
    				writer.close();
    			}catch (Throwable t) {
    			}
    			
    		}
    	}
    }    
    	
    }
    private void load (){
    	
    }

}
