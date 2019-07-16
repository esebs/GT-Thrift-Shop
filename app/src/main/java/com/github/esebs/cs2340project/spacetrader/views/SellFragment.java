package com.github.esebs.cs2340project.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

/**
 * This class will generate and display the sell fragment
 * when the 'Sell' tab is pressed
 *
 * @version 1.0
 * @author Elio Gerges
 */
class SellFragment extends Fragment {
    private final Model model = Model.getModelInstance();

    private final TradingViewModel tradingViewModel = new TradingViewModel();

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

    //The amount the player wants to sell
    private int waterQuantity;
    private int fursQuantity;
    private int foodQuantity;
    private int oreQuantity;
    private int gamesQuantity;
    private int firearmsQuantity;
    private int medicineQuantity;
    private int machinesQuantity;
    private int narcoticsQuantity;
    private int robotsQuantity;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private SeekBar seekBar;


    /**
     * When the 'Sell' tab is pressed, onCreateView method is executed
     * and the contents of the 'Sell' tab are displayed. The items,
     * number of items of sale and the item sale price are displayed.
     * When the user presses the button with the item quantity, they
     * are prompted with an alert asking the user how many items they
     * would like to sell. The user can then select a number then press
     * sell to make that transaction. The quantity buttons are then updated
     * accordingly.
     *
     * @param inflater is the inflater for the layout
     * @param container is the container for all items in the fragment
     * @param savedInstanceState is the saved instance of the fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell_fragment, container, false);
        final View dialog = inflater.inflate(R.layout.trade_dialog, container,false);
        TextView waterPrice;
        TextView fursPrice;
        TextView foodPrice;
        TextView orePrice;
        TextView gamesPrice;
        TextView firearmsPrice;
        TextView medicinePrice;
        TextView machinesPrice;
        TextView narcoticsPrice;
        TextView robotsPrice;

        textView2 = dialog.findViewById(R.id.text_view_2);
        textView2.setText(getString(R.string.sell_text_2));

        //Selling Water
        waterButton = view.findViewById(R.id.water_qty);
        waterPrice = view.findViewById(R.id.water_price);
        waterQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.WATER) == -1) {
            //Room doesn't have the resources available, disable buying
            waterButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.WATER)));
            waterButton.setEnabled(false);
            waterPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            waterButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.WATER)));
            waterPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.WATER)));
        }

        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.WATER);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "water"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, waterQuantity));

                seekBar.setOnSeekBarChangeListener(waterSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.WATER, waterQuantity);
                                waterButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.WATER)));

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
        fursQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FURS) == -1) {
            //Room doesn't have the resources available, disable buying
            fursButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FURS)));
            fursButton.setEnabled(false);
            fursPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            fursButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FURS)));
            fursPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.FURS)));
        }

        fursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.FURS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "furs"));
                textView2 =  dialog.findViewById(R.id.text_view_2);

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, fursQuantity));

                seekBar.setOnSeekBarChangeListener(fursSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FURS, fursQuantity);
                                fursButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.FURS)));

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
        foodQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FOOD) == -1) {
            //Room doesn't have the resources available, disable buying
            foodButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FOOD)));
            foodButton.setEnabled(false);
            foodPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            foodButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FOOD)));
            foodPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.FOOD)));
        }

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.FOOD);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "food"));
                textView2 =  dialog.findViewById(R.id.text_view_2);

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, foodQuantity));

                seekBar.setOnSeekBarChangeListener(foodSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FOOD, foodQuantity);
                                foodButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.FOOD)));

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
        oreQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.ORE) == -1) {
            //Room doesn't have the resources available, disable buying
            oreButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.ORE)));
            oreButton.setEnabled(false);
            orePrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            oreButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.ORE)));
            orePrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.ORE)));
        }

        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.ORE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "ore"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, oreQuantity));

                seekBar.setOnSeekBarChangeListener(oreSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.ORE, oreQuantity);
                                oreButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.ORE)));

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
        gamesQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.GAMES) == -1) {
            //Room doesn't have the resources available, disable buying
            gamesButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.GAMES)));
            gamesButton.setEnabled(false);
            gamesPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            gamesButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.GAMES)));
            gamesPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.GAMES)));
        }

        gamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.GAMES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "games"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, gamesQuantity));

                seekBar.setOnSeekBarChangeListener(gamesSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.GAMES, gamesQuantity);
                                gamesButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.GAMES)));

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
        firearmsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.FIREARMS) == -1) {
            //Room doesn't have the resources available, disable buying
            firearmsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FIREARMS)));
            firearmsButton.setEnabled(false);
            firearmsPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            firearmsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.FIREARMS)));
            firearmsPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.FIREARMS)));
        }

        firearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.FIREARMS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "firearms"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, firearmsQuantity));

                seekBar.setOnSeekBarChangeListener(firearmsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.FIREARMS, firearmsQuantity);
                                firearmsButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.FIREARMS)));

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
        medicineQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.MEDICINE) == -1) {
            //Room doesn't have the resources available, disable buying
            medicineButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.MEDICINE)));
            medicineButton.setEnabled(false);
            medicinePrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            medicineButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.MEDICINE)));
            medicinePrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.MEDICINE)));
        }

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.MEDICINE);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }

                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "medicine"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, medicineQuantity));

                seekBar.setOnSeekBarChangeListener(medicineSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.MEDICINE, medicineQuantity);
                                medicineButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.MEDICINE)));

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
        machinesQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.MACHINES) == -1) {
            //Room doesn't have the resources available, disable buying
            machinesButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.MACHINES)));
            machinesButton.setEnabled(false);
            machinesPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            machinesButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.MACHINES)));
            machinesPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.MACHINES)));
        }

        machinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.MACHINES);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "machines"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, machinesQuantity));

                seekBar.setOnSeekBarChangeListener(machinesSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.MACHINES, machinesQuantity);
                                machinesButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.MACHINES)));

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
        narcoticsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.NARCOTICS) == -1) {
            //Room doesn't have the resources available, disable buying
            narcoticsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.NARCOTICS)));
            narcoticsButton.setEnabled(false);
            narcoticsPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            narcoticsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.NARCOTICS)));
            narcoticsPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.NARCOTICS)));
        }

        narcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.NARCOTICS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "narcotics"));
                textView2 =  dialog.findViewById(R.id.text_view_2);

                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, narcoticsQuantity));

                seekBar.setOnSeekBarChangeListener(narcoticsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.NARCOTICS,
                                        narcoticsQuantity);
                                narcoticsButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.NARCOTICS)));

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
        robotsQuantity = 0;

        //Check to see if Room has this resource
        if (tradingViewModel.getSellPrice(Resource.ROBOTS) == -1) {
            //Room doesn't have the resources available, disable buying
            robotsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.ROBOTS)));
            robotsButton.setEnabled(false);
            robotsPrice.setText(getString(R.string.price_not_available));
        } else {
            //Room has the resources available, display the quantity and the price
            robotsButton.setText(getString(R.string.sell_resource_quantity,
                    tradingViewModel.getSellQuantity(Resource.ROBOTS)));
            robotsPrice.setText(getString(R.string.sell_resource_price,
                    tradingViewModel.getSellPrice(Resource.ROBOTS)));
        }

        robotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                int maxSell = tradingViewModel.calculateMaxSellQuantity(Resource.ROBOTS);

                if (dialog.getParent() != null) {
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                textView1 =  dialog.findViewById(R.id.text_view_1);
                textView1.setText(getString(R.string.sell_text_1, maxSell, "robots"));
                textView2 =  dialog.findViewById(R.id.text_view_2);


                seekBar = dialog.findViewById(R.id.seek_bar);
                //Sets the max of the seekBar to the max that you can buy
                seekBar.setMax(maxSell);
                seekBar.setProgress(0);


                textView3 =  dialog.findViewById(R.id.quantity);
                textView3.setText(getString(R.string.buy_text_3, robotsQuantity));

                seekBar.setOnSeekBarChangeListener(robotsSeekBarListener);

                //Sets up the trade_dialog layout, "Buy" button, "Cancel" button.
                //It then creates the AlertDialog and displays it.
                alertDialog.setView(dialog)
                        .setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tradingViewModel.sellResources(Resource.ROBOTS, robotsQuantity);
                                robotsButton.setText(getString(R.string.sell_resource_quantity,
                                        tradingViewModel.getSellQuantity(Resource.ROBOTS)));

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
    private final SeekBar.OnSeekBarChangeListener waterSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes water quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy water seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            waterQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, waterQuantity));
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
    private final SeekBar.OnSeekBarChangeListener fursSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes furs quantity to new value and updates seek bar counter
         *
         * @param seekBar  seekBar for Alert
         * @param progress current progress of the buy furs seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            fursQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, fursQuantity));
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
    private final SeekBar.OnSeekBarChangeListener foodSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes food quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy food seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            foodQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, foodQuantity));
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
    private final SeekBar.OnSeekBarChangeListener oreSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes ore quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy ore seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            oreQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, oreQuantity));
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
    private final SeekBar.OnSeekBarChangeListener gamesSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes games quantity to new value and updates seek bar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy games seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            gamesQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, gamesQuantity));
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
    private final SeekBar.OnSeekBarChangeListener firearmsSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes firearms quantity to new value and updates seekBar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy firearms seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            firearmsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, firearmsQuantity));
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
    private final SeekBar.OnSeekBarChangeListener medicineSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes medicine quantity to new value and updates seekBar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy medicine seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            medicineQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, medicineQuantity));
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
    private final SeekBar.OnSeekBarChangeListener machinesSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes machines quantity to new value and updates seekBar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy machines seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            machinesQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, machinesQuantity));
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
    private final SeekBar.OnSeekBarChangeListener narcoticsSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes narcotics quantity to new value and updates seekBar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy narcotics seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            narcoticsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, narcoticsQuantity));
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
    private final SeekBar.OnSeekBarChangeListener robotsSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
        /**
         * Changes robots quantity to new value and updates seekBar counter
         * @param seekBar seekBar for Alert
         * @param progress current progress of the buy robots seekBar
         * @param fromUser if progress is from user
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            robotsQuantity = seekBar.getProgress();
            textView3.setText(getString(R.string.buy_text_3, robotsQuantity));
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

    /**
     * This method will refresh the tabs each time the users revisits a tab
     *
     * @param isVisibleToUser is a boolean representing if a fragment is visible to the user
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (getFragmentManager() != null)) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
