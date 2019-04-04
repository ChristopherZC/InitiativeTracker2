package edu.unc.live.initiativetracker2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

public class MainActivity extends AppCompatActivity
{
    String[] players = new String[35];
    int[] rolls = new int[35];
    String[] pList = new String[35];
    Button add, remove, clear;
    TextView olist;
    EditText rollInt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.addB);
        remove = (Button) findViewById(R.id.removeB);
        olist = (TextView) findViewById(R.id.oList);
        clear = (Button) findViewById(R.id.cButton);

        rollInt = (EditText) findViewById(R.id.input1);

        rollInt.setText("");

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String roll = rollInt.getText().toString();

                try
                {
                    String[] hold = roll.split(" ");
                    int r = Integer.parseInt(hold[1]);
                    rolls[r + 5] = r;
                    if (pList[r + 5] != null)
                    {
                        players[r + 5] = players[r + 5].concat(" , " + roll);
                    } else {
                        pList[r + 5] = hold[0];
                        players[r + 5] = roll;
                    }
                    olist.setText("Initiative Order");
                    olist.append("\n");
                    for (int i = rolls.length - 1; i >= 0; i--)
                    {
                        if (players[i] != null) {
                            olist.append(players[i]);
                            olist.append("\n");
                        }
                    }
                    rollInt.setText("");

                }
                catch (Exception e)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Error");
                    builder.setMessage("There is an issue with the input. Please enter the input as 'Player roll'");

                    builder.setNegativeButton("Close", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    builder.show();


                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String roll = rollInt.getText().toString();
                boolean contains = false;
                int z = 0;
                for (int i = 0; i < pList.length; i++)
                {
                    if (pList[i] != null)
                    {
                        if (pList[i].equalsIgnoreCase(roll))
                        {
                            contains = true;
                            z = i;
                        }
                    }
                }

                if (contains)
                {
                    players[z] = null;
                    olist.setText("Initiative Order");
                    olist.append("\n");
                    for (int i = rolls.length - 1; i >= 0; i--)
                    {
                        if (players[i] != null) {
                            olist.append(players[i]);
                            olist.append("\n");
                        }
                    }
                    rollInt.setText("");
                } else
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setCancelable(true);
                    builder1.setTitle("Error");
                    builder1.setMessage("This player has not rolled initiative!");

                    builder1.setNegativeButton("Close", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    builder1.show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                olist.setText("Initiative cleared");
            }
        });


    }
}
