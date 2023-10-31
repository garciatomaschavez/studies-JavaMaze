import java.util.Arrays;

public class Map
{
    Options options = new Options();

    // almacena el CARACTER ascii a mostrar en cada field
    private String[][] worldMapVisual;

    // almacena el VALOR al que equivale el caracter a mostrar en cada field
    private int[][] worldMapValue;

    // almacena los POSIBLES valores que puede tener cada field
    private int[][][] worldMapOptions;

    // booleano que marca si se ha hecho el primer paso de la generación (asignar valor a 1 casilla aleatoria)
    private boolean generated = false;

    // constructor
    public Map()
    {
        // los creamos con una longitud [y][x] iguales a las propiedades LENGTH y WIDTH guardadas en la clase options
        this.worldMapVisual = new String[options.length][options.width];

        this.worldMapValue = new int[options.length][options.width];

        // en este también hacemos que la tercera dimension sea un array de 12 posiciones siempre (12 posibles valores
        // por casilla)
        this.worldMapOptions = new int[options.length][options.width][12];

        // recorro el worldMapOptions y a cada array en X le meto los valores del 1 al 12, que son (al principio), los
        // posibles valores hasta que se le asigne uno a la primera casilla aleatoria
        for (int y=0; y < worldMapOptions.length; y++)
        {
            for (int x=0; x < worldMapOptions[y].length; x++)
            {
                int cont = 1;
                for (int z=0; z < worldMapOptions[y][x].length; z++)
                {
                    worldMapOptions[y][x][z] = cont;
                    cont++;
                }
            }
        }
    }

    // escoge la primera casilla aleatoria de la que partirá toda la generación
    public void calcInitial()
    {
        // generamos coordenada Y aleatoria
        int randLength = (int) (Math.random() * (this.options.length));
        // generamos coordenada X aleatoria
        int randWidth = (int) (Math.random() * (this.options.width));

        int randValue = 1 + (int)(Math.random() * ((12 - 1) + 1));
        // a ese field aleatorio le vamos a asignar un valor aleatorio (es decir, ahora va a ser representado de x manera)
        worldMapValue[randLength][randWidth] = randValue;

        // también vamos a poner que sus opciones de valores son solo 1, la que tiene asignada
        worldMapOptions[randLength][randWidth] = Arrays.copyOf(worldMapOptions[randLength][randWidth], 1);
        worldMapOptions[randLength][randWidth][0] = randValue;

        // marcamos que ahora el mapa es representable porque ya hemos empezado el proceso de generación
        this.generated = true;
    }

    // dibuja el mapa en pantalla
    public void showMap()
    {
        // solo si ya se ha llevado a cabo el primer paso de la generación
        if (this.generated)
        {

            for(int y = 0; y < this.worldMapValue.length; y++)
            {
                for(int x = 0; x < this.worldMapValue[y].length; x++)
                {
                    // realiza la traducción entre el valor numérico asignado en worldMapValue al tile asignado
                    worldMapVisual[y][x] = obtainTileset(worldMapValue[y][x]);

                    System.out.print(worldMapVisual[y][x]);
                }
                System.out.print("\n");
            }
        }
    }


    public String obtainTileset(int tileValue)
    {
        switch (tileValue)
        {
            case 1:
                return "╔";
            case 2:
                return "╗";
            case 3:
                return "╚";
            case 4:
                return "╝";
            case 5:
                return "╠";
            case 6:
                return "╣";
            case 7:
                return "╦";
            case 8:
                return "╩";
            case 9:
                return "╬";
            case 10:
                return "║";
            case 11:
                return "═";
            case 12:
                return " ";
            default:
                return "?";
        }
    }


    // check los alrededores de la casilla con las coordenadas indicadas en los parametros
    public int checkSurroundings(int y, int x)
    {

    }

    /*

    public String characterLogic(String[] previousValue){

    }
    */

    public void updateMap()
    {
        int rand;
        for(int i=0; i < this.worldMapVisual.length; i++)
        {
            for(int j= 0; j < this.worldMapVisual[i].length; j++)
            {
                worldMapVisual[i][j] = obtainTileset(worldMapValue[i][j]);
                // this.worldMapVisual[i][j] = DEBUG_calcRandomChar();
            }
        }
    }


    public String DEBUG_calcRandomChar()
    {
        int rand = 1 + (int)(Math.random() * ((12 - 1) + 1));
        switch (rand)
        {
            case 1:
                return "╔";
            case 2:
                return "╗";
            case 3:
                return "╚";
            case 4:
                return "╝";
            case 5:
                return "╠";
            case 6:
                return "╣";
            case 7:
                return "╦";
            case 8:
                return "╩";
            case 9:
                return "╬";
            case 10:
                return "║";
            case 11:
                return "═";
            case 12:
                return " ";
            default:
                return "?";
        }
    }

    public int calcRandom()
    {
        return 1 + (int)(Math.random() * ((12 - 1) + 1));
    }

}
