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

                        String message = String.format("%s now owns province of %s.", CFG.game.getCiv(civID).getCivName(), CFG.game.getProvince(CFG.game.getActiveProvinceID()).getName());

                        addMessage(message);
                        addMessage("");

                        CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_POSITIVE);

                        return;
                    }

                    addMessage("Select any province.");
                    addMessage("");

                    CFG.toast.setInView("Select any province.", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                    return;
                }

                if (tempCommand[0].equals("addmoney")) {
                    long moneyToAdd;

					// Why not
                    try {
                        moneyToAdd = Long.parseLong(tempCommand[1]);
                    } catch (NumberFormatException nfe) {
                        addMessage("Expected numbers!");
                        addMessage("");

                        	CFG.toast.setInView("Expected numbers!", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

                        return;
                    }

                    CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).setMoney(CFG.game.getCiv(CFG.game.getPlayer(CFG.PLAYER_TURNID).getCivID()).getMoney() + moneyToAdd);

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
                        Commands.addMessage("Invalid ideology!");
                        Commands.addMessage("");

                        	CFG.toast.setInView("Invalid ideology1", CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);

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

                    Commands.addMessage(message);
                    Commands.addMessage("");

                    CFG.toast.setInView(message, CFG.COLOR_TEXT_MODIFIER_NEUTRAL);

                    Commands.addMessage("Warning: possible minor glitches with colors/text;");
                    Commands.addMessage("");

                    return;
                }

                if (!CFG.SPECTATOR_MODE && tempCommand[0].equals("removeplayer")) {
                		if (CFG.game.getPlayersSize() > 1) {
                			String order = tempCommand[1];

                        if(order.equalsIgnoreCase("first")) {
                            if(CFG.PLAYER_TURNID == 0) { 
                                CFG.PLAYER_TURNID = CFG.game.getPlayersSize() - 1;
                            }

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

						                    return;
                            } catch(java.lang.NoSuchFieldException | java.lang.IllegalAccessException r) {
                                addMessage("Reflection error.");
                                addMessage("");

	                            return;
                            }
                        } else {
                			    int last = CFG.game.getPlayersSize() - 1;

                			    if(CFG.PLAYER_TURNID == last) { addMessage("Can't remove the playing player! Skip the turn!"); addMessage(""); return; }

                			    CFG.game.removePlayer(last);

                			    addMessage("Removed the last player!");
                			    addMessage("");

		                    return;
                        }
                		} else {
                			addMessage("Can't remove the only one player!");
                			addMessage("");

                			return;
                		}
                	}

                    // maybe later
                	if (tempCommand[0].equals("revolt")) {
                                        		//addMessage("In testing");
                		//addMessage("");
                		String countryID = tempCommand[1];

                    if (CFG.game.getActiveProvinceID() < 0) {
                        Commands.addMessage("Click on country's province");
                        Commands.addMessage("");
                        return;
                    }

Province province = CFG.game.getProvince(CFG.game.getActiveProvinceID());

String prevOwnerName = CFG.game.getCiv(province.getCivID()).getCivName();

int prevCivID = province.getCivID();

if (prevCivID <= 0) {
    addMessage("Invalid province for revolt.");
    addMessage("");
    return;
}

CFG.game.createScenarioAddCivilization(countryID, CFG.game.getActiveProvinceID(), false, true, true);

CFG.game.getCiv(CFG.game.getActiveProvinceID()).setMoney(50000L);

if (CFG.FOG_OF_WAR == 2) {
	for (int i = 0; i < CFG.game.getPlayersSize(); ++i) {
		CFG.game.getPlayer(i).addMetCivilization(true);
	}
}

int tempPop = CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().getPopulation();

CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().clearData();
CFG.game.getProvince(CFG.game.getActiveProvinceID()).getPopulationData().setPopulationOfCivID(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), tempPop);
CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setMoney(10000L);
CFG.gameAction.updateCivsMovementPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
CFG.gameAction.updateCivsDiplomacyPoints(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());
CFG.gameAction.buildRank_Score(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID());

CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() - CFG.game.getProvince(CFG.game.getActiveProvinceID()).getArmy(0));
CFG.game.getProvince(CFG.game.getActiveProvinceID()).updateArmy(7500);
CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).setNumOfUnits(CFG.game.getCiv(CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID()).getNumOfUnits() + 10000);

CFG.game.declareWar(prevCivID, CFG.game.getProvince(CFG.game.getActiveProvinceID()).getCivID(), true);

int tActiveProvince = CFG.game.getActiveProvinceID();
CFG.game.setActiveProvinceID(-1);
CFG.game.setActiveProvinceID(tActiveProvince);

addMessage(String.format("%s (%s) is now revolting against %s!", CFG.game.getCiv(CFG.game.getProvince(tActiveProvince).getCivID()).getCivName(), CFG.ideologiesManager.getIdeology(CFG.game.getCiv(CFG.game.getProvince(tActiveProvince).getCivID()).getIdeologyID()).getName(), prevOwnerName));
addMessage("");
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

    private static final String cheatMess() {
        return "[" + CFG.langManager.get("Cheat") + "] ";
    }

    private static final void IllegalCommand() {
        Commands.addMessage("# -- " + CFG.langManager.get("UnknownCommand"));
        CFG.toast.setInView("# -- " + CFG.langManager.get("UnknownCommand"), CFG.COLOR_TEXT_MODIFIER_NEGATIVE2);
        Commands.addMessage("");
    }
}
