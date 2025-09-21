package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.AoCGame;
import age.of.civilizations2.jakowski.lukasz.CFG;
import age.of.civilizations2.jakowski.lukasz.Editor_NeighboringProvinces;
import age.of.civilizations2.jakowski.lukasz.Map_Scale;
import age.of.civilizations2.jakowski.lukasz.Message_WeCanSignPeace;
import age.of.civilizations2.jakowski.lukasz.Point_XY;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Commands {
    protected static final int CONSOLE_LIMIT = 650;
    protected static List<String> sConsole = new ArrayList<String>();
    protected static List<Point_XY> lShit = new ArrayList<Point_XY>();
    protected static long lShitTime = 0L;

    Commands() {
    }

    protected static final void addMessage(String nMess) {
        sConsole.add(nMess);
        if (sConsole.size() > 650) {
            sConsole.remove(0);
        }
    }

    protected static final void execute(String nCommand) {
        block118: {
            if (nCommand.length() == 0) {
                return;
            }
            Commands.addMessage("");
            Commands.addMessage("#" + nCommand);
            String[] tempCommand = nCommand.toLowerCase().split(" ");
            try {
                if (tempCommand.length <= 0) break block118;
                if (tempCommand[0].equals("console")) {
                    CFG.menuManager.setVisible_InGame_FlagAction_Console(!CFG.menuManager.getVisible_InGame_FlagAction_Console());
                    if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.toast.setInView("Hello");
                    }
                    return;
                }
                if (tempCommand[0].equals("info")) {
                    Commands.addMessage("FramesPerSecond: " + Gdx.graphics.getFramesPerSecond());
                    Commands.addMessage("Width: " + Gdx.graphics.getWidth());
                    Commands.addMessage("Height: " + Gdx.graphics.getHeight());
                    Commands.addMessage("PpiX: " + Gdx.graphics.getPpiX());
                    Commands.addMessage("PpiY: " + Gdx.graphics.getPpiY());
                    Commands.addMessage("Density: " + Gdx.graphics.getDensity());
                    Commands.addMessage("XHDPI: " + CFG.XHDPI);
                    Commands.addMessage("XXHDPI: " + CFG.XXHDPI);
                    Commands.addMessage("XXXHDPI: " + CFG.XXXHDPI);
                    Commands.addMessage("XXXXHDPI: " + CFG.XXXXHDPI);
                    return;
                }
                if (tempCommand[0].equals("debug")) {
                    CFG.DEBUG_MODE = !CFG.DEBUG_MODE;
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled"))));
                    CFG.toast.setInView(CFG.langManager.get(CFG.langManager.get("DEBUG") + ": " + (CFG.DEBUG_MODE ? CFG.langManager.get("Enabled") : CFG.langManager.get("Disabled"))));
                    return;
                }
                if (tempCommand[0].equals("center")) {
                    if (tempCommand.length > 1) {
                        try {
                            int tempID = Integer.parseInt(tempCommand[1]);
                            if (tempID < CFG.game.getProvincesSize()) {
                                CFG.map.getMapCoordinates().centerToProvinceID(tempID);
                                CFG.game.setActiveProvinceID(tempID);
                                CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                            } else {
                                Commands.IllegalCommand();
                            }
                            return;
                        }
                        catch (IllegalArgumentException ex) {
                            Commands.IllegalCommand();
                        }
                        catch (IndexOutOfBoundsException ex) {
                            Commands.IllegalCommand();
                        }
                    } else {
                        CFG.map.getMapScroll().stopScrollingTheMap();
                        CFG.map.getMapScale().setCurrentScale(Map_Scale.MINSCALE);
                        CFG.map.getMapCoordinates().setNewPosX(-((int)((float)(CFG.map.getMapBG().getWidth() / 2) - (float)CFG.GAME_WIDTH / Map_Scale.MINSCALE / 2.0f)));
                        CFG.map.getMapCoordinates().setNewPosY(-((int)((float)(CFG.map.getMapBG().getHeight() / 2) - (float)CFG.GAME_HEIGHT / Map_Scale.MINSCALE / 2.0f)));
                    }
                    return;
                }
                if (tempCommand[0].equals("centerciv")) {
                    if (tempCommand.length > 1) {
                        try {
                            int tempID = Integer.parseInt(tempCommand[1]);
                            if (tempID < CFG.game.getCivsSize() && tempID > 0) {
                                CFG.map.getMapCoordinates().centerToCivilizationBox(tempID, true);
                                CFG.toast.setInView(CFG.game.getCiv(tempID).getCivName());
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                                if (!tempCommand[1].equals(CFG.game.getCiv(i).getCivName()) && !tempCommand[1].equals(CFG.game.getCiv(i).getCivTag())) continue;
                                CFG.map.getMapCoordinates().centerToCivilizationBox(i, true);
                                CFG.toast.setInView(CFG.game.getCiv(i).getCivName());
                                return;
                            }
                            Commands.IllegalCommand();
                        }
                        catch (IndexOutOfBoundsException ex) {
                            Commands.IllegalCommand();
                        }
                    } else {
                        Commands.IllegalCommand();
                    }
                    return;
                }
                if (tempCommand[0].equals("scale")) {
                    if (tempCommand.length > 1) {
                        try {
                            tempCommand[1] = tempCommand[1].replace(',', '.');
                            float tempS = Float.parseFloat(tempCommand[1]);
                            CFG.map.getMapScale().setCurrentScale(tempS);
                            return;
                        }
                        catch (IllegalArgumentException ex) {
                            Commands.IllegalCommand();
                        }
                    } else {
                        CFG.map.getMapScale().setCurrentScale(1.0f);
                    }
                    return;
                }
                if (tempCommand[0].equals("close") || tempCommand[0].equals("bye")) {
                    if (CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(false);
                    }
                    CFG.menuManager.getKeyboard().setVisible(false);
                    return;
                }
                if (tempCommand[0].equals("fps")) {
                    AoCGame.drawFPS = !AoCGame.drawFPS;
                    return;
                }
                if (tempCommand[0].equals("hi") || tempCommand[0].equals("hello")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Hello") + "!");
                    Commands.addMessage(CFG.langManager.get("Hello") + "!");
                    return;
                }
                if (tempCommand[0].equals("spin") || tempCommand[0].equals("iss") || tempCommand[0].equals("wheee") || tempCommand[0].equals("whee")) {
                    CFG.map.getMapScroll().setScrollPos(125000, 10);
                    CFG.map.getMapScroll().setScrollPos(10, 10);
                    CFG.menuManager.getKeyboard().setVisible(false);
                    CFG.menuManager.setVisible_InGame_FlagAction(false);
                    CFG.map.getMapScroll().startScrollingTheMap();
                    CFG.toast.setInView(CFG.langManager.get("Wheee") + "!");
                    Commands.addMessage(CFG.langManager.get("Wheee") + "!");
                    return;
                }
                if (tempCommand[0].equals("help") || tempCommand[0].equals("commands")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    CFG.toast.setInView(CFG.langManager.get("Help"));
                    Commands.addMessage("#" + CFG.sVERSION + ": " + "1.01ELA");
                    Commands.addMessage("");
                    Commands.addMessage("console");
                    Commands.addMessage("close");
                    Commands.addMessage("civ");
                    Commands.addMessage("civs");
                    Commands.addMessage("province");
                    Commands.addMessage("center X");
                    Commands.addMessage("centerciv X");
                    Commands.addMessage("scale X");
                    return;
                }
                if (tempCommand[0].equals("party") || tempCommand[0].equals("fuck") || tempCommand[0].equals("fuk") || tempCommand[0].equals("flags")) {
                    if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) {
                        CFG.menuManager.setVisible_InGame_FlagAction_Console(true);
                    }
                    Random oR = new Random();
                    lShit.clear();
                    for (int i = 0; i < CFG.GAME_WIDTH + CFG.GAME_HEIGHT; ++i) {
                        lShit.add(new Point_XY(oR.nextInt(CFG.GAME_WIDTH), oR.nextInt(CFG.GAME_HEIGHT)));
                    }
                    lShitTime = System.currentTimeMillis();
                    CFG.toast.setInView(CFG.langManager.get("clear"));
                    CFG.menuManager.getKeyboard().setVisible(false);
                    return;
                }
                if (!CFG.menuManager.getVisible_InGame_FlagAction_Console()) break block118;
                if (tempCommand[0].equals("clear")) {
                    sConsole.clear();
                    lShit.clear();
                    return;
                }
                if (tempCommand[0].equals("Drew Durnil") || tempCommand[0].equals("drew durnil") || tempCommand[0].equals("drewdurnil") || tempCommand[0].equals("drew") || tempCommand[0].equals("Drew") || tempCommand[0].equals("Durnil") || tempCommand[0].equals("durnil") || tempCommand[0].equals("observe") || tempCommand[0].equals("noob") || tempCommand[0].equals("Spectator") || tempCommand[0].equals("spectator")) {
                    CFG.toast.setInView("Games -> New Game -> Options -> Spectactor Mode");
                    CFG.toast.setTimeInView(4500);
                    Commands.addMessage("Games -> New Game -> Options -> Spectator Mode");
                    return;
                }
                if (tempCommand[0].equals("civs") || tempCommand[0].equals("tags")) {
                    for (int i = 1; i < CFG.game.getCivsSize(); ++i) {
                        Commands.addMessage("CIV ID: " + i + ", TAG: " + CFG.game.getCiv(i).getCivTag() + ", " + CFG.game.getCiv(i).getCivName());
                    }
                    return;
                }
                if (tempCommand[0].equals("civ")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        Commands.addMessage("CIV ID: " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() + ", TAG: " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag() + ", " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("province")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        Commands.addMessage("PROVINCE ID: " + CFG.game.getActiveProvinceID() + ", CIV TAG" + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivTag());
                        Commands.addMessage("POPULATION: " + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation() + ", ECONOMY" + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy());
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("showids")) {
                    CFG.game.buildDrawArmy_ShowIDs();
                    CFG.toast.setInView("showarmy");
                    CFG.toast.setTimeInView(4500);
                    Commands.addMessage(CFG.langManager.get("Disable") + ": showarmy");
                    return;
                }
                if (tempCommand[0].equals("showarmy")) {
                    CFG.game.buildDrawArmy();
                    return;
                }
                if (!CFG.SPECTATOR_MODE && tempCommand[0].equals("addplayer")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() > 0 && !CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getControlledByPlayer()) {
                        if (CFG.SPECTATOR_MODE) {
                            CFG.SPECTATOR_MODE = false;
                            if (CFG.game.getPlayersSize() == 1) {
                                CFG.game.removePlayer(0);
                            }
                        }
                        CFG.game.addPlayer(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                        CFG.gameAction.buildFogOfWar(CFG.game.getPlayersSize() - 1);
                        if (CFG.FOG_OF_WAR == 2) {
                            CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).buildMetProvincesAndCivs();
                        }
                        CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).loadPlayersFlag();
                        Commands.addMessage(CFG.langManager.get("Added") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                        return;
                    }
                    Commands.IllegalCommand();
                    CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                    Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                    Commands.addMessage("");
                    break block118;
                }
                if (tempCommand[0].equals("addciv")) {
                    if (tempCommand.length > 1) {
                        if (CFG.game.getActiveProvinceID() >= 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince() && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getIsCapital()) {
                            int i;
                            for (i = 1; i < CFG.game.getCivsSize(); ++i) {
                                if (!CFG.game.getCiv(i).getCivTag().equals(tempCommand[1])) continue;
                                Commands.IllegalCommand();
                                Commands.addMessage(CFG.game.getCiv(i).getCivName() + ": IS IN THE GAME");
                                Commands.addMessage("");
                                return;
                            }
                            CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(0), 0);
                            CFG.game.createScenarioAddCivilization(tempCommand[1], CFG.game.getActiveProvinceID(), false, true, true);
                            if (CFG.FOG_OF_WAR == 2) {
                                for (i = 0; i < CFG.game.getPlayersSize(); ++i) {
                                    CFG.game.getPlayer(i).addMetCivilization(true);
                                }
                            }
                            int tempPop = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation();
                            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().clearData();
                            CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), tempPop);
                            CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setMoney(100L);
                            CFG.gameAction.updateCivsMovementPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                            CFG.gameAction.updateCivsDiplomacyPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                            CFG.gameAction.buildRank_Score(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
                            int tActiveProvince = CFG.game.getActiveProvinceID();
                            CFG.game.setActiveProvinceID(-1);
                            CFG.game.setActiveProvinceID(tActiveProvince);
                            Commands.addMessage(CFG.langManager.get("Added") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                        } else {
                            Commands.IllegalCommand();
                            CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                            Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                            Commands.addMessage("");
                        }
                    } else {
                        Commands.IllegalCommand();
                    }
                    return;
                }
                if (tempCommand[0].equals("technology")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        if (tempCommand.length > 1) {
                            try {
                                int tempTech = Integer.parseInt(tempCommand[1]);
                                if (tempTech > 100) {
                                    tempTech = 100;
                                } else if (tempTech < 1) {
                                    tempTech = 1;
                                }
                                CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setTechnologyLevel((float)tempTech / 100.0f);
                                Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Technology") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getTechnologyLevel() + ", " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName());
                                Commands.addMessage("");
                                int tActiveProvince = CFG.game.getActiveProvinceID();
                                CFG.game.setActiveProvinceID(-1);
                                CFG.game.setActiveProvinceID(tActiveProvince);
                                CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Technology"));
                            }
                            catch (IllegalArgumentException ex) {
                                Commands.IllegalCommand();
                            }
                        } else {
                            Commands.IllegalCommand();
                        }
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("population")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), 750 + CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()));
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Population") + ": +" + 750);
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Population"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                            CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        }
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("armyset") || tempCommand[0].equals("setarmy")) {
                    int tArmy = Integer.parseInt(tempCommand[1]);
                    if (tArmy >= 0 && CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(0));
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(tArmy);
                        CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() + tArmy);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Army") + ": " + tArmy);
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Army"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("noliberity")) {
                    CFG.NO_LIBERITY = !CFG.NO_LIBERITY;
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Liberation") + ": " + (CFG.NO_LIBERITY ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled")));
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Liberation") + ": " + (CFG.NO_LIBERITY ? CFG.langManager.get("Disabled") : CFG.langManager.get("Enabled")));
                    return;
                }
                if (tempCommand[0].equals("id")) {
                    if (CFG.game.getActiveProvinceID() >= 0) {
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Province") + ": " + CFG.game.getActiveProvinceID());
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Civilization") + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName() + ": " + CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivID());
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("War"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("war")) {
                    int civA = Integer.parseInt(tempCommand[1]);
                    int civB = Integer.parseInt(tempCommand[2]);
                    if (civA >= 0 && civB >= 0 && CFG.game.getCiv(civA).getNumOfProvinces() > 0 && CFG.game.getCiv(civB).getNumOfProvinces() > 0) {
                        CFG.game.declareWar(civA, civB, true);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("War") + ": " + CFG.game.getCiv(civA).getCivName() + " -> " + CFG.game.getCiv(civB).getCivName());
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("War"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("peace")) {
                    int civA = Integer.parseInt(tempCommand[1]);
                    int civB = Integer.parseInt(tempCommand[2]);
                    if (civA >= 0 && civB >= 0 && CFG.game.getCivsAtWar(civA, civB)) {
                        CFG.game.getCiv((int)civB).civGameData.civilization_Diplomacy_GameData.messageBox.addMessage(new Message_WeCanSignPeace(civA));
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Added") + ": " + CFG.game.getCiv(civA).getCivName() + " -> " + CFG.game.getCiv(civB).getCivName());
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Added"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("Error"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("Error")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("buildport")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getLevelOfPort() >= 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfPort(1);
                        Commands.addMessage(Commands.cheatMess() + "Port built");
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Port built"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("buildfort")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfFort(1);
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                        Commands.addMessage(Commands.cheatMess() + "Fort built");
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Fort built"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("buildtower")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setLevelOfWatchTower(1);
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateDrawArmy();
                        Commands.addMessage(Commands.cheatMess() + "Tower built");
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Tower built"));
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("economy")) {
                    if (CFG.game.getActiveProvinceID() >= 0 && CFG.game.getProvince(CFG.game.getActiveProvinceID()).getWasteland() < 0 && !CFG.game.getProvince(CFG.game.getActiveProvinceID()).getSeaProvince()) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).setEconomy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getEconomy() + 600);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Economy") + ": +" + 600);
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Economy"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                            CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        }
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("army")) {
                    if (CFG.game.getActiveProvinceID() >= 0) {
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(CFG.activeCivilizationArmyID), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(CFG.activeCivilizationArmyID) + 300);
                        Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Army") + ": +" + 300);
                        Commands.addMessage("");
                        int tActiveProvince = CFG.game.getActiveProvinceID();
                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                        CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Army"));
                        if (CFG.menuManager.getVisibleInGame_CensusOfProvince()) {
                            CFG.menuManager.rebuildInGame_CensusOfProvince(CFG.game.getActiveProvinceID());
                        }
                    } else {
                        Commands.IllegalCommand();
                        CFG.toast.setInView(CFG.langManager.get("ChooseAProvince"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
                        Commands.addMessage(CFG.langManager.get(CFG.langManager.get("ChooseAProvince")));
                        Commands.addMessage("");
                    }
                    return;
                }
                if (tempCommand[0].equals("money")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() + 450L);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("Money") + ": +" + 450);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("Money"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("movement")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMovePoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMovePoints() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE / 2);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("MovementPoints") + ": +" + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE / 2);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("movement"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }
                if (tempCommand[0].equals("diplomacy")) {
                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setDiplomacyPoints(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getDiplomacyPoints() + CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 3 / 4);
                    Commands.addMessage(Commands.cheatMess() + CFG.langManager.get("DiplomacyPoints") + ": +" + (float)(CFG.ideologiesManager.getIdeology((int)CFG.game.getCiv((int)CFG.game.getPlayer((int)CFG.PLAYER_TURNID).getCivID()).getIdeologyID()).COST_OF_MOVE * 3 / 4) / 10.0f);
                    Commands.addMessage("");
                    CFG.toast.setInView(Commands.cheatMess() + CFG.langManager.get("diplomacy"));
                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());
                    return;
                }

                // Mod starts here!
				// Messed up tabulation in some places and spaghetti code; following the original game coding style
                if (tempCommand[0].equals("setowner")) {
                    String civ = tempCommand[1];

                    int civID = civ.equals("me") ? CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() : Integer.parseInt(civ);

                    int activeProvinceID = CFG.game.getActiveProvinceID();

                    if (activeProvinceID >= 0) {
                        CFG.game.getProvince(activeProvinceID).setCivID(civID, false);
                        CFG.game.getProvince(activeProvinceID).setCivID_Just(civID);

                        int tActiveProvince = CFG.game.getActiveProvinceID();

                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);

                        String message = String.format("%s now owns the province of %s.", CFG.game.getCiv(civID).getCivName(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());

                        addMessage(message);
                        addMessage("");

                        CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                        return;
                    }

                    addMessage("Select any province.");
                    addMessage("");

                    CFG.toast.setInView("Select any province.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                    return;
                }

                if (tempCommand[0].equals("addmoney")) {
                    long moneyToAdd;

			    	// XD
                    try {
                        moneyToAdd = Long.parseLong(tempCommand[1]);
                    } catch (NumberFormatException nfe) {
                        addMessage("Expected numbers!");
                        addMessage("");

                        CFG.toast.setInView("Expected numbers!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    CFG.game.getCiv(
                        CFG.game.getActiveProvinceID() < 0 ||
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() <= 0 
                        ? CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID() :
                        CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID())
                            .setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() + moneyToAdd);

                    addMessage("Money enrolled!");
                    addMessage("");

                    CFG.toast.setInView("Money enrolled!", CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());

                    return;
                }

                if (tempCommand[0].equals("ideology")) {
                    int ideology;

                    String ideologyInputString;

                    if (CFG.game.getActiveProvinceID() < 0) {
                        addMessage("Click on country's province!");
                        addMessage("");

	                    CFG.toast.setInView("Click on country's province!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int civID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();

                    switch (ideologyInputString = tempCommand[1]) {
                        case "democracy": { ideology = 0; break; }
                        case "monarchy": { ideology = 1; break; }
                        case "communism": { ideology = 2; break; }
                        case "fascism": { ideology = 3; break; }
                        case "republic": { ideology = 4; break; }
                        case "horde": { ideology = 5; break; }
                        case "citystate": { ideology = 6; break; }
                        default: { ideology = -1; break; }
                    }

                    if (ideology == -1) {
                        addMessage("Invalid ideology!");
                        addMessage("");

                        CFG.toast.setInView("Invalid ideology!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    CFG.game.getCiv(civID).setIdeologyID(ideology);
                    CFG.game.getCiv(civID).setCivTag(CFG.ideologiesManager.getRealTag(CFG.game.getCiv(civID).getCivTag()) + CFG.ideologiesManager.getIdeology(CFG.game.getCiv(civID).getIdeologyID()).getExtraTag());
                    CFG.game.getCiv(civID).loadFlag();

                    for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                        if (CFG.game.getPlayer(i).getCivID() != CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()) {
                            continue;
                        }

                        CFG.game.getPlayer(i).loadPlayersFlag();

                        break;
                    }

                    // Is this a base color or what the hell is this???
                    com.badlogic.gdx.graphics.Color iColor = CFG.ideologiesManager.getIdeology(CFG.game.getCiv(civID).getIdeologyID()).getColor();

                    Civilization civ = CFG.game.getCiv(civID);

                    civ.setR(Math.max(0, civ.getR() - (short)(iColor.r * 255.0f)));
                    civ.setG(Math.max(0, civ.getG() - (short)(iColor.g * 255.0f)));
                    civ.setB(Math.max(0, civ.getB() - (short)(iColor.b * 255.0f)));

                    CFG.setActiveCivInfo(CFG.getActiveCivInfo());

                    int tActiveProvince = CFG.game.getActiveProvinceID();

                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.setActiveProvinceID(tActiveProvince);

                    String message = String.format("%s has %s now!", CFG.game.getCiv(civID).getCivName(), ideologyInputString);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                    addMessage("Warning: possible minor glitches with colors/text;");
                    addMessage("");

                    return;
                }

                if (!CFG.SPECTATOR_MODE && tempCommand[0].equals("removeplayer")) {
                    if (CFG.game.getPlayersSize() > 1) {
                        String player = tempCommand[1];

                        int playerID;

                        // XD
                        try {
                            playerID = Integer.parseInt(player);
                        } catch(NumberFormatException nfe) {
                            addMessage("Expected player ID (number)!");
                            addMessage("");

                            CFG.toast.setInView("Expected player ID (number)!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                            return;
                        }

                        if(playerID > CFG.game.getPlayersSize() || playerID < 1) {
                            addMessage("Invalid player ID!");
                            addMessage("");

                            CFG.toast.setInView("Invalid player ID!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                            return;
                        }

                        playerID -= 1;

                        if(CFG.PLAYER_TURNID == playerID) {
                            addMessage("Can't remove the playing player! Skip the turn!");
                            addMessage("");

                            CFG.toast.setInView("Can't remove the playing player! Skip the turn!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                            return;
                            }

                        if(playerID == 0) {
                            // Don't try this at home!
                            try {
                                java.lang.reflect.Field playersField = CFG.game.getClass().getDeclaredField("lPlayers");

                                playersField.setAccessible(true);

                                List<Player> players = (List<Player>) playersField.get(CFG.game);

                                Player firstToGo = players.get(0);

                                players.set(0, players.get(players.size() - 1));
                                players.set(players.size() - 1, firstToGo);

                                CFG.game.removePlayer(players.size() - 1);

                                CFG.PLAYER_TURNID = 0;

                                addMessage("Removed the first player!");
                                addMessage("");

                                CFG.toast.setInView("Removed the first player!", CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                                return;
                            } catch(java.lang.NoSuchFieldException | java.lang.IllegalAccessException r) {
                                addMessage("Reflection error.");
                                addMessage("");

                                return;
                            }
                        } else {
                                CFG.game.removePlayer(playerID);

                                addMessage(String.format("Removed the %dth player!", playerID+1));
                                addMessage("");

                                CFG.toast.setInView(String.format("Removed the %dth player!", playerID+1), CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                            return;
                        }
                    } else {
                        addMessage("Can't remove the only one player!");
                        addMessage("");

                        CFG.toast.setInView("Can't remove the only one player!", CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                        return;
                    }
                }

                if (tempCommand[0].equals("revolt")) {
                    String countryID = tempCommand[1];

                    int activeProvinceID = CFG.game.getActiveProvinceID();

                    if (activeProvinceID < 0) {
                        Commands.addMessage("Click on country's province");
                        Commands.addMessage("");

                        CFG.toast.setInView("Click on country's province!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    Province province = CFG.game.getProvince(activeProvinceID);

                    String prevOwnerName = CFG.game.getCiv(province.getCivID()).getCivName();

                    int prevCivID = province.getCivID();

                    if (prevCivID <= 0) {
                        addMessage("Invalid province for revolt.");
                        addMessage("");

                        CFG.toast.setInView("Invalid province for revolt.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    CFG.game.createScenarioAddCivilization(countryID, activeProvinceID, false, true, true);

                    if (CFG.FOG_OF_WAR == 2) {
                        for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
                            CFG.game.getPlayer(i).addMetCivilization(true);
                        }
                    }

                    int tempPop = CFG.game.getProvince(activeProvinceID).getPopulationData().getPopulation();

                    CFG.game.getProvince(activeProvinceID).getPopulationData().clearData();
                    CFG.game.getProvince(activeProvinceID).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(activeProvinceID).getCivID(), tempPop);

                    CFG.game.getCiv(CFG.game.getProvince(activeProvinceID).getCivID()).setMoney(100000L);

                    CFG.gameAction.updateCivsMovementPoints(CFG.game.getProvince(activeProvinceID).getCivID());

                    CFG.gameAction.updateCivsDiplomacyPoints(CFG.game.getProvince(activeProvinceID).getCivID());

                    CFG.gameAction.buildRank_Score(CFG.game.getProvince(activeProvinceID).getCivID());

                    CFG.game.getCiv(CFG.game.getProvince(activeProvinceID).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(activeProvinceID).getCivID()).getNumOfUnits() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(0));

                    CFG.game.getProvince(activeProvinceID).updateArmy(15000);

                    CFG.game.getCiv(CFG.game.getProvince(activeProvinceID).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(activeProvinceID).getCivID()).getNumOfUnits() + 12500);

                    CFG.game.declareWar(prevCivID, CFG.game.getProvince(activeProvinceID).getCivID(), true);

                    int tActiveProvince = CFG.game.getActiveProvinceID();

                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.setActiveProvinceID(tActiveProvince);

                    String message = String.format("%s (%s) is now revolting against %s!", CFG.game.getCiv(CFG.game.getProvince(tActiveProvince).getCivID()).getCivName(), CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(tActiveProvince).getCivID()).getIdeologyID()).getName(), prevOwnerName);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                    return;
                }

                if (tempCommand[0].equals("intervene")) {
                    if (CFG.game.getActiveProvinceID() <= 0) {
                        addMessage("Pick a country (any province) that you want to help in war!");
                        addMessage("");

                        CFG.toast.setInView("Pick a country (any province) that you want to help in war!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int helpCountryID = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID();
                    int countryAgainst = Integer.parseInt(tempCommand[1]);

                    int warID = CFG.game.getWarID(helpCountryID, countryAgainst);

                    if (warID == -1) {
                        addMessage("Country is not in war at least with selected country ID.");
                        addMessage("");

                        CFG.toast.setInView("Country is not in war at least with selected country ID.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int playerID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    if(CFG.game.getCivRelation_OfCivB(playerID, helpCountryID) < 30.0f || CFG.game.getCivRelation_OfCivB(helpCountryID, playerID) < 30.0f) {
                        addMessage("You should have at least 30 points of *mutual* relationship with country you want to help.");
                        addMessage("");

                        CFG.toast.setInView("You should have at least 30 points of *mutual* relationship with country you want to help.", CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                        return;
                    }

                    CFG.game.joinWar(playerID, countryAgainst, warID);

                    CFG.game.setMilitaryAccess(playerID, helpCountryID, 625);
                    CFG.game.setMilitaryAccess(helpCountryID, playerID, 625);

                    CFG.game.setCivNonAggressionPact(playerID, helpCountryID, 130);
                    CFG.game.setCivNonAggressionPact(helpCountryID, playerID, 130);

                    CFG.game.setCivRelation_OfCivB(helpCountryID, playerID, 99.9f);

                    int tActiveProvince = CFG.game.getActiveProvinceID();

                    CFG.game.setActiveProvinceID(-1);
                    CFG.game.setActiveProvinceID(tActiveProvince);

                    String message = String.format("Intervened into %s's war against %s!", CFG.game.getCiv(helpCountryID).getCivName(), CFG.game.getCiv(countryAgainst).getCivName());

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                    return;
                }

                if(tempCommand[0].equals("switchplayer")) {
                    if (CFG.game.getActiveProvinceID() <= 0 || CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID() <= 0 || CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getControlledByPlayer()) {
                        addMessage("Pick a valid country (any country's province) that you want to switch to!");
                        addMessage("");

                        CFG.toast.setInView("Pick a valid country (any country's province) that you want to switch to!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    String from = CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getCivName();
                    String to = CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getCivName();

                    CFG.game.addPlayer(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());

                    CFG.gameAction.buildFogOfWar(CFG.game.getPlayersSize() - 1);

                    if (CFG.FOG_OF_WAR == 2) {
                        CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).buildMetProvincesAndCivs();
                    }

                    CFG.game.getPlayer(CFG.game.getPlayersSize() - 1).loadPlayersFlag();

                    try {
                        java.lang.reflect.Field playersField = CFG.game.getClass().getDeclaredField("lPlayers");

                        playersField.setAccessible(true);

                        List<Player> players = (List<Player>) playersField.get(CFG.game);

                        Player currentPlayer = players.get(CFG.PLAYER_TURNID);

                        players.set(CFG.PLAYER_TURNID, players.get(players.size() - 1));
                        players.set(players.size() - 1, currentPlayer);

                        CFG.game.removePlayer(players.size() - 1);

                        CFG.PLAYER_TURNID = CFG.PLAYER_TURNID;

                        CFG.menuManager.updateInGame_TOP_All(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID());

                        int tActiveProvince = CFG.game.getActiveProvinceID();

                        CFG.game.setActiveProvinceID(-1);
                        CFG.game.setActiveProvinceID(tActiveProvince);
                    } catch(java.lang.NoSuchFieldException | java.lang.IllegalAccessException r) {
                        addMessage("Reflection error.");
                        addMessage("");

                        return;
                    }

                    String message = String.format("Switched from %s to %s!", from, to);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPAutoBuild") || tempCommand[0].equalsIgnoreCase("rpab")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    String building = tempCommand[1].toLowerCase();

                    final int moneyAllocated = Integer.parseInt(tempCommand[2]);

                    int money = Integer.parseInt(tempCommand[2]);

                    boolean excluding;

                    java.util.Set<Integer> exclude = aeProcExclusion(tempCommand[3]);

                    if(exclude == null) {
                        return;
                    } else if(exclude.size() == 0) {
                        excluding = false;
                    } else {
                        excluding = true;
                    }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    Civilization playerCiv = CFG.game.getCiv(playerCivID);

                    int totalBuilt = 0, buildingsSkipped = 0, moneyLeft = 0;

                    if (moneyAllocated > playerCiv.getMoney()) {
                        addMessage("You have allocated more money than you have!");
                        addMessage("");

                        CFG.toast.setInView("You have allocated more money than you have!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int movePoints = playerCiv.getMovePoints();

                    playerCiv.setMoney((long) playerCiv.getMoney() - moneyAllocated);
                    playerCiv.setMovePoints(0);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    CFG.soundsManager.playSound(SoundsManager.SOUND_WORKSHOP);

                    for(int p=0; p < playerCiv.getNumOfProvinces(); ++p) {
                        if(money <= 0) break;

                        int pID = playerCiv.getProvinceID(p);

                        if(excluding && exclude.contains(pID)) {
                            buildingsSkipped++; continue;
                        }

                        Province countryProv = CFG.game.getProvince(pID);

                        int buildCost = aeCalcBCost(building, aeGetBLevel(building, countryProv) + 1, pID);

                        if ( (money - buildCost) <= 0 ) {
                            addMessage("Auto-Builder: Not enough money!");

                            buildingsSkipped += (playerCiv.getNumOfProvinces() - totalBuilt) - exclude.size();

                            break;
                        }

                        if(!aeBuildAvailable(building, playerCiv, countryProv)) { buildingsSkipped += 1; continue; }

                        aeConstruct(building, playerCiv, countryProv);

                        money -= buildCost; totalBuilt += 1;
                    }

                    CFG.gameAction.updateInGame_ProvinceInfo();

                    if(money > 0) moneyLeft = money;

                    if(totalBuilt == 0) playerCiv.setMovePoints(movePoints);

                    playerCiv.setMoney((long) playerCiv.getMoney() + moneyLeft);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    String message = String.format(
                        "%s Building; Spent: %d; Skipped: %d; Built: %d (out of possible %d); Money Left: %d",
                        building.substring(0,1).toUpperCase() + building.substring(1).toLowerCase(),
                        moneyAllocated - money, buildingsSkipped, totalBuilt, playerCiv.getNumOfProvinces() - exclude.size(), moneyLeft);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPAutoFestival") || tempCommand[0].equalsIgnoreCase("rpaf")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    final int moneyAllocated = Integer.parseInt(tempCommand[1]);

                    int money = Integer.parseInt(tempCommand[1]);

                    boolean excluding;

                    java.util.Set<Integer> exclude = aeProcExclusion(tempCommand[2]);

                    if(exclude == null) {
                        return;
                    } else if(exclude.size() == 0) {
                        excluding = false;
                    } else {
                        excluding = true;
                    }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    Civilization playerCiv = CFG.game.getCiv(playerCivID);

                    int festivalsAreHeld = 0, festivalsSkipped = 0, moneyLeft = 0;

                    if (moneyAllocated > playerCiv.getMoney()) {
                        addMessage("You have allocated more money than you have!");
                        addMessage("");

                        CFG.toast.setInView("You have allocated more money than you have!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int movePoints = playerCiv.getMovePoints();

                    playerCiv.setMoney((long) playerCiv.getMoney() - moneyAllocated);
                    playerCiv.setMovePoints(0);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    CFG.soundsManager.playSound(SoundsManager.SOUND_ASSIMILATE);

                    for(int p=0; p < playerCiv.getNumOfProvinces(); ++p) {
                        if(money <= 0) break;

                        int pID = playerCiv.getProvinceID(p);

                        if(excluding && exclude.contains(pID)) {
                            festivalsSkipped++; continue;
                        }

                        Province countryProv = CFG.game.getProvince(pID);

                        int festivalCost = DiplomacyManager.festivalCost(pID) * 2; // For ignoring game's movement points system

                        if ( (money - festivalCost) <= 0 ) {
                            addMessage("Auto-Festival: Not enough money!");

                            festivalsSkipped += (playerCiv.getNumOfProvinces() - festivalsAreHeld) - exclude.size();

                            break;
                        }

                        if(countryProv.getHappiness() == 1.0f) {
                            festivalsSkipped++; continue;
                        }

                        if(playerCiv.addFestival(new CivFestival(pID, 10))) {
                            festivalsAreHeld++;

                            money -= festivalCost;
                        } else {
                            festivalsSkipped++;
                        }
                    }

                    CFG.gameAction.updateInGame_ProvinceInfo();

                    if(money > 0) moneyLeft = money;

                    if(festivalsAreHeld == 0) playerCiv.setMovePoints(movePoints);

                    playerCiv.setMoney((long) playerCiv.getMoney() + moneyLeft);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    String message = String.format(
                        "Festival; Spent: %d; Skipped: %d; Festivals Are Held: %d (out of possible %d); Money Left: %d",
                        moneyAllocated - money, festivalsSkipped, festivalsAreHeld, playerCiv.getNumOfProvinces() - exclude.size(), moneyLeft);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPAutoDevelop") || tempCommand[0].equalsIgnoreCase("rpad")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    final int moneyAllocated = Integer.parseInt(tempCommand[1]);

                    int money = Integer.parseInt(tempCommand[1]);

                    boolean excluding;

                    java.util.Set<Integer> exclude = aeProcExclusion(tempCommand[2]);

                    if(exclude == null) {
                        return;
                    } else if(exclude.size() == 0) {
                        excluding = false;
                    } else {
                        excluding = true;
                    }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    Civilization playerCiv = CFG.game.getCiv(playerCivID);

                    int developed = 0, skipped = 0, moneyLeft = 0;

                    if (moneyAllocated > playerCiv.getMoney()) {
                        addMessage("You have allocated more money than you have!");
                        addMessage("");

                        CFG.toast.setInView("You have allocated more money than you have!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int moneyPerProvince = (int) moneyAllocated / (playerCiv.getNumOfProvinces() - exclude.size());

                    int movePoints = playerCiv.getMovePoints();

                    playerCiv.setMoney((long) playerCiv.getMoney() - moneyAllocated);
                    playerCiv.setMovePoints(0);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    CFG.soundsManager.playSound(SoundsManager.SOUND_LIBRARY);

                    for(int p=0; p < playerCiv.getNumOfProvinces(); ++p) {
                        if(money <= 0) break;

                        int pID = playerCiv.getProvinceID(p);

                        if(excluding && exclude.contains(pID)) {
                            skipped++; continue;
                        }

                        Province countryProv = CFG.game.getProvince(pID);

                        if(countryProv.getDevelopmentLevel() == playerCiv.getTechnologyLevel()) {
                            skipped++; continue;
                        }

                        float ecoPoints = DiplomacyManager.invest_DevelopmentByGold(pID, moneyPerProvince),
                              ecoPointsPerTurn = Math.max(ecoPoints / 8.0f, 1.0E-5f);

                        if(CFG.game.getCiv(playerCiv.getCivID()).addInvest_Development(new CivInvest_Development(pID, 8, ecoPoints, ecoPointsPerTurn))) {
                            developed++;

                            money -= moneyPerProvince;
                        } else {
                            skipped++;
                        }
                    }

                    CFG.gameAction.updateInGame_ProvinceInfo();

                    if(money > 0) moneyLeft = money;

                    if(developed == 0) playerCiv.setMovePoints(movePoints);

                    playerCiv.setMoney((long) playerCiv.getMoney() + moneyLeft);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    String message = String.format(
                        "Development; Per Province: %d; Spent: %d; Skipped: %d; Developed: %d (out of possible %d); Money Left: %d",
                        moneyPerProvince, moneyAllocated - money, skipped, developed, playerCiv.getNumOfProvinces() - exclude.size(), moneyLeft);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPAutoDE") || tempCommand[0].equalsIgnoreCase("rpade")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    int eFilter = Integer.parseInt(tempCommand[1]);

                    final int moneyAllocated = Integer.parseInt(tempCommand[2]);

                    int money = Integer.parseInt(tempCommand[2]);

                    boolean excluding;

                    java.util.Set<Integer> exclude = aeProcExclusion(tempCommand[3]);

                    if(exclude == null) {
                        return;
                    } else if(exclude.size() == 0) {
                        excluding = false;
                    } else {
                        excluding = true;
                    }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    Civilization playerCiv = CFG.game.getCiv(playerCivID);

                    int eDeveloped = 0, skipped = 0, moneyLeft = 0;

                    if (moneyAllocated > playerCiv.getMoney()) {
                        addMessage("You have allocated more money than you have!");
                        addMessage("");

                        CFG.toast.setInView("You have allocated more money than you have!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int moneyPerProvince = (int) moneyAllocated / (playerCiv.getNumOfProvinces() - exclude.size());

                    int movePoints = playerCiv.getMovePoints();

                    playerCiv.setMoney((long) playerCiv.getMoney() - moneyAllocated);
                    playerCiv.setMovePoints(0);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    CFG.soundsManager.playSound(SoundsManager.SOUND_LIBRARY);

                    for(int p=0; p < playerCiv.getNumOfProvinces(); ++p) {
                        if(money <= 0) break;

                        int pID = playerCiv.getProvinceID(p);

                        if(excluding && exclude.contains(pID)) {
                            skipped++; continue;
                        }

                        Province countryProv = CFG.game.getProvince(pID);

                        if(eFilter != 0 && countryProv.getEconomy() >= eFilter) {
                            skipped++; continue;
                        }

                        int ecoPoints = DiplomacyManager.invest_EconomyByGold(pID, moneyPerProvince),
                            ecoPointsPerTurn = Math.max(ecoPoints / 4, 1);

                        if(CFG.game.getCiv(playerCiv.getCivID()).addInvest(new CivInvest(pID, 10, ecoPoints, ecoPointsPerTurn))) {
                            eDeveloped++;

                            money -= moneyPerProvince;
                        } else {
                            skipped++;
                        }
                    }

                    CFG.gameAction.updateInGame_ProvinceInfo();

                    if(money > 0) moneyLeft = money;

                    if(eDeveloped == 0) playerCiv.setMovePoints(movePoints);

                    playerCiv.setMoney((long) playerCiv.getMoney() + moneyLeft);
                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    String message = String.format(
                        "Economy; Per Province: %d; Spent: %d; Skipped: %d; Developed: %d (out of possible %d); Money Left: %d",
                        moneyPerProvince, moneyAllocated - money, skipped, eDeveloped, playerCiv.getNumOfProvinces() - exclude.size(), moneyLeft);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPLoanInfo") || tempCommand[0].equalsIgnoreCase("rpli")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    if(tempCommand.length >= 3) {
                        int gold = Integer.parseInt(tempCommand[1]);
                        int tenure = Integer.parseInt(tempCommand[2]);

                        if(gold < 10000 || gold > leMaxLoan(playerCivID)) {
                            addMessage("Loan Info: You can't take this amount Gold; type RPLoanInfo to get acceptable gold amount per loan.");
                            addMessage("");

                            return;
                        }

                        if(tenure < 5 || tenure > 625) {
                            addMessage("Loan Info: Tenure is out of bounds; type RPLoanInfo to get acceptable tenure per loan.");
                            addMessage("");

                            return;
                        }

                        int interest = leInterest(playerCivID, gold, tenure);

                        addMessage("Type RPLoanInfo for generic loan information.");
                        addMessage(String.format(
                            "This loan (%d Gold) has %d%s interest (%d Gold) with %d tenure; Gold per turn: %d.",
                            gold, interest, "%", (gold * interest) / 100, tenure, Math.max(1, (gold + ((gold * interest) / 100)) / tenure)));
                        addMessage("");

                        return;
                    }

                    // Weird order
                    addMessage("Type RPLoanInfo [GOLD] [TENURE] for specific loan information.");
                    addMessage(String.format("Maximal loans limit for this country: %d.", leMaxLoans(playerCivID)));
                    addMessage(String.format(
                        "Minimal interest per minimal gold and tenure: %d%s, Maximal interest per maximal gold and tenure: %d%s.",
                        leInterest(playerCivID, 10000, 5), "%", leInterest(playerCivID, leMaxLoan(playerCivID), 625), "%"));
                    addMessage("Minimal tenure: 5 turns/5 months, Maximal tenure: 625 turns/30 years.");
                    addMessage(String.format("Minimal gold per loan: 10.000, Maximal gold per loan: %d.", leMaxLoan(playerCivID)));
                    addMessage("");

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("RPLoan") || tempCommand[0].equalsIgnoreCase("rpl")) {
                    if(CFG.SPECTATOR_MODE) { addMessage("You are in spectator mode."); addMessage(""); return; }

                    int playerCivID = CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID();

                    Civilization playerCiv = CFG.game.getCiv(playerCivID);

                    int gold = Integer.parseInt(tempCommand[1]);
                    int tenure = Integer.parseInt(tempCommand[2]);

                    if(gold <= 0 || gold > leMaxLoan(playerCivID) || tenure < 5 || tenure > 625) {
                        addMessage("Bank: Fill in the correct amount Gold and tenure! Type RPLoanInfo to get loan information.");
                        addMessage("");

                        CFG.toast.setInView("Bank: Fill in the correct amount Gold and tenure! Type RPLoanInfo to get loan information.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    if(playerCiv.getLoansSize() >= leMaxLoans(playerCivID)) {
                        addMessage("Bank: Refused; Too many loans for the country.");
                        addMessage("");

                        CFG.toast.setInView("Bank: Refused; Too many loans for the country.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    int interestPercent = leInterest(playerCivID, gold, tenure);

                    int goldPerTurn = Math.max(1, (gold + ((gold * interestPercent) / 100)) / tenure);

                    playerCiv.setMoney(playerCiv.getMoney() + (long) gold);
                    playerCiv.addLoan(goldPerTurn, tenure);
                    playerCiv.setMovePoints(playerCiv.getMovePoints() - 4);

                    CFG.menuManager.updateInGame_TOP_All(playerCivID);

                    String message = String.format(
                        "Bank: Approved; %d Gold handed out with %d%s interest over %d months; Repayment per turn: %d Gold.",
                        gold, interestPercent, "%", tenure, goldPerTurn);

                    addMessage(message);
                    addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                    return;
                }

                if (tempCommand[0].equalsIgnoreCase("pid")) {
                    int activeProvinceID = CFG.game.getActiveProvinceID();

                    if (activeProvinceID >= 0 && !CFG.game.getProvince(activeProvinceID).getSeaProvince() && CFG.game.getProvince(activeProvinceID).getWasteland() < 0) {
                        Province activeProvince = CFG.game.getProvince(activeProvinceID);

                        addMessage(String.format("%s, %s: %s", CFG.game.getCiv(activeProvince.getCivID()).getCivName(), activeProvince.getName(), activeProvinceID));
                        addMessage("");
                    } else {
                        addMessage("Choose a valid province!");
                        addMessage("");

                        CFG.toast.setInView("Choose a valid province!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    return;
                }

                if (!tempCommand[0].equals("reloadprovince")) break block118;
                try {
                    int tempID = Integer.parseInt(tempCommand[1]);
                    if (tempID < CFG.game.getProvincesSize()) {
                        Editor_NeighboringProvinces.updateProvince(tempID);
                        CFG.game.setActiveProvinceID(tempID);
                        CFG.toast.setInView(CFG.game.getProvince(tempID).getName());
                    } else {
                        Commands.IllegalCommand();
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    addMessage(ex.getMessage());
                }
                catch (IndexOutOfBoundsException ex) {
                    addMessage(ex.getMessage());
                }
                return;
            }
            catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (NumberFormatException ex) {
                CFG.exceptionStack(ex);
            }
            catch (IllegalArgumentException ex) {
                CFG.exceptionStack(ex);
            }
        }
        Commands.IllegalCommand();
    }

    //#region Automatization Expansion
    private static final java.util.Set<Integer> aeProcExclusion(String exclusionParam) {
        java.util.Set<Integer> exclude = new java.util.HashSet<>();


        if(exclusionParam.equalsIgnoreCase("none") || exclusionParam.equals("0")) {
            return exclude;
        } else {
            String[] parts = exclusionParam.split(",");

            for (String part : parts) {
                try {
                    exclude.add(Integer.parseInt(part));
                } catch (NumberFormatException nfe) {
                    addMessage(String.format("Invalid province ID for excluding: %s", part));
                    addMessage("");

                    CFG.toast.setInView(String.format("Invalid province ID for excluding: %s", part), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                    return null;
                }
            }

            return exclude;
        }
    }

    private static final boolean aeBuildAvailable(String type, Civilization pCiv, Province cProv) {
    	float buildingTechLevel;
    	int buildingMaxLevel;

    	switch(type){
    		case "fortress": { buildingTechLevel=BuildingsManager.getFort_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "tower": { buildingTechLevel=BuildingsManager.getTower_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "farm": { buildingTechLevel=BuildingsManager.getFarm_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "workshop": { buildingTechLevel=BuildingsManager.getWorkshop_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "library": { buildingTechLevel=BuildingsManager.getLibrary_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "armoury": { buildingTechLevel=BuildingsManager.getArmoury_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		case "supply": { buildingTechLevel=BuildingsManager.getSupply_TechLevel(aeGetBLevel(type, cProv) + 1); break; }
    		default: { buildingTechLevel = 0; break; }
    	}

    	switch(type){
    		case "fortress": { buildingMaxLevel=BuildingsManager.getFort_MaxLevel(); break; }
    		case "tower": { buildingMaxLevel=BuildingsManager.getTower_MaxLevel(); break; }
    		case "farm": { buildingMaxLevel=BuildingsManager.getFarm_MaxLevel(); break; }
    		case "workshop": { buildingMaxLevel=BuildingsManager.getWorkshop_MaxLevel(); break; }
    		case "library": { buildingMaxLevel=BuildingsManager.getLibrary_MaxLevel(); break; }
    		case "armoury": { buildingMaxLevel=BuildingsManager.getArmoury_MaxLevel(); break; }
    		case "supply": { buildingMaxLevel=BuildingsManager.getSupply_MaxLevel(); break; }
    		default: { buildingMaxLevel = 0; break; }
    	}

		return aeGetBLevel(type, cProv) < buildingMaxLevel && pCiv.getTechnologyLevel() >= buildingTechLevel;
    }

    private static final int aeCalcBCost(String type, int level, int pID) {
    	int cost;

    	switch(type){
    		case "fortress": { cost=BuildingsManager.getFort_BuildCost(level, pID); break; }
    		case "tower": { cost=BuildingsManager.getTower_BuildCost(level, pID); break; }
    		case "farm": { cost=BuildingsManager.getFarm_BuildCost(level, pID); break; }
    		case "workshop": { cost=BuildingsManager.getWorkshop_BuildCost(level, pID); break; }
    		case "library": { cost=BuildingsManager.getLibrary_BuildCost(level, pID); break; }
    		case "armoury": { cost=BuildingsManager.getArmoury_BuildCost(level, pID); break; }
    		case "supply": { cost=BuildingsManager.getSupply_BuildCost(level, pID); break; }
    		default: { cost = 0; break; }
    	}

		return (int) Math.round(cost * 1.15); // 15% fee for ignoring game's movement points
    }

    private static final int aeGetBLevel(String type, Province cProv) {
    	int level;

    	switch(type){
    		case "fortress": { level=cProv.getLevelOfFort(); break; }
    		case "tower": { level=cProv.getLevelOfWatchTower(); break; }
    		case "farm": { level=cProv.getLevelOfFarm(); break; }
    		case "workshop": { level=cProv.getLevelOfWorkshop(); break; }
    		case "library": { level=cProv.getLevelOfLibrary(); break; }
    		case "armoury": { level=cProv.getLevelOfArmoury(); break; }
    		case "supply": { level=cProv.getLevelOfSupply(); break; }
    		default: { level = -1; break; }
    	}

    	return level;
    }

	private static final void aeConstruct(String type, Civilization pCiv, Province cProv) {
		switch(type) {
			case "fortress": {
                pCiv.addNewConstruction(new Construction_GameData_Fort(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "tower": {
                pCiv.addNewConstruction(new Construction_GameData_Tower(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "farm": {
                pCiv.addNewConstruction(new Construction_GameData_Farm(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "workshop": {
                pCiv.addNewConstruction(new Construction_GameData_Workshop(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "library": {
                pCiv.addNewConstruction(new Construction_GameData_Library(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "armoury": {
                pCiv.addNewConstruction(new Construction_GameData_Armoury(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
			case "supply": {
                pCiv.addNewConstruction(new Construction_GameData_Supply(cProv.getProvinceID(), aeGetBLevel(type, cProv) + 1));
                break;
			}
		}
	}
    //#endregion

    //#region Loan Expansion
    private static final int leMaxLoan(int civID) {
        Civilization civ = CFG.game.getCiv(civID);

        return (int) Math.min(9_999_999, Math.max(1000,
            ((civ.iIncomeTaxation + civ.iIncomeProduction) * 80f
            + Math.pow(civ.countEconomy(), 0.55) * (0.5f + civ.getTechnologyLevel() * 1.5))
        ));
    }

    private static final int leInterest(int civID, int gold, int tenure) {
        Civilization civ = CFG.game.getCiv(civID);

        float cWealth = (civ.iIncomeTaxation + civ.iIncomeProduction) * 0.5f
                        + civ.countEconomy() * 0.5f
                        + civ.getTechnologyLevel() * 50f;

        float annualRate = Math.max(0.02f, 0.08f - Math.min(0.06f, cWealth / 200000f));

        return (int) Math.min(600, Math.round((Math.pow(1 + (annualRate / 12f), tenure) - 1) * 100) + 1);
    }

    private static final int leMaxLoans(int civID) {
        Civilization civ = CFG.game.getCiv(civID);

        float cWealth = (civ.iIncomeTaxation + civ.iIncomeProduction)
                        + civ.countEconomy() * 0.5f
                        + civ.getTechnologyLevel() * 50f;

        int[] thresholds = {25_000, 50_000, 100_000, 200_000, 300_000, 400_000, 500_000, 600_000, 700_000, 800_000};
        int[] loans =      {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

        for (int tI = thresholds.length - 1; tI >= 0; tI--) {
            if (cWealth >= thresholds[tI]) return loans[tI];
        }

        return 3;
    }
    //#endregion

    private static final String cheatMess() {
        return "[" + CFG.langManager.get("Cheat") + "] ";
    }

    private static final void IllegalCommand() {
        Commands.addMessage("# -- " + CFG.langManager.get("UnknownCommand"));
        CFG.toast.setInView("# -- " + CFG.langManager.get("UnknownCommand"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        Commands.addMessage("");
    }
}
