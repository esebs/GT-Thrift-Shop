package com.github.esebs.cs2340project.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.esebs.cs2340project.spacetrader.R;
import com.github.esebs.cs2340project.spacetrader.entities.Resource;
import com.github.esebs.cs2340project.spacetrader.model.Model;
import com.github.esebs.cs2340project.spacetrader.viewmodels.TradingViewModel;

import org.w3c.dom.Text;

public class SellFragment extends Fragment {
    private Model model = Model.getModelInstance();

    private TradingViewModel tradingViewModel = new TradingViewModel();

    private Button waterButton;
    private Button fursButton;
    private Button foodButton;
    private Button oreButton;
    private Button gamesButton;
    private Button firearmsButton;
    private Button medicineButton;
    private Button machinesButton;
    private Button narcoticsButton;
    private Button robotsButton;

    private TextView waterPrice;
    private TextView fursPrice;
    private TextView foodPrice;
    private TextView orePrice;
    private TextView gamesPrice;
    private TextView firearmsPrice;
    private TextView medicinePrice;
    private TextView machinesPrice;
    private TextView narcoticsPrice;
    private TextView robotsPrice;

    //The amount the player wants to sell
    private int waterQuanity;
    private int fursQuanity;
    private int foodQuanity;
    private int oreQuanity;
    private int gamesQuanity;
    private int firearmsQuanity;
    private int medicineQuanity;
    private int machinesQuanity;
    private int narcoticsQuanity;
    private int robotsQuanity;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private SeekBar seekBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_fragment, container, false);
        final View dialog = inflater.inflate(R.layout.dialog, container,false);

        //Selling Water
        waterButton = view.findViewById(R.id.water_qty);
        waterPrice = view.findViewById(R.id.water_price);
        waterQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.WATER) == -1) {
            //Room doesn't have the resources available, disable buying
            waterButton.setText("" + tradingViewModel.getSellQuantity(Resource.WATER));
            waterButton.setEnabled(false);
            waterPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            waterButton.setText("" + tradingViewModel.getSellQuantity(Resource.WATER));
            waterPrice.setText("" + tradingViewModel.getSellPrice(Resource.WATER) + " cr.");
        }

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.WATER);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Water.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(waterQuanity + " units");

                seekBar.setOnSeekBarChangeListener(waterSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.WATER, waterQuanity);
                                waterButton.setText("" + tradingViewModel.getSellQuantity(Resource.WATER));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Furs
        fursButton = view.findViewById(R.id.furs_qty);
        fursPrice = view.findViewById(R.id.furs_price);
        fursQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FURS) == -1) {
            //Room doesn't have the resources available, disable buying
            fursButton.setText("" + tradingViewModel.getSellQuantity(Resource.FURS));
            fursButton.setEnabled(false);
            fursPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            fursButton.setText("" + tradingViewModel.getSellQuantity(Resource.FURS));
            fursPrice.setText("" + tradingViewModel.getSellPrice(Resource.FURS) + " cr.");
        }

        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.FURS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Furs.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(fursQuanity + " units");

                seekBar.setOnSeekBarChangeListener(fursSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FURS, fursQuanity);
                                fursButton.setText("" + tradingViewModel.getSellQuantity(Resource.FURS));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Food
        foodButton = view.findViewById(R.id.food_qty);
        foodPrice = view.findViewById(R.id.food_price);
        foodQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FOOD) == -1) {
            //Room doesn't have the resources available, disable buying
            foodButton.setText("" + tradingViewModel.getSellQuantity(Resource.FOOD));
            foodButton.setEnabled(false);
            foodPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            foodButton.setText("" + tradingViewModel.getSellQuantity(Resource.FOOD));
            foodPrice.setText("" + tradingViewModel.getSellPrice(Resource.FOOD) + " cr.");
        }

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.FOOD);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Food.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(foodQuanity + " units");

                seekBar.setOnSeekBarChangeListener(foodSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FOOD, foodQuanity);
                                foodButton.setText("" + tradingViewModel.getSellQuantity(Resource.FOOD));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Ore
        oreButton = view.findViewById(R.id.ore_qty);
        orePrice = view.findViewById(R.id.ore_price);
        oreQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.ORE) == -1) {
            //Room doesn't have the resources available, disable buying
            oreButton.setText("" + tradingViewModel.getSellQuantity(Resource.ORE));
            oreButton.setEnabled(false);
            orePrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            oreButton.setText("" + tradingViewModel.getSellQuantity(Resource.ORE));
            orePrice.setText("" + tradingViewModel.getSellPrice(Resource.ORE) + " cr.");
        }

        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.ORE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Ore.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(oreQuanity + " units");

                seekBar.setOnSeekBarChangeListener(oreSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.ORE, oreQuanity);
                                oreButton.setText("" + tradingViewModel.getSellQuantity(Resource.ORE));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Games
        gamesButton = view.findViewById(R.id.games_qty);
        gamesPrice = view.findViewById(R.id.games_price);
        gamesQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.GAMES) == -1) {
            //Room doesn't have the resources available, disable buying
            gamesButton.setText("" + tradingViewModel.getSellQuantity(Resource.GAMES));
            gamesButton.setEnabled(false);
            gamesPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            gamesButton.setText("" + tradingViewModel.getSellQuantity(Resource.GAMES));
            gamesPrice.setText("" + tradingViewModel.getSellPrice(Resource.GAMES) + " cr.");
        }

        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.GAMES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Games.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(gamesQuanity + " units");

                seekBar.setOnSeekBarChangeListener(gamesSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.GAMES, gamesQuanity);
                                gamesButton.setText("" + tradingViewModel.getSellQuantity(Resource.GAMES));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Firearms
        firearmsButton = view.findViewById(R.id.firearms_qty);
        firearmsPrice = view.findViewById(R.id.firearms_price);
        firearmsQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FIREARMS) == -1) {
            //Room doesn't have the resources available, disable buying
            firearmsButton.setText("" + tradingViewModel.getSellQuantity(Resource.FIREARMS));
            firearmsButton.setEnabled(false);
            firearmsPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            firearmsButton.setText("" + tradingViewModel.getSellQuantity(Resource.FIREARMS));
            firearmsPrice.setText("" + tradingViewModel.getSellPrice(Resource.FIREARMS) + " cr.");
        }

        firearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.FIREARMS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Firearms.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(firearmsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(firearmsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FIREARMS, firearmsQuanity);
                                firearmsButton.setText("" + tradingViewModel.getSellQuantity(Resource.FIREARMS));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Medicine
        medicineButton = view.findViewById(R.id.medicine_qty);
        medicinePrice = view.findViewById(R.id.medicine_price);
        medicineQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.MEDICINE) == -1) {
            //Room doesn't have the resources available, disable buying
            medicineButton.setText("" + tradingViewModel.getSellQuantity(Resource.MEDICINE));
            medicineButton.setEnabled(false);
            medicinePrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            medicineButton.setText("" + tradingViewModel.getSellQuantity(Resource.MEDICINE));
            medicinePrice.setText("" + tradingViewModel.getSellPrice(Resource.MEDICINE) + " cr.");
        }

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.MEDICINE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Medicine.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(medicineQuanity + " units");

                seekBar.setOnSeekBarChangeListener(medicineSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.MEDICINE, medicineQuanity);
                                medicineButton.setText("" + tradingViewModel.getSellQuantity(Resource.MEDICINE));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });


        //Selling Machine
        machinesButton = view.findViewById(R.id.machine_qty);
        machinesPrice = view.findViewById(R.id.machine_price);
        machinesQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.MACHINES) == -1) {
            //Room doesn't have the resources available, disable buying
            machinesButton.setText("" + tradingViewModel.getSellQuantity(Resource.MACHINES));
            machinesButton.setEnabled(false);
            machinesPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            machinesButton.setText("" + tradingViewModel.getSellQuantity(Resource.MACHINES));
            machinesPrice.setText("" + tradingViewModel.getSellPrice(Resource.MACHINES) + " cr.");
        }

        machinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.MACHINES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Machines.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(machinesQuanity + " units");

                seekBar.setOnSeekBarChangeListener(machinesSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.MACHINES, machinesQuanity);
                                machinesButton.setText("" + tradingViewModel.getSellQuantity(Resource.MACHINES));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Narcotics
        narcoticsButton = view.findViewById(R.id.narcotics_qty);
        narcoticsPrice = view.findViewById(R.id.narcotics_price);
        narcoticsQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.NARCOTICS) == -1) {
            //Room doesn't have the resources available, disable buying
            narcoticsButton.setText("" + tradingViewModel.getSellQuantity(Resource.NARCOTICS));
            narcoticsButton.setEnabled(false);
            narcoticsPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            narcoticsButton.setText("" + tradingViewModel.getSellQuantity(Resource.NARCOTICS));
            narcoticsPrice.setText("" + tradingViewModel.getSellPrice(Resource.NARCOTICS) + " cr.");
        }

        narcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.NARCOTICS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Narcotics.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(narcoticsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(narcoticsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.NARCOTICS, narcoticsQuanity);
                                narcoticsButton.setText("" + tradingViewModel.getSellQuantity(Resource.NARCOTICS));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });



        //Selling Robots
        robotsButton = view.findViewById(R.id.robots_qty);
        robotsPrice = view.findViewById(R.id.robots_price);
        robotsQuanity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.ROBOTS) == -1) {
            //Room doesn't have the resources available, disable buying
            robotsButton.setText("" + tradingViewModel.getSellQuantity(Resource.ROBOTS));
            robotsButton.setEnabled(false);
            robotsPrice.setText("--- cr.");
        } else {
            //Room has the resources available, display the quantity and the price
            robotsButton.setText("" + tradingViewModel.getSellQuantity(Resource.ROBOTS));
            robotsPrice.setText("" + tradingViewModel.getSellPrice(Resource.ROBOTS) + " cr.");
        }

        robotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSellable = tradingViewModel.calculateMaxSellable(Resource.ROBOTS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 = (TextView) dialog.findViewById(R.id.text_view_1);
                textView1.setText("You can sell up to " + maxSellable + " units of Robots.");
                textView2 = (TextView) dialog.findViewById(R.id.text_view_2);
                textView2.setText("How many would you like to sell?");


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSellable);
                seekBar.setProgress(0);


                textView3 = (TextView) dialog.findViewById(R.id.quantity);
                textView3.setText(robotsQuanity + " units");

                seekBar.setOnSeekBarChangeListener(robotsSeekBarListener);

                //Sets up the dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.ROBOTS, robotsQuanity);
                                robotsButton.setText("" + tradingViewModel.getSellQuantity(Resource.ROBOTS));

                                System.out.println(model.getPlayer());
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });


        return view;
    }


    //SeekBar handler for Selling Water
    SeekBar.OnSeekBarChangeListener waterSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes water quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy water seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            waterQuanity = seekBar.getProgress();
            textView3.setText(waterQuanity + " units");
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


    //SeekBar handler for Selling Furs
    SeekBar.OnSeekBarChangeListener fursSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes furs quantity to new value and updates seek bar counter
         *
         * @param seekBar  seekBar for Alert
         * @param progress current progress of the buy furs seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            fursQuanity = seekBar.getProgress();
            textView3.setText(fursQuanity + " units");
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


    //SeekBar handler for Selling Food
    SeekBar.OnSeekBarChangeListener foodSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes food quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy food seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            foodQuanity = seekBar.getProgress();
            textView3.setText(foodQuanity + " units");
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


    //SeekBar handler for Selling Ore
    SeekBar.OnSeekBarChangeListener oreSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes ore quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy ore seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            oreQuanity = seekBar.getProgress();
            textView3.setText(oreQuanity + " units");
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


    //SeekBar handler for Selling Games
    SeekBar.OnSeekBarChangeListener gamesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes games quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy games seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            gamesQuanity = seekBar.getProgress();
            textView3.setText(gamesQuanity + " units");
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


    //SeekBar handler for Selling Firearms
    SeekBar.OnSeekBarChangeListener firearmsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes firearms quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy firearms seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            firearmsQuanity = seekBar.getProgress();
            textView3.setText(firearmsQuanity + " units");
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


    //SeekBar handler for Selling Medicine
    SeekBar.OnSeekBarChangeListener medicineSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes medicine quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy medicine seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            medicineQuanity = seekBar.getProgress();
            textView3.setText(medicineQuanity + " units");
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

    //SeekBar handler for Selling Machines
    SeekBar.OnSeekBarChangeListener machinesSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes machines quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy machines seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            machinesQuanity = seekBar.getProgress();
            textView3.setText(machinesQuanity + " units");
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


    //SeekBar handler for Selling Narcotics
    SeekBar.OnSeekBarChangeListener narcoticsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes narcotics quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy narcotics seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            narcoticsQuanity = seekBar.getProgress();
            textView3.setText(narcoticsQuanity + " units");
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


    //SeekBar handler for Selling Robots
    SeekBar.OnSeekBarChangeListener robotsSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes robots quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy robots seekbar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            robotsQuanity = seekBar.getProgress();
            textView3.setText(robotsQuanity + " units");
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
