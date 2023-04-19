import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.BlockIndex;
import model.Player;
import model.RowBlockModel;
import controller.RowGameController;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private RowGameController game;

    @Before
    public void setUp() {
	game = new RowGameController();
    }

    @After
    public void tearDown() {
	game = null;
    }

    @Test
    public void testNewGame() {
        assertEquals (Player.PLAYER_1, game.gameModel.getPlayer());
        assertEquals (9, game.gameModel.movesLeft);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
	RowBlockModel block = new RowBlockModel(null);
    }
    
    @Test
    public void testLegalMove() {
        RowGameController testGame = new RowGameController();
        BlockIndex testMove = new BlockIndex(0, 0);
        testGame.move(testMove);
        assertEquals(Player.PLAYER_2, testGame.gameModel.getPlayer());
        assertEquals(8, testGame.gameModel.movesLeft);
        assertEquals(testGame.gameModel.blocksData[0][0].getIsLegalMove(), false);
    }

    @Test
    public void testWin() {
        RowGameController testGame = new RowGameController();
        BlockIndex testMove = new BlockIndex(0, 0);
        int[][] winMoves = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 2, 1 }, { 0, 2 } };
        for (int[] move : winMoves) {
            testMove = new BlockIndex(move[0], move[1]);
            testGame.move(testMove);
        }
        assertEquals("Player " + Player.PLAYER_1 + " wins!", testGame.gameModel.getFinalResult());
    }

    @Test
    public void testTie() {
        RowGameController testGame = new RowGameController();
        BlockIndex testMove = new BlockIndex(0, 0);
        int[][] tieMoves = { { 0, 0 }, { 0, 1 }, { 2, 1 }, { 2, 2 }, { 1, 2 }, { 1, 0 }, { 2, 0 }, { 1, 1 }, { 0, 2 } };
        for (int[] move : tieMoves){
        testMove = new BlockIndex(move[0], move[1]);
        testGame.move(testMove);
        }
        assertEquals(0, testGame.gameModel.movesLeft);
        assertEquals("Game ends in a draw", testGame.gameModel.getFinalResult());
    }

    @Test
    public void testReset() {
        RowGameController testGame = new RowGameController();
        BlockIndex testMove = new BlockIndex(0, 0);
        testGame.move(testMove);
        testMove = new BlockIndex(0, 1);
        testGame.move(testMove);
        testGame.resetGame();
        assertEquals(testGame.gameModel.movesLeft, 9);
        assertEquals(testGame.gameModel.blocksData[0][0].getIsLegalMove(), true);
        assertEquals(testGame.gameModel.blocksData[0][1].getIsLegalMove(), true);
        assertEquals(Player.PLAYER_1, testGame.gameModel.getPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUndoZeroMoves() {
        RowGameController testGame = new RowGameController();
        testGame.undoMove();
    }
    
    @Test
    public void testUndoFirstMove() {
        RowGameController testGame = new RowGameController();
        // testGame.gameView.gui.setVisible(true);
        BlockIndex testMove = new BlockIndex(0, 0);
        testGame.move(testMove);
        testGame.undoMove();
        assertEquals(9, testGame.gameModel.movesLeft);
        assertEquals(true, testGame.gameModel.blocksData[0][0].getIsLegalMove());
        }
}
