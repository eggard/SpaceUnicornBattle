package com.example.spaceunicornbattle;

public class fighter {
    public int life = 10;
    public int dps = 2;
    public int id;
    public String team;


    public fighter (int i, String t){
        this.id = i;
        this.team = t;
    }


    public void getHitBy (int i) {
        this.life  = this.life - i;
    }

    public String getStatus () {
        String s = this.team + " " + String.valueOf(this.id) + " with " + String.valueOf(this.life) + " hp left ";
        return s;
    }
    public String outHit () {
        String s = this.team + " " + String.valueOf(this.id) + " hits with " + String.valueOf(this.dps) + " dps on > ";
        return s;
    }


}
