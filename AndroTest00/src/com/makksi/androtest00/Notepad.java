package com.makksi.androtest00;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.util.Log;

import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import java.io.Writer;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        	@Override
        	public void onClick(View v){
        		save("testo.txt");
        	}
        });
        Button loadButton = (Button) findViewById(R.id.loadButton);      
        loadButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		load("testo.txt");
        	}
        });   
        Log.i("Notepad", "Directory: " + getFilesDir().getAbsolutePath());
    }
    private void save (String filename){
    	EditText textArea = (EditText) findViewById(R.id.textArea);
    	String text = textArea.getText().toString();
    	Writer writer = null;
    	try {
    		writer = new OutputStreamWriter (openFileOutput(filename,MODE_PRIVATE));
    		writer.write(text);
    		Toast.makeText(this, "Testo salvato", Toast.LENGTH_SHORT).show();
    	}catch (IOException e) {
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
    		while ((len = reader.read(buf)) != -1){
    			aux.append(buf, 0,len);
    		}
    		text=aux.toString();
    		Toast.makeText(this, "Testo caricato", Toast.LENGTH_SHORT).show();
    	}catch (FileNotFoundException e) {
    		text ="";
    		Toast.makeText(this,"Testo non trovato",Toast.LENGTH_SHORT).show();
    	}catch (IOException e){
    		Log.e("Notepad", "Impossibile aprire il file", e);
    		text="";
    		Toast.makeText(this, "Errore", Toast.LENGTH_SHORT).show();
    	}finally {
    		if (reader != null){
    			try{
    				reader.close();
    			}catch (Throwable t) {
    			}
    		}
    	}
    	EditText textArea = (EditText) findViewById(R.id.textArea);
    	textArea.setText(text);
    }    
}
