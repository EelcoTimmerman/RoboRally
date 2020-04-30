package nl.sogyo.roborally.domain.rulebooks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.cards.MoveBackCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;
import nl.sogyo.roborally.domain.cards.MoveThreeCard;
import nl.sogyo.roborally.domain.cards.MoveTwoCard;
import nl.sogyo.roborally.domain.cards.RotateLeftCard;
import nl.sogyo.roborally.domain.cards.RotateRightCard;
import nl.sogyo.roborally.domain.cards.UTurnCard;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class TestRulebookRobots {
    final String TESTBOARD4X4 = "ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-X*ES-X*ES-X*||*ES-X*ES-X*ES-X*ES-E*||*ES-X*ES-S*ES-X*ES-X";

    @Test
    public void testRulebookHasRobot(){
        Robot robot1 = new Robot(2, 2);
        RulebookRobots rulebookRobots = new RulebookRobots(robot1);
        Robot robot2 = rulebookRobots.getRobot();
        assertEquals(robot1, robot2);
    }

    @Test
    public void testRulebookHasBoard(){
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4);
        Board board = rulebookRobots.getBoard();
        assertEquals(4, board.getWidth());
        assertEquals(4, board.getHeight());
    }

    @Test
    public void testMoveRobotForwardsWithoutObstacles(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testMoveRobotForwardsIntoNorthWall(){
        Robot robot = new Robot(2,0, Direction.NORTH);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoEastWall(){
        Robot robot = new Robot(3,2, Direction.EAST);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(3, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoSouthWall(){
        Robot robot = new Robot(1, 3, Direction.SOUTH);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(1, robot.getXCoordinate());
        assertEquals(3, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoWestWall(){
        Robot robot = new Robot(0,1, Direction.WEST);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(0, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testRobotTurnsRight(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new RotateRightCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(Direction.EAST, robot.getOrientation());
    }

    @Test
    public void testRobotTurnsLeft(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new RotateLeftCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(Direction.WEST, robot.getOrientation());
    }
    
    @Test
    public void testRobotUTurn(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new UTurnCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(Direction.SOUTH, robot.getOrientation());
    }

    @Test
    public void testRobotMovesOffBoard(){
        Robot robot = new Robot(0,0, Direction.NORTH);
        ICard card = new MoveOneCard();
        robot.program(card);
        robot.setRespawnPoint(2,2);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotMovesBackwards(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new MoveBackCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(3, robot.getYCoordinate());
    }

    @Test
    public void testRobotMovesIntoWallBackwards(){
        Robot robot = new Robot(2,0, Direction.SOUTH);
        ICard card = new MoveBackCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());

    }

    @Test
    public void testRobotMovesOffBoardBackwards(){
        Robot robot = new Robot(0,0, Direction.SOUTH);
        ICard card = new MoveBackCard();
        robot.program(card);
        robot.setRespawnPoint(2,2);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoSteps(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        ICard card = new MoveTwoCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsIntoWall2Steps(){
        Robot robot = new Robot(2,1, Direction.NORTH);
        ICard card = new MoveTwoCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsIntoWall1Step(){
        Robot robot = new Robot(2,0, Direction.NORTH);
        ICard card = new MoveTwoCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsOffTheBoard2Steps(){
        Robot robot = new Robot(1,1, Direction.NORTH);
        ICard card = new MoveTwoCard();
        robot.program(card);
        robot.setRespawnPoint(2,2);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsOffTheBoard1Step(){
        Robot robot = new Robot(1,0, Direction.NORTH);
        ICard card = new MoveTwoCard();
        robot.program(card);
        robot.setRespawnPoint(2,2);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesThreeStepsOffTheBoard2Step(){
        Robot robot = new Robot(0,1, Direction.NORTH);
        ICard card = new MoveThreeCard();
        robot.program(card);
        robot.setRespawnPoint(2,2);
        RulebookRobots rulebookRobots = new RulebookRobots(TESTBOARD4X4, robot);
        rulebookRobots.playRound();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());

    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveOneCard(){
        String boardString = "ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveOneCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesBackwardOntoPit(){
        String boardString = "ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.WEST);
        ICard card = new MoveBackCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveTwoCard(){
        String boardString = "ES-X*ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveTwoCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveTwoCard(){
        String boardString = "ES-X*PT-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveTwoCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesThreeForwardOntoPit(){
        String boardString = "ES-X*ES-X*ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveThreeCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveThreeCard(){
        String boardString = "ES-X*ES-X*PT-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveThreeCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveThreeCard(){
        String boardString = "ES-X*PT-X*ES-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        ICard card = new MoveThreeCard();
        robot.program(card);
        RulebookRobots rulebookRobots = new RulebookRobots(boardString, robot);
        rulebookRobots.playRound();
        assert(robot.isAt(0, 0));
    }

}