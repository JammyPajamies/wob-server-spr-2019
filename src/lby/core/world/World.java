package lby.core.world;

// Java Imports
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;
import lby.core.Lobby;
import lby.core.LobbyController;

import shared.core.GameResources;
import shared.core.ServerResources;
import lby.db.world.WorldDAO;
import lby.db.world.WorldZoneDAO;
import lby.net.response.ResponseSpeciesCreate;
import shared.metadata.Constants;
import shared.model.Player;
import shared.model.SpeciesType;
import lby.net.response.shop.ResponseShopAction;
import shared.core.GameEngine;
import shared.db.EcoSpeciesDAO;
import shared.db.StatsDAO;
import shared.db.EcosystemDAO;
import shared.db.SpeciesChangeListDAO;
import shared.model.Ecosystem;
import shared.model.Species;
import shared.model.SpeciesGroup;
import shared.util.Clock;
import shared.util.EventListener;
import shared.util.EventType;
import shared.util.GameTimer;
import shared.util.Log;
import shared.util.NetworkFunctions;
import shared.util.Vector3;

public class World {

    private final int CLOCK_UPDATE_CYCLE = 1000 * 60 * 60;   // Update clock (event triggers) every hour
    // Variables
    private final int world_id;
    private String name;
    private short type;
    private float time_rate = 1.0f;
    private int day = 1;
    // Other
    private List<Zone> zoneList;
    private final Map<Integer, Player> playerList = new HashMap<Integer, Player>();
    private final Map<Integer, Integer> shopList = new HashMap<Integer, Integer>();
    private final GameTimer worldTimer = new GameTimer();
    private final GameTimer shopTimer = new GameTimer();
    private final Clock clock;
    private GameEngine gameEngine;

    public World(int world_id, String name, short type, float time_rate, int day) {
        this.world_id = world_id;
        this.name = name;
        this.type = type;
        this.time_rate = time_rate;
        // this.day = day;
        day = SpeciesChangeListDAO.getDay();

        clock = new Clock(day, time_rate * Constants.TIME_MODIFIER);
        createClockEvents();

        worldTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                clock.run();
           }
        }, 1000, CLOCK_UPDATE_CYCLE);
    }

    private void createClockEvents() {
        // Update Day
        clock.createEvent(EventType.NEW_DAY, new EventListener() {
            @Override
            public void run(Object... args) {
                // day = (Integer) args[0];

                // Update Time Every 5 Days
                day = SpeciesChangeListDAO.getDay();
                if (day % 5 == 0) {
                    WorldDAO.updateDay(world_id, day);
                }
            }
        });
    }

    public int getID() {
        return world_id;
    }

    public String getName() {
        return name;
    }
    
    public short getType() {
        return type;
    }

    public float getTimeRate() {
        return time_rate;
    }

    public float setTimeRate(float time_rate) {
        return this.time_rate = time_rate;
    }

    public int getDay() {
        return SpeciesChangeListDAO.getDay();
    }

    // Presently this is disabled
    // day = simulation number
    public int setDay(int day) {
        return SpeciesChangeListDAO.getDay();
        // return this.day = day;
    }
    
    public List<Zone> getZones() {
        return zoneList;
    }
    
    public List<Zone> setZones(List<Zone> zoneList) {
        return this.zoneList = zoneList;
    }
    
    public List<Zone> getZoneList() {
        return zoneList;
    }

    public Clock getClock() {
        return clock;
    }

    public Map<Integer, Player> getPlayers() {
        return playerList;
    }
    
    public boolean hasPlayer(int player_id) {
        return playerList.containsKey(player_id);
    }

    public void add(Player player) {
        playerList.put(player.getID(), player);
    }

    public void remove(int player_id) {
        playerList.remove(player_id);
    }
    
    public void updateClock() {
        clock.run();
    }
    

    /**
     * Create new and merge existing purchases until a given time frame is up.
     *
     * @param itemList
     * @param player
     * @return
     */
    public int createShopOrder(Map<Integer, Integer> itemList, Player player, int totalCost) {
        Log.println("Player [" + player.getName() + "] is requesting for a shop order.");
        // int totalCost = 0;
        int newCredits;

        // Determine the total cost of purchase
        for (int item_id : itemList.keySet()) {
            SpeciesType species = ServerResources.getSpeciesTable().getSpecies(item_id);
            // List<SpeciesType> speciesArray = ServerResources.getSpeciesTable().getSpecies();  // DH commented out. Why here?                      
            Log.println("item_id = " + item_id);
            if (species != null) {
                int biomass = itemList.get(item_id);
                // totalCost += species.getCost() * Math.ceil(biomass / species.getBiomass()); //if species has a low biomass per unit, the price will be very high
                Log.println("biomass: " + biomass);
            } else {
                Log.println("Error: Could not find item_id");
                return -1;
            }
        }       
  
        Log.println("total cost before: " + totalCost);
        Log.println("player credits: " + player.getCredits());
        if (totalCost > player.getCredits()) {
            totalCost = player.getCredits();
        }
        if (GameResources.useCredits(player, totalCost)) {
             //LobbyController.getInstance().getLobby(this).getEventHandler().execute(EventTypes.SPECIES_BOUGHT, itemList.size());

            int totalBiomass = 0;
            for (int item_id : itemList.keySet()) {
                SpeciesType species = ServerResources.getSpeciesTable().getSpecies(item_id);

                if (species != null) {
                    totalBiomass += itemList.get(item_id);
                }
            }
            //LobbyController.getInstance().getLobby(this).getEventHandler().execute(EventTypes.BIOMASS_BOUGHT, totalBiomass);

                        // Insert these item values into the hashmap
            for (int item_id : itemList.keySet()) {
                int amount = itemList.get(item_id);
                // New item
                if (shopList.containsKey(item_id)) {
                    amount += shopList.get(item_id);
                }
                shopList.put(item_id, amount);
            }
            
            // Create a new timer, if none exist.
            if (shopTimer.getTask() == null || shopTimer.getTimeRemaining() <= 0) {
                // Timer Declaration Start
                final World world_f = this;
                Log.consoleln("world " + world_f);
                Log.consoleln("timer " + shopTimer.getTimeElapsed());
                Log.println("player_id: " + player.getID());
                final Player player_f = player;
                world_f.processShopOrder(player_f);
//                shopTimer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        world_f.processShopOrder(player_f);
//                    }
//                }, Date.from(Instant.now()));
                // End
            }
            newCredits = player.getCredits();
        } else {
            newCredits = -1;
        }
        
        Log.println("Order has been placed! new credits balance = " + newCredits);
        return newCredits;
    }

    /**
     * Processes all pending purchases.
     * @param player
     */
    public void processShopOrder(Player player) {
        // Retrieve starting Zone
        Log.println("player:\t" + player);
        Ecosystem ecosystem = player.getEcosystem();
        Log.println("eco: " + ecosystem);
        if (ecosystem == null) {
            ecosystem = EcosystemDAO.getEcosystem(getID(), player.getID());
            player.setEcosystem(ecosystem);
            Log.println("eco from EcosystemDAO: " + ecosystem);            
        }        
        
        if(ecosystem == null)
        {
            short type = 1;
            ecosystem = new Ecosystem(player.getAccountID(),getID(),player.getID(),player.getName(),type);
            player.setEcosystem(ecosystem);
     
        }
        Log.println("eco after: " + ecosystem);
        Log.println("eco id: " + ecosystem.getID());
        Log.println("shoplist: " + shopList);
        
        World world = this;
        //Lobby lobby = ;
        
        //gameEngine is null here
        //gameEngine = GameEngine(lobby, world, ecosystem);
        createSpeciesByPurchase(player, shopList, ecosystem);
   //     gameEngine.forceSimulation();
        
        Log.println("process order");
        String tempList = "";

        int index = 0;
        for (Entry<Integer, Integer> entry : shopList.entrySet()) {
            tempList += entry.getKey() + ":" + entry.getValue();

            if (index++ < shopList.size() - 1) {
                tempList += ",";
            }
        }
        

//        ResponseShopAction response = new ResponseShopAction();
//        response.setStatus(2);
//        response.setItems(tempList);
//        NetworkFunctions.sendToPlayer(response, player.getID());

        shopList.clear();
    }
    
     public void createSpeciesByPurchase(Player player, Map<Integer, Integer> speciesList, Ecosystem ecosystem) {
        for (Entry<Integer, Integer> entry : speciesList.entrySet()) {
            int species_id = entry.getKey(), biomass = entry.getValue();
            SpeciesType speciesType = ServerResources.getSpeciesTable().getSpecies(species_id);

            for (int node_id : speciesType.getNodeList()) {
            	ecosystem.setNewSpeciesNode(node_id, biomass);
            }

            Species species = null;

            if (ecosystem.containsSpecies(species_id)) {
                species = ecosystem.getSpecies(species_id);
                int biomassPrev = EcoSpeciesDAO.getSpeciesBiomass(ecosystem.getID(), species_id);
                // DH - biomass was not being updated, so divided by number of groups & added
                int size = species.getGroups().size();
                for (SpeciesGroup group : species.getGroups().values()) {
                    EcoSpeciesDAO.updateBiomass(ecosystem.getID(), group.getID(), species_id, (biomassPrev + biomass)/size);
                    group.setBiomass((biomassPrev + biomass)/size);
//                    if(!Constants.DEBUG_MODE){
//	                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
//	                    NetworkFunctions.sendToLobby(response, lobby.getID());
//                    }
                }                
            } else {
                    int group_id = EcoSpeciesDAO.createSpecies(ecosystem.getID(), species_id, biomass);
                    species = new Species(species_id, speciesType);
                    SpeciesGroup group = new SpeciesGroup(species, group_id, biomass, Vector3.zero);
                    species.add(group);
//                    if(!Constants.DEBUG_MODE){
//	                    ResponseSpeciesCreate response = new ResponseSpeciesCreate(Constants.CREATE_STATUS_DEFAULT, ecosystem.getID(), group);
//	                    NetworkFunctions.sendToLobby(response, lobby.getID());
//                    }
            }
            SpeciesChangeListDAO.createEntry(ecosystem.getID(), species_id, biomass, SpeciesChangeListDAO.getDay()); 
            // Log.println("Added to ecosystem, id: " + species.getID());
            // Log.println("biomass: " + species.getTotalBiomass());
            ecosystem.addSpecies(species);
        }
    }
     /**
     * Create new and merge existing purchases until a given time frame is up.
     *
     * @param zone_id
     * @param player
     * @return
     */
    public int createTilesByPurchase(Integer zone_id, Player player) throws SQLException {
        Log.println("Player [" + player.getName() + "] is requesting for a tile order.");
        int totalCost = 0;

        // Determine the total cost of purchase
        totalCost = (int) (WorldZoneDAO.getCarryingCapacity(zone_id)*100);
        
  
        Log.println("total cost before: " + totalCost);
        Log.println("player credits: " + player.getCredits());
        if (GameResources.useCredits(player, totalCost)) {
             //LobbyController.getInstance().getLobby(this).getEventHandler().execute(EventTypes.SPECIES_BOUGHT, itemList.size());
            WorldZoneDAO.updateOwner(player.getAccountID(), zone_id);
//            int totalBiomass = 0;
//            for (int item_id : itemList.keySet()) {
//                SpeciesType species = ServerResources.getSpeciesTable().getSpecies(item_id);
//
//                if (species != null) {
//                    totalBiomass += itemList.get(item_id);
//                }
//            }
//            //LobbyController.getInstance().getLobby(this).getEventHandler().execute(EventTypes.BIOMASS_BOUGHT, totalBiomass);
//
//                        // Insert these item values into the hashmap
//            for (int item_id : itemList.keySet()) {
//                int amount = itemList.get(item_id);
//                // New item
//                if (shopList.containsKey(item_id)) {
//                    amount += shopList.get(item_id);
//                }
//                shopList.put(item_id, amount);
//            }
//            
//           
//                
//            // Create a new timer, if none exist.
//            if (shopTimer.getTask() == null || shopTimer.getTimeRemaining() <= 0) {
//                // Timer Declaration Start
//                final World world_f = this;
//                Log.consoleln("world " +world_f);
//                Log.consoleln("timer " +shopTimer.getTimeElapsed());
//                final Player player_f = player;
//                world_f.processShopOrder(player_f);
////                shopTimer.schedule(new TimerTask() {
////                    @Override
////                    public void run() {
////                        world_f.processShopOrder(player_f);
////                    }
////                }, Date.from(Instant.now()));
//                // End
//            }
//            

        } else {
            totalCost = -1;
        }
        
        Log.println("Order has been placed! Total cost = " + totalCost);
        return totalCost;
    }
}
