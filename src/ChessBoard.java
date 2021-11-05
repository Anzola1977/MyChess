public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // создаём двумерный массив доски
    String nowPlayer; // чей сейчас ход

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) { //проверяем есть ли такие места на доске
//проверяем если цвет текущего игрока не совпадает с цветом фигуры на указанной клетке, то ходить ею нельзя
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
//если данная фигура может быть сдвинута на эту позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                //проверяем позицию для рокировки
                //если фигура - это король или ладья
                if (board[startLine][startColumn].getSymbol().equals("K") || board[startLine][startColumn].getSymbol().equals("R")) {
                    //устанавливаем, что данная фигура ещё не двигалась
                    board[startLine][startColumn].check = false;
                }
                //если была возможность сдвинуть фигуру, то переместили её на конечную клетку
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                //удалили фигуру со стартовой клетки
                board[startLine][startColumn] = null; // set null to previous cell
                //если сейчас был ход белых, то следующий ход чёрных, а если был ход чёрных, то наоборот
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
// пробегается по массиву доски и если там стоит null, то пустое поле или поле, занятое фигурой в зависимости от цвета и символа
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
