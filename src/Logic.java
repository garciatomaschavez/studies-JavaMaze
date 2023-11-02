import java.util.Arrays;

public class Logic
{
    // realiza la conversion entre el valor numérico de un tile y su representación visual
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


    // check los alrededores de la casilla con las coordenadas indicadas en los parámetros
    public void checkSurroundings(int y, int x, Map map)
    {
        // mirar la de arriba
        if (y-1 > 0 && map.worldMapValue[y-1][x] == 0)
        {
            int[] newOptions = intersection(map.worldMapOptions[y-1][x], getOptions(0, map.worldMapValue[y][x]));
            map.worldMapOptions[y-1][x] = newOptions;
        }

        // mirar la de la derecha
        if (x+1 < map.worldMapOptions.length && map.worldMapValue[y][x+1] == 0)
        {
            int[] newOptions = intersection(map.worldMapOptions[y][x+1], getOptions(1, map.worldMapValue[y][x]));
            map.worldMapOptions[y][x+1] = newOptions;
        }

        // mirar abajo
        if (y+1 < map.worldMapOptions.length && map.worldMapValue[y+1][x] == 0)
        {
            // hacemos la intersección de los valores que ya tiene asignados el Field comprobado y los posibles
            // valores que puede tener teniendo en cuenta el Tile de referencia
            int[] newOptions = intersection(map.worldMapOptions[y+1][x], getOptions(2, map.worldMapValue[y][x]));
            map.worldMapOptions[y+1][x] = newOptions;
        }

        // mirar la de la izquierda
        if (x-1 > 0 && map.worldMapValue[y][x-1] == 0) {
            int[] newOptions = intersection(map.worldMapOptions[y][x - 1], getOptions(3, map.worldMapValue[y][x]));
            map.worldMapOptions[y][x - 1] = newOptions;
        }
    }


    // devuelve el array correspondiente a las opciones iniciales en relación con el tipo de Tile y a la posicion
    // que estamos comprobando
    public int[] getOptions(int pos, int tileValue)
    {
        Rules rules = new Rules();
        

        switch (tileValue)
        {
            case 1:
                return rules.t1[pos];
            case 2:
                return rules.t2[pos];
            case 3:
                return rules.t3[pos];
            case 4:
                return rules.t4[pos];
            case 5:
                return rules.t5[pos];
            case 6:
                return rules.t6[pos];
            case 7:
                return rules.t7[pos];
            case 8:
                return rules.t8[pos];
            case 9:
                return rules.t9[pos];
            case 10:
                return rules.t10[pos];
            case 11:
                return rules.t11[pos];
            case 12:
                return rules.t12[pos];
        }

        int[] error = {2};
        return error;
    }


    // realiza la intersección de dos arrays numéricos
    public int[] intersection(int[] array1, int[] array2)
    {
        int[] res = new int[0];

        for (int value1:array1)
        {
            for (int value2:array2)
            {
                if (value1 == value2)
                {
                    res = Arrays.copyOf(res, res.length+1);
                    res[res.length-1] = value1;
                }
            }
        }

        return res;
    }


    // barre el worldMapValue para comprobar qué casillas son checkeable con el checkSurroundings (si tiene valor != 0)
    public void updateMapOptions(Map map)
    {
        // recorremos eje Y
        for (int y=0; y < map.worldMapValue.length; y++)
        {
            // recorremos eje X
            for (int x=0; x < map.worldMapValue[y].length; x++)
            {
                // comprobamos si ese Tile tiene un valor asignado (es distinto a 0)
                // si lo es, hacemos el checkSurroundings de ese Tile
                if (map.worldMapValue[y][x] != 0)
                {
                    checkSurroundings(y, x, map);
                }
            }
        }
    }


    // barremos el worldMapOptions para ver qué casilla/s tienen el rango de posibilidades más pequeño
    // significando que serán mejores candidatos porque son más seguros en cuanto a la coherencia del mapa
    public int shortestOptionRange(Map map)
    {
        int shortest = 12;

        // recorremos eje Y
        for (int y=0; y < map.worldMapOptions.length; y++)
        {
            // recorremos eje X
            for (int x=0; x < map.worldMapOptions[y].length; x++)
            {
                // comprobamos si ese Tile tiene un rango de valores menor que el rango de valores
                // más pequeño encontrado hasta ahora
                if (map.worldMapOptions[y][x].length < shortest && map.worldMapOptions[y][x].length != 1)
                {
                    shortest = map.worldMapOptions[y][x].length;
                }
            }
        }

        return shortest;
    }


    // busca los Tile candidatos que tienen el menor rango de opciones y luego elige uno aleatorio entre ellos
    // devuelve las coordenadas del candidato seleccionado
    public int[] findCandidate(Map map, int shortest)
    {
        int[][] candidates = {};

        // recorremos eje Y
        for (int y=0; y < map.worldMapOptions.length; y++)
        {
            // recorremos eje X
            for (int x=0; x < map.worldMapOptions[y].length; x++)
            {
                // comprobamos si ese Tile tiene la longitud que buscamos (la del rango más corto)
                if (map.worldMapOptions[y][x].length == shortest)
                {
                    int[] coords = {y, x};

                    candidates = Arrays.copyOf(candidates, candidates.length+1);
                    candidates[candidates.length-1] = coords;
                }
            }
        }

        int randCandidate = (int) (Math.random() * (candidates.length));

        return candidates[randCandidate];
    }


    // elige un valor aleatorio en el rango de opciones que tiene el Tile seleccionado
    public void updateTile(Map map, int[] coords)
    {
        int y = coords[0];
        int x = coords[1];
        int[] tileOptions = map.worldMapOptions[y][x];

        int randOption = (int) (Math.random() * tileOptions.length);

        map.worldMapValue[y][x] = tileOptions[randOption];

        // también vamos a poner que sus opciones de valores son solo 1, la que tiene asignada
        map.worldMapOptions[y][x] = Arrays.copyOf(map.worldMapOptions[y][x], 1);
        map.worldMapOptions[y][x][0] = tileOptions[randOption];
    }
}
