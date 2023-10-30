public class Map {
    Options options = new Options();

    private String[][] worldMapVisual;
    private int[][] worldMapValue;
    private int[][][] worldMapOptions;
    private boolean generated = false;

    public Map() {
        this.worldMapVisual = new String[options.length][options.width];
        this.worldMapValue = new int[options.length][options.width];
        this.worldMapOptions = new int[options.length][options.width][12];
    }

    public void calcInitial() {
        int randLength = (int) (Math.random() * (this.options.length));
        int randWidth = (int) (Math.random() * (this.options.width));
        worldMapValue[randLength][randWidth] = 1+(int)(Math.random()*((12-1)+1));
        generated = true;
    }

    public void showMap() {
        if (this.generated) {
            for(int y = 0; y < this.worldMapValue.length; y++) {
                for(int x = 0; x < this.worldMapValue[y].length; x++) {
                    worldMapVisual[y][x] = obtainTileset(worldMapValue[y][x]);

                    System.out.print(worldMapVisual[y][x]);
                }
                System.out.print("\n");
            }
        }
    }


    public String obtainTileset(int tileValue) {
        return switch (tileValue) {
            case 1 -> "╔";
            case 2 -> "╗";
            case 3 -> "╚";
            case 4 -> "╝";
            case 5 -> "╠";
            case 6 -> "╣";
            case 7 -> "╦";
            case 8 -> "╩";
            case 9 -> "╬";
            case 10 -> "║";
            case 11 -> "═";
            case 12 -> " ";
            default -> "?";
        };
    }


    /*
    public obtainSurroundings(int index) {
        index
    }

    public String characterLogic(String[] previousValue){

    }
    */

    public void updateMap() {
        int rand;
        for(int i=0; i < this.worldMapVisual.length; i++) {
            for(int j= 0; j < this.worldMapVisual[i].length; j++) {


                worldMapVisual[i][j] = obtainTileset(worldMapValue[i][j]);
                // this.worldMapVisual[i][j] = DEBUG_calcRandomChar();
            }
        }
    }


    public String DEBUG_calcRandomChar() {
        int rand = 1+(int)(Math.random()*((12-1)+1));
        return switch (rand) {
            case 1 -> "╔";
            case 2 -> "╗";
            case 3 -> "╚";
            case 4 -> "╝";
            case 5 -> "╠";
            case 6 -> "╣";
            case 7 -> "╦";
            case 8 -> "╩";
            case 9 -> "╬";
            case 10 -> "║";
            case 11 -> "═";
            case 12 -> " ";
            default -> "!";
        };
    }

    public int calcRandom() {
        return 1+(int)(Math.random()*((12-1)+1));
    }

}
