package zdobnikova.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GameTest {

    Game game;

    @BeforeEach
    void createBoard(){
        game = new Game();
    }

    @Test
    void makeFirstMoves() {
        game.makeMove(new Point(7, 6));
        game.makeMove(new Point(7, 5));
        game.makeMove(new Point(7, 7));
        assertEquals(game.getBoard().get(new Point(7, 6)), null);
        assertEquals(game.getBoard().get(new Point(7, 5)), null);
        assertEquals(game.getBoard().get(new Point(7, 7)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.WHITE);
    }

    @Test
    void makeFirstMove() {
        game.makeMove(new Point(7, 7));
        assertEquals(game.getBoard().get(new Point(7, 7)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.WHITE);
    }

    @Test
    void makeSecondMove(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(1,3));
        assertEquals(game.getBoard().get(new Point(7,7)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.BLACK);
    }

    @Test
    void makeMoveInSamePlace(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(7,7));
        assertEquals(game.getBoard().get(new Point(7,7)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.WHITE);
    }

    @Test
    void makeMoveOutOfBoard(){
        game.makeMove(new Point(24,24));
        assertEquals(game.getCurrentPlayer(), Stone.BLACK);
    }

    @Test
    void winBlack(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(7,2));
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(7,4));
        game.makeMove(new Point(1,3));
        game.makeMove(new Point(7,6));
        game.makeMove(new Point(1,4));
        game.makeMove(new Point(7,11));
        game.makeMove(new Point(1,5));
        game.makeMove(new Point(7,1));
        game.makeMove(new Point(1,6));
        assertEquals(game.getWinner(),Stone.BLACK);

    }

    @Test
    void foulSix(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(7,2));
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(7,4));
        game.makeMove(new Point(1,3));
        game.makeMove(new Point(7,6));
        game.makeMove(new Point(1,4));
        game.makeMove(new Point(7,11));
        game.makeMove(new Point(1,5));
        game.makeMove(new Point(7,1));
        game.makeMove(new Point(1,7));
        game.makeMove(new Point(8,1));
        assertEquals(game.foulMax(new Point(1,6)),true);
    }

    @Test
    void forkThree(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(1,3));
        game.makeMove(new Point(7,8));
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(6,6));
        game.makeMove(new Point(1,5));
        game.makeMove(new Point(5,6));
        game.makeMove(new Point(1,10));
        assertEquals(game.fork(new Point(7,6)),true);
    }
    @Test
    void forkThreeFour(){
        game.makeMove(new Point(7,7));
        game.makeMove(new Point(1,3));
        game.makeMove(new Point(7,8));
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(6,6));
        game.makeMove(new Point(1,5));
        game.makeMove(new Point(5,6));
        game.makeMove(new Point(1,10));
        game.makeMove(new Point(4,6));
        game.makeMove(new Point(10,6));
        assertEquals(game.fork(new Point(7,6)),false);

    }

    @Test
    void forkNot() {
        game.makeMove(new Point(7, 7));
        game.makeMove(new Point(1, 3));
        game.makeMove(new Point(7, 8));
        game.makeMove(new Point(1, 2));
        game.makeMove(new Point(6, 6));
        game.makeMove(new Point(1, 5));
        assertEquals(game.fork(new Point(7, 6)), false);
    }


}