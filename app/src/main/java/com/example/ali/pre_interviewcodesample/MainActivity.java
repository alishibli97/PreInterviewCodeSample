package com.example.ali.pre_interviewcodesample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView t;
    private Button shuffle,get_card,get_my_cards,get_rem_cards;
    int n;

    String s_deck[] = {"Heart Ace","Heart 2","Heart 3","Heart 4","Heart 5","Heart 6","Heart 7","Heart 8","Heart 9","Heart 10",
            "Heart Jack","Heart Queen","Heart King",
            "Spades Ace","Spades 2","Spades 3","Spades 4","Spades 5","Spades 6","Spades 7","Spades 8","Spades 9","Spades 10",
            "Spades Jack","Spades Queen","Spades King",
            "Clubs Ace","Clubs 2","Clubs 3","Clubs 4","Clubs 5","Clubs 6","Clubs 7","Clubs 8","Clubs 9","Clubs 10",
            "Clubs Jack","Clubs Queen","Clubs King",
            "Diamonds Ace","Diamonds 2","Diamonds 3","Diamonds 4","Diamonds 5","Diamonds 6","Diamonds 7","Diamonds 8",
            "Diamonds 9","Diamonds 10","Diamonds Jack","Diamonds Queen","Diamonds King"};

    List<String> deck_cards = new ArrayList<String>();
    List<String> user_cards = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.num);
        shuffle = (Button) findViewById(R.id.shuffle);
        get_card = (Button) findViewById(R.id.get_card);
        get_my_cards = (Button) findViewById(R.id.user_cards);
        get_rem_cards = (Button) findViewById(R.id.rem_cards);

        for (int i =0; i<52 ; i++){
            deck_cards.add(s_deck[i]);
        }

        n=52;
        t.setText(Integer.toString(n));

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
            }
        });

        get_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealOneCard();
            }
        });

        get_my_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r = getMyCards();

                AlertDialog.Builder a_dialog = new AlertDialog.Builder(MainActivity.this);
                a_dialog.setMessage(r).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setTitle("My cards").setIcon(R.drawable.cards).create().show();
            }
        });

        get_rem_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r = get_deckCard();

                AlertDialog.Builder a_dialog = new AlertDialog.Builder(MainActivity.this);
                a_dialog.setMessage(r).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setTitle("Remaining cards").setIcon(R.drawable.cards).create().show();
            }
        });

    }

    // SHUFFLES THE ENTIRE CARDS IN THE DECK; BOTH IN THE STRING AND THE LIST
    private void shuffle() {
        if (n>0) {
            for (int i = 0; i < deck_cards.size(); i++) {
                int j = new Random().nextInt(deck_cards.size());
                swap(0, j);
            }
        }
        else{
            Toast.makeText(MainActivity.this,"No more cards bro",Toast.LENGTH_SHORT).show();
        }
    }

    // RETURNS ONE CARD FROM THE DECK TO THE USER
    private void dealOneCard() {
        if(n>0){
            int j = new Random().nextInt(deck_cards.size());

            user_cards.add(deck_cards.get(j));
            Toast.makeText(MainActivity.this,deck_cards.get(j),Toast.LENGTH_SHORT).show();

            deck_cards.remove(j);

            n--;
            t.setText(Integer.toString(n));

        }
        else{
            Toast.makeText(MainActivity.this,"No more cards bro",Toast.LENGTH_SHORT).show();
        }
    }

    // SWAPS TWO DECK CARDS
    private void swap(int i, int j) {
        String temp = deck_cards.get(i);
        deck_cards.set(i,deck_cards.get(j));
        deck_cards.set(j,temp);
    }

    // RETURNS A STRING CONTAINING CONCATENATION OF ALL CARDS IN THE DECK
    private String get_deckCard() {
        String s = "";
        int i=0;
        while (i<deck_cards.size()){
            s = s + '\n' + deck_cards.get(i);
            i++;
        }
        return s;
    }

    // RETURNS A STRING CONTAINING CONCATENATION OF ALL CARDS THAT THE USER GOT
    private String getMyCards() {
        String s = "";
        int i = 0;
        if (user_cards.size()>0)
        {
            while (i<user_cards.size()){
                s = s + '\n' + user_cards.get(i);
                i++;
            }
        }
        return s;
    }
}
