public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //проверяем, что все координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            //проверяем, что стартовая координата не равна конечной
            if (line != toLine && column != toColumn &&
                    //и конечная клетка пустая
            (chessBoard.board[toLine][toColumn] == null ||
                    //или цвет фигуры в конечной клетке не равен цвету текущего (игрока)
            !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                    // и стартовая клетка не пустая
                    chessBoard.board[line][column] != null) {
                // если стартовая клетка не равна коню, то не ходим
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

                // перечисляем все возможные позиции для лошади
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}};
//проверяем можно ли сходить в нужную позицию
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i][0] == toLine && positions[i][1] == toColumn)
                        return true;
                }
            }
        } else return false;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    public boolean checkPos(int pos) {   // check that our position is correct
        if (pos >= 0 && pos <= 7) return true;
        else return false;
    }
}
