package com.example.spaceunicornbattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView output;
    public TextView outredfight;
    public TextView outbluefight;
    public Button blueplus;
    public Button redplus;
    public Button startAttack;
    private ArrayList<fighter> TeamRed = new ArrayList<fighter>();
    private ArrayList<fighter> TeamBlue = new ArrayList<fighter>();
    final String s = System.getProperty ("line.separator");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.my_output);
        outredfight = findViewById(R.id.outredfighter);
        outbluefight = findViewById(R.id.outbluefighter);
        redplus = findViewById(R.id.redfighter);
        blueplus = findViewById(R.id.bluefighter);
        startAttack = findViewById(R.id.startfighing);

        redplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TeamRed.add(new fighter(TeamRed.size()+1,"red"));
                outredfight.setText(String.valueOf(TeamRed.size()));
            }
        });
        blueplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TeamBlue.add(new fighter(TeamBlue.size()+1,"blue"));
                outbluefight.setText(String.valueOf(TeamBlue.size()));
            }
        });

        startAttack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int iatt;
                int ideff = 0;


                for (iatt = 0; iatt < TeamRed.size(); iatt++) {
                    if (TeamBlue.get(ideff).life > 0) {
                        // attacker found target
                        TeamBlue.get(ideff).getHitBy(TeamRed.get(iatt).dps);
                        output.append(s);
                        output.append(TeamRed.get(iatt).outHit());
                        output.append(TeamBlue.get(ideff).getStatus());
                    } else {
                        // target already dead
                        iatt--;
                        ideff++;
                        if (ideff >= TeamBlue.size()) {
                            // no target left
                            output.append(s);
                            break;
                        }
                    }
                }
                // all attackers attacked once -> finish
                output.append(s);
                output.append("all done one side");
                output.append(s);
                output.append(s);
                output.append("alternative attack method : red vs blue");
                attackOneSide(TeamRed, TeamBlue);
                output.append(s);
                output.append("alternative attack method : blue vs red");
                attackOneSide(TeamBlue, TeamRed);
            }

        });
    }

    public void attackOneSide (ArrayList<fighter> TeamAttacker, ArrayList<fighter> TeamDefender) {
        int iatt;
        int ideff = 0;


        for (iatt = 0; iatt < TeamAttacker.size(); iatt++) {
            if (TeamDefender.get(ideff).life > 0) {
                // attacker found target
                TeamDefender.get(ideff).getHitBy(TeamAttacker.get(iatt).dps);
                output.append(s);
                output.append(TeamAttacker.get(iatt).outHit());
                output.append(TeamDefender.get(ideff).getStatus());
            } else {
                // target already dead
                // search with same attacker again
                iatt--;
                // go to next defender
                ideff++;
                if (ideff >= TeamDefender.size()) {
                    // no target left
                    output.append(s);
                    break;
                }
            }
        }
        // all attackers have attacked once -> finish
        output.append(s);
        output.append("all done one side - out of the method");
    }
}