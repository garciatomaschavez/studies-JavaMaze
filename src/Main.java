public class Main
{
    public static void main(String[] args)
    {
        MenuScreens screen = new MenuScreens();
        Map map = new Map();

        for (int i=1; i < (map.options.width * map.options.length); i++)
        {
            map.logic.updateMapOptions(map);
            map.logic.updateTile(map, map.logic.findCandidate(map, map.logic.shortestOptionRange(map)));
            map.updateMap();
            map.showMap();
            System.out.println("\n\n\n\n\n");

        }


        // screen.mainMenu();
    }
}