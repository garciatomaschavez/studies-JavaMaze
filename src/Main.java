public class Main {
    public static void main(String[] args) {
        MenuScreens screen = new MenuScreens();
        Map map = new Map();

        map.calcInitial();
        map.updateMap();
        map.showMap();


        // screen.mainMenu();

    }
}