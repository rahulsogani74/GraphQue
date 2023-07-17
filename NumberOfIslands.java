public class NumberOfIslands {
    public int numIslands(char[][] value) {
        int count = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                if (value[i][j] == '1') {
                    num(value, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void num(char[][] value, int i, int j) {
        if (i >= 0 && j >= 0 && i < value.length && j < value[0].length && value[i][j] == '1') {
            value[i][j] = '0';
            num(value, i + 1, j);
            num(value, i - 1, j);
            num(value, i, j + 1);
            num(value, i, j - 1);
        }
    }

    public static void main(String[] args) {
        char[][] value = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int islandCount = numberOfIslands.numIslands(value);

        System.out.println("Number of islands: " + islandCount);
    }
}
