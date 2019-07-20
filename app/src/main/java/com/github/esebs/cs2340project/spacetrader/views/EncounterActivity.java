package com.github.esebs.cs2340project.spacetrader.views;
import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.AlbinoSquirrel;
import com.github.esebs.cs2340project.spacetrader.entities.BobWaters;
import com.github.esebs.cs2340project.spacetrader.entities.Encounterable;
import com.github.esebs.cs2340project.spacetrader.entities.Pirate;
import com.github.esebs.cs2340project.spacetrader.entities.Player;
import com.github.esebs.cs2340project.spacetrader.entities.Police;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.entities.Trader;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.RandomEventsViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Queue;


public class EncounterActivity extends AppCompatActivity {
    private Model model = Model.getModelInstance();
    private Player player = model.getPlayer();
    private RandomEventsViewModel randomEventsViewModel;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView encounterText;
    private TextView playerVehicle;
    private TextView playerVehicleHealth;
    private TextView opponentVehicle;
    private TextView opponentVehicleHealth;
    private TextView encounterMessage;
    private Encounterable e;
    private View dialog;
    private SeekBar seekBar;
    private int traderQuantity = 0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;


    @Override
    /**
     * This method is called when the EncounterActivity is called
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);
        randomEventsViewModel = new RandomEventsViewModel();
        randomEventsViewModel.createNewEncountersQueue();
        Queue<Encounterable> queue = randomEventsViewModel.getEncountersQueue();

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        playerVehicle = findViewById(R.id.player_vehicle);
        playerVehicleHealth = findViewById(R.id.player_vehicle_health);
        opponentVehicle = findViewById(R.id.opponent_vehicle);
        opponentVehicleHealth = findViewById(R.id.opponent_vehicle_health);
        encounterMessage = findViewById(R.id.encounter_message);
        encounterText = findViewById(R.id.encounter);

        executeEvent();
    }

    /**
     * This method will execute the next event in the encountersQueue
     * in RandomEventsViewModel
     */
    public void executeEvent() {
        e = randomEventsViewModel.pollEncountersQueue();

        if (e == null) {
            Intent intent = new Intent(EncounterActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            playerVehicle.setText("Vehicle: " + player.getVehicle().getVehicleType());
            playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
            opponentVehicle.setText("Vehicle: " + e.getVehicle().getVehicleType());
            opponentVehicleHealth.setText("Health: " + e.getVehicle().getCurrentHealth());

            if (e instanceof Trader) {
                handleTrader((Trader) e);
            } else if (e instanceof Police) {
                handlePolice((Police) e);
            } else if (e instanceof Pirate) {
                handlePirate((Pirate) e);
            } else if (e instanceof AlbinoSquirrel) {
                handleAlbinoSquirrel((AlbinoSquirrel) e);
            } else if (e instanceof BobWaters) {
                handleBobWaters((BobWaters) e);
            }
        }
    }

    /**
     * This method will handle all possible events when encountering a
     * trader. The user can Attack, Ignore, or Trade with a trade.
     * This method also handles the button presses associated with
     * each action a user can take aganist a trader.
     *
     * @param trader the Trader that is in the encounter queue
     */
    public void handleTrader(Trader trader) {
        int maxBuyQuantity = trader.calculateMaxBuyQuantity();
        int sellPrice = trader.getPrice();
        String resourceName = trader.getResource().name().toLowerCase();
        traderQuantity = 0;
        encounterText.setText("Trader Encounter");
        encounterMessage.setText("While traveling to your destination, you encounter a trader. \n\n" +
                "The trader has " +maxBuyQuantity+" unit(s) of " +resourceName+ ".\n\n" +
                "They are offering "+ sellPrice +" cr. per unit. Would you like to trade?");

        button1.setText("Attack");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean playerAttacks = player.attack(trader);
                boolean opponentAttacks = trader.attack();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                alertDialog.setTitle("Attack!");
                if (playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the trader!\n" +
                            "The trader attacks and hits you!");

                } else if (!playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the trader!\n" +
                            "The trader attacks and hits you!");

                } else if (playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the trader!\n" +
                            "The trader attacks and misses you!");

                } else if (!playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the trader!\n" +
                            "The trader attacks and misses you!");
                }

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
                        opponentVehicleHealth.setText("Health: " + trader.getVehicle().getCurrentHealth());
                        checkTraderDefeat(trader);
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button2.setText("Ignore");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeEvent();
            }
        });

        button3.setText("Trade");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                dialog = getLayoutInflater().inflate(R.layout.trade_dialog, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);
                textView1 = dialog.findViewById(R.id.text_view_1);
                textView2 = dialog.findViewById(R.id.text_view_2);
                textView3 = dialog.findViewById(R.id.quantity);
                seekBar = dialog.findViewById(R.id.seek_bar);
                traderQuantity = 0;

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1.setText(getString(R.string.buy_text_1, maxBuyQuantity, resourceName));
                textView2.setText(getString(R.string.buy_text_2));
                textView3.setText(getString(R.string.buy_text_3, traderQuantity));

                seekBar.setMax(maxBuyQuantity);
                seekBar.setProgress(0);
                seekBar.setOnSeekBarChangeListener(traderSeekBarListener);

                AlertDialog.Builder alert = alertDialog.setView(dialog);
                alert.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        player.trade(trader, traderQuantity);
                        executeEvent();
                    }

                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.create();
                alert.show();
            }
        });

        button4.setVisibility(View.GONE);
    }


    /**
     * This method will handle all possible events when encountering a
     * pirate. The user can Attack, Flee, or Surrender to a pirate.
     * This method also handles the button presses associated with
     * each action a user can take against a pirate.
     *
     * @param pirate the Pirate that is in the encounter queue
     */
    public void handlePirate(Pirate pirate) {
        encounterText.setText("Pirate Encounter");
        encounterMessage.setText("While traveling to your destination, you encounter a pirate. \n\n" +
                "The pirate is getting ready to attack you... \n\n" +
                "What are you going to do?");

        button1.setText("Attack");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean playerAttacks = player.attack(pirate);
                boolean opponentAttacks = pirate.attack();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                alertDialog.setTitle("Attack!");
                if (playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the pirate!\n" +
                            "The pirate attacks and hits you!");

                } else if (!playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the pirate!\n" +
                            "The pirate attacks and hits you!");

                } else if (playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the pirate!\n" +
                            "The pirate attacks and misses you!");

                } else if (!playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the pirate!\n" +
                            "The pirate attacks and misses you!");
                }

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
                        opponentVehicleHealth.setText("Health: " + pirate.getVehicle().getCurrentHealth());
                        checkPirateDefeat(pirate);
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button2.setText("Flee");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean playerFlees = player.flee(pirate);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                if (!playerFlees) {
                    boolean opponentAttacks = pirate.attack();
                    alertDialog.setTitle("Can't Escape!");
                    if (opponentAttacks) {
                        alertDialog.setMessage("The pirate attacks and hits you!");

                    } else {
                        alertDialog.setMessage("The pirate attacks but misses you...");
                    }
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
                            opponentVehicleHealth.setText("Health: " + pirate.getVehicle().getCurrentHealth());
                            checkPirateDefeat(pirate);
                        }
                    });

                } else {
                    alertDialog.setTitle("Escaped Successfully!");
                    alertDialog.setMessage("You have escaped from the pirate");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            executeEvent();
                        }
                    });
                }

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button3.setText("Surrender");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCredits = player.getCredits();
                Resource takenResource = pirate.surrender();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                alertDialog.setTitle("Surrender...");
                if (takenResource == null) {
                    alertDialog.setMessage("The pirate doesn't find anything in your cargo hold.\n" +
                            "They decide to take half of your credits instead.\n" +
                            "They take " + currentCredits/2 + " cr. and let you go");
                } else {
                    String resourceName = takenResource.name().toLowerCase();
                    alertDialog.setMessage("The pirate searches your cargo hold.\n" +
                            "They find some " + resourceName + " in your cargo hold.\n" +
                            "They decide to take it all and let you go");
                }

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        executeEvent();
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button4.setVisibility(View.GONE);

    }

    /**
     * This method will handle all possible events when encountering a
     * police. The user can Attack, Flee, or Submit or Bribe a police.
     * This method also handles the button presses associated with
     * each action a user can take against a police.
     *
     * @param police the Police that is in the encounter queue
     */
    public void handlePolice(Police police) {
        encounterText.setText("Police Encounter");
        encounterMessage.setText("While traveling to your destination, you encounter a Police. \n\n" +
                "The police summon you to submit to an inspection. \n\n" +
                "What would you like to do?");
        button1.setText("Attack");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean playerAttacks = player.attack(police);
                boolean opponentAttacks = police.attack();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                alertDialog.setTitle("Attack!");
                if (playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the police!\n" +
                            "The police attacks and hits you!");

                } else if (!playerAttacks && opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the police!\n" +
                            "The police attacks and hits you!");

                } else if (playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and hit the police!\n" +
                            "The police attacks and misses you!");

                } else if (!playerAttacks && !opponentAttacks) {
                    alertDialog.setMessage("You attack and miss the police!\n" +
                            "The police attacks and misses you!");
                }

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
                        opponentVehicleHealth.setText("Health: " + police.getVehicle().getCurrentHealth());
                        checkPoliceDefeat(police);
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button2.setText("Flee");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean playerFlees = player.flee(police);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                if (!playerFlees) {
                    boolean opponentAttacks = police.attack();
                    alertDialog.setTitle("Can't Escape!");
                    if (opponentAttacks) {
                        alertDialog.setMessage("The police attacks and hits you!");

                    } else {
                        alertDialog.setMessage("The police attacks but misses you...");
                    }
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            playerVehicleHealth.setText("Health: " + player.getVehicle().getCurrentHealth());
                            opponentVehicleHealth.setText("Health: " + police.getVehicle().getCurrentHealth());
                            checkPoliceDefeat(police);
                        }
                    });

                } else {
                    alertDialog.setTitle("Escaped Successfully!");
                    alertDialog.setMessage("You have escaped from the police");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            executeEvent();
                        }
                    });
                }

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();

            }
        });

        button3.setText("Submit");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCredits = player.getCredits();
                boolean illegalGoodsFound = police.searchCargoHold();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                alertDialog.setTitle("Police Search");
                if (illegalGoodsFound) {
                    alertDialog.setMessage("The police search your cargo hold.\n" +
                            "They find that you are carrying illegal goods with you.\n" +
                            "They confiscate the items and fine you " + currentCredits/4 + " cr.");
                } else {
                    alertDialog.setMessage("The police search your cargo hold.\n" +
                            "They find nothing.\n" +
                            "They apologize for the inconvenience and let you go.");
                }

                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        executeEvent();
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        button4.setVisibility(View.VISIBLE);
        button4.setText("Bribe");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bribeAmount = police.getBribeAmount();
                boolean acceptBribe = police.takesBribe();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);

                if (acceptBribe) {
                    alertDialog.setTitle("Bribe Accepted!");
                    alertDialog.setMessage("You offer the police a bribe.\n" +
                            "The police accept your bribe for " + bribeAmount + " cr.\n" +
                            "The police then let you go");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            executeEvent();
                        }
                    });
                } else {
                    alertDialog.setTitle("Bribe Declined...");
                    alertDialog.setMessage("You offer the police a bribe.\n" +
                            "The police do not accept your bribe and insist on \n" +
                            "searching your vehicle.");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                }

                alertDialog.setCancelable(false);
                alertDialog.create().setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

    }

    /**
     * If the AlbinoSquirrel is encountered, this will display an alert.
     *
     * @param albinoSquirrel the object AlbinoSquirrel in the encounter queue
     */
    public void handleAlbinoSquirrel(AlbinoSquirrel albinoSquirrel) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);
        boolean isKilled = albinoSquirrel.doesKill();

        if (isKilled) {
            alertDialog.setMessage("You have stumbled upon the Albino Squirrel.\n" +
                    "It decides to kill you...");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(EncounterActivity.this, DiedActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            alertDialog.setMessage("You have stumbled upon the Albino Squirrel.\n" +
                    "It decides to spare you...");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    executeEvent();
                }

            });
        }

        alertDialog.setCancelable(false);
        alertDialog.create().setCanceledOnTouchOutside(false);
        AlertDialog show = alertDialog.show();

        WindowManager.LayoutParams lp =  show.getWindow().getAttributes();
        lp.dimAmount = 1f;
        show.getWindow().setAttributes(lp);
    }

    /**
     * If Bob Water is encountered, this will display an alert.
     *
     * @param bob the object Bob in the encounter queue
     */
    public void handleBobWaters(BobWaters bob) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EncounterActivity.this);
        boolean givesMoney = bob.givesPlayerMoney();

        if (givesMoney) {
            alertDialog.setMessage("You have stumbled upon Bob Water.\n" +
                    "He decides to give you 10,000 cr!");
        } else {
            alertDialog.setMessage("You have stumbled upon Bob Water.\n" +
                    "He ignores you...");
        }

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                executeEvent();
            }
        });

        alertDialog.create();
        AlertDialog show = alertDialog.show();

        WindowManager.LayoutParams lp =  show.getWindow().getAttributes();
        lp.dimAmount = 1f;
        show.getWindow().setAttributes(lp);

    }

    /**
     * When the trader and the player are in a fight,
     * this method is called after every move to check
     * if one of them has died.
     *
     * @param trader is the trader the player is fighting against
     */
    public void checkTraderDefeat(Trader trader) {
        AlertDialog.Builder deadDialog = new AlertDialog.Builder(EncounterActivity.this);
        if (player.isDead() || trader.isDead()) {
            if (player.isDead()) {
                deadDialog.setTitle("Defeated...");
                deadDialog.setMessage("The trader has defeated you");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(EncounterActivity.this, DiedActivity.class);
                        startActivity(intent);
                    }
                });

            } else if (trader.isDead()) {
                deadDialog.setTitle("Victory!");
                deadDialog.setMessage("You have defeated the trader");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        executeEvent();
                    }
                });
            }

            deadDialog.setCancelable(false);
            deadDialog.create().setCanceledOnTouchOutside(false);
            deadDialog.show();
        }
    }

    /**
     * When the pirate and the player are in a fight,
     * this method is called after every move to check
     * if one of them has died.
     *
     * @param pirate is the pirate the player is fighting against
     */
    public void checkPirateDefeat(Pirate pirate) {
        AlertDialog.Builder deadDialog = new AlertDialog.Builder(EncounterActivity.this);
        if (player.isDead() || pirate.isDead()) {
            if (player.isDead()) {
                deadDialog.setTitle("Defeated...");
                deadDialog.setMessage("The pirate has defeated you");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(EncounterActivity.this, DiedActivity.class);
                        startActivity(intent);
                    }
                });

            } else if (pirate.isDead()) {
                deadDialog.setTitle("Victory!");
                deadDialog.setMessage("You have defeated the pirate");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        executeEvent();
                    }
                });
            }

            deadDialog.setCancelable(false);
            deadDialog.create().setCanceledOnTouchOutside(false);
            deadDialog.show();
        }
    }

    /**
     * When the police and the player are in a fight,
     * this method is called after every move to check
     * if one of them has died.
     *
     * @param police is the police the player is fighting against
     */
    public void checkPoliceDefeat (Police police) {
        AlertDialog.Builder deadDialog = new AlertDialog.Builder(EncounterActivity.this);
        if (player.isDead() || police.isDead()) {
            if (player.isDead()) {
                deadDialog.setTitle("Defeated...");
                deadDialog.setMessage("The police has defeated you");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(EncounterActivity.this, DiedActivity.class);
                        startActivity(intent);
                    }
                });

            } else if (police.isDead()) {
                deadDialog.setTitle("Victory!");
                deadDialog.setMessage("You have defeated the police");
                deadDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        executeEvent();
                    }
                });
            }

            deadDialog.setCancelable(false);
            deadDialog.create().setCanceledOnTouchOutside(false);
            deadDialog.show();
        }

    }


    //SeekBar handler for buying a resource from a trader.
    private final SeekBar.OnSeekBarChangeListener traderSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // updated continuously as the user slides the thumb
                    traderQuantity = seekBar.getProgress();
                    textView3.setText(getString(R.string.buy_text_3, traderQuantity));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // called when the user first touches the SeekBar
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // called after the user finishes moving the SeekBar
                }
            };
}
