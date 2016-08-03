package com.lazztech.thoughtlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gianlazzarini on 7/31/16.
 */
public class PHQ9Activity extends AppCompatActivity implements OnSeekBarChangeListener
{

    SeekBar seekBar1;
    int seekBar1value;        //The SeekBar value output
    SeekBar seekBar2;
    int seekBar2value;        //The SeekBar value output
    SeekBar seekBar3;
    int seekBar3value;        //The SeekBar value output
    SeekBar seekBar4;
    int seekBar4value;        //The SeekBar value output
    SeekBar seekBar5;
    int seekBar5value;        //The SeekBar value output
    SeekBar seekBar6;
    int seekBar6value;        //The SeekBar value output
    SeekBar seekBar7;
    int seekBar7value;        //The SeekBar value output
    SeekBar seekBar8;
    int seekBar8value;        //The SeekBar value output
    SeekBar seekBar9;
    int seekBar9value;        //The SeekBar value output

    int totalScoreInt;

    String Date = "Date: ";
    
    TextView result;
    Button SaveScore;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.phq9);
        // bind GUI elements with local controls
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar7);
        seekBar8 = (SeekBar)findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar)findViewById(R.id.seekBar9);

        //set change listener
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
        seekBar5.setOnSeekBarChangeListener(this);
        seekBar6.setOnSeekBarChangeListener(this);
        seekBar7.setOnSeekBarChangeListener(this);
        seekBar8.setOnSeekBarChangeListener(this);
        seekBar9.setOnSeekBarChangeListener(this);

        totalScoreInt =
                        + seekBar1value
                        + seekBar2value
                        + seekBar3value
                        + seekBar4value
                        + seekBar5value
                        + seekBar6value
                        + seekBar7value
                        + seekBar8value
                        + seekBar9value;

        result = (TextView)findViewById(R.id.totalScore);
        SaveScore = (Button)findViewById(R.id.SaveDepressionScore);

        SaveScore.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                //Put up the Yes/No message box
                AlertDialog.Builder builder = new AlertDialog.Builder(PHQ9Activity.this);
                builder
                        .setTitle("Are you sure you're finished?")
                        .setMessage("Touch \"YES\" to save.")
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // write on SD card file data in the text box
                                try {
                                    File directory = Environment.getExternalStorageDirectory();
                                    File myFile = new File(directory, "mythoughtlog.txt");

                                    // Check if the file already exists so you don't keep creating
                                    if (!myFile.exists()) {
                                        //Log.i(TAG, "Creating the file as it doesn't exist already");
                                        myFile.createNewFile();
                                    }

                                    // Open the FileoutputStream
                                    FileOutputStream fOut = new FileOutputStream(myFile, true);

                                    // Open the printStream to allow for Strings to be written
                                    PrintStream printStream = new PrintStream(fOut);

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mma");
                                    String currentDateandTime = sdf.format(new Date());
                                    Date += " " + currentDateandTime;


                                    // Using a stringBuffer to append all the values to
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append(Date);
                                    stringBuffer.append('\n');
                                    stringBuffer.append(String.valueOf("Score: " +totalScoreInt));

                                    // Print the stringBuffer to the file
                                    printStream.print(stringBuffer.toString());

                                    // Close everything out
                                    printStream.close();
                                    fOut.close();
                                    Toast.makeText(getBaseContext(),
                                            "Saved to 'mythoughtlog.txt'",
                                            Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getBaseContext(), e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", null)                        //Do nothing on no
                        .show();

            }//Save onClick
        });// btnWriteSDFile
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBar1value = progress;
        seekBar2value = progress;
        seekBar3value = progress;
        seekBar4value = progress;
        seekBar5value = progress;
        seekBar6value = progress;
        seekBar7value = progress;
        seekBar8value = progress;
        seekBar9value = progress;

        result.setText(String.valueOf("Score: " +totalScoreInt));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
}
