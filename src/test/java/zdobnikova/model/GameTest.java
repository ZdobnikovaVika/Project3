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
    void makeFirstMove() {
        game.makeMove(new Point(1, 2));
        assertEquals(game.getBoard().get(new Point(1, 2)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.WHITE);
    }

    @Test
    void makeSecondMove(){
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(1,3));
        assertEquals(game.getBoard().get(new Point(1,2)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.BLACK);
    }

    @Test
    void makeMoveInSamePlace(){
        game.makeMove(new Point(1,2));
        game.makeMove(new Point(1,2));
        assertEquals(game.getBoard().get(new Point(1,2)), Stone.BLACK);
        assertEquals(game.getCurrentPlayer(), Stone.WHITE);
    }

    @Test
    void makeMoveOutOfBoard(){
        game.makeMove(new Point(24,24));
        assertEquals(game.getCurrentPlayer(), Stone.BLACK);
    }


}