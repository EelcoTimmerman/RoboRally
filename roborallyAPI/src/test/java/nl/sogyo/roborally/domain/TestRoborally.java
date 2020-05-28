package nl.sogyo.roborally.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.BoardFactory;

public class TestRoborally {
    private Board TESTBOARD4X4 = null;
    private Board PITTESTBOARD = null;
    private Board SLOWCONVEYORBELTTESTBOARD = null;
    private Board SLOWCONVEYORBELTTESTBOARDOTHER = null;
    private Board GEARTESTBOARD = null;
    private Board ROBOTLASERTESTBOARDWALL = null;
    private Board BOARDLASERTESTBOARD = null;
    private Board SLOWCONVERYORBELTROTATIONTESTBOARD = null;
    private Board MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD = null;
    private Board MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD = null;    
    private Board WINNINGTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD = null;
    private Board MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD = null;
    private final int[] doNothingIntArray = {7,7,7,7,7};

    @Before
    public void initializeBoards(){
        TESTBOARD4X4 = BoardFactory.createTESTBOARD4X4();
        PITTESTBOARD = BoardFactory.createPitTestBoard();
        SLOWCONVEYORBELTTESTBOARD = BoardFactory.createSlowConveyorbeltTestBoard();
        SLOWCONVEYORBELTTESTBOARDOTHER = BoardFactory.createSlowConveyorbeltTestBoardOther();
        GEARTESTBOARD = BoardFactory.createGearTestBoard();
        ROBOTLASERTESTBOARDWALL = BoardFactory.createRobotLaserWallTestBoard();
        BOARDLASERTESTBOARD = BoardFactory.createBoardlaserTestBoard();
        SLOWCONVERYORBELTROTATIONTESTBOARD = BoardFactory.createSlowConveyorbeltRotationTestBoard();
        MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD = BoardFactory.createMultipleRobotsOnSlowConveyorbeltTestBoard();
        MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD = BoardFactory.createMultipleRobotsOnSlowConveyorbeltWithPitTestBoard();
        WINNINGTESTBOARD = BoardFactory.createWinningBoard();
        MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard();
        MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD = BoardFactory.createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard();
    }

    @Test
    public void testRulebookHasRobot(){
        Robot robot1 = new Robot(2, 2);
        Roborally roborally = new Roborally(robot1);
        Robot robot2 = roborally.getRobots().get(0);
        assertEquals(robot1, robot2);
    }

    @Test
    public void testRulebookHasBoard(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Board board = roborally.getBoard();
        assertEquals(4, board.getWidth());
        assertEquals(4, board.getHeight());
    }

    @Test
    public void testMoveRobotForwardsWithoutObstacles(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testMoveRobotForwardsIntoNorthWall(){
        Robot robot = new Robot(2,0, Direction.NORTH);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoEastWall(){
        Robot robot = new Robot(3,2, Direction.EAST);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(3, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoSouthWall(){
        Robot robot = new Robot(1, 3, Direction.SOUTH);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(1, robot.getXCoordinate());
        assertEquals(3, robot.getYCoordinate());
    }
    
    @Test
    public void testMoveRobotForwardsIntoWestWall(){
        Robot robot = new Robot(0,1, Direction.WEST);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testRobotTurnsRight(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new RotateRightCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(Direction.EAST, robot.getOrientation());
    }

    @Test
    public void testRobotTurnsLeft(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new RotateLeftCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(Direction.WEST, robot.getOrientation());
    }
    
    @Test
    public void testRobotUTurn(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new UTurnCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(Direction.SOUTH, robot.getOrientation());
    }

    @Test
    public void testRobotMovesOffBoard(){
        Robot robot = new Robot(0,0, Direction.NORTH);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        robot.setRespawnPoint(2,2);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotMovesBackwards(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new MoveBackCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(3, robot.getYCoordinate());
    }

    @Test
    public void testRobotMovesIntoWallBackwards(){
        Robot robot = new Robot(2,0, Direction.SOUTH);
        Card card = new MoveBackCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());

    }

    @Test
    public void testRobotMovesOffBoardBackwards(){
        Robot robot = new Robot(0,0, Direction.SOUTH);
        Card card = new MoveBackCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        robot.setRespawnPoint(2,2);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoSteps(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsIntoWall2Steps(){
        Robot robot = new Robot(2,1, Direction.NORTH);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsIntoWall1Step(){
        Robot robot = new Robot(2,0, Direction.NORTH);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(0, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsOffTheBoard2Steps(){
        Robot robot = new Robot(1,1, Direction.NORTH);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        robot.setRespawnPoint(2,2);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesTwoStepsOffTheBoard1Step(){
        Robot robot = new Robot(1,0, Direction.NORTH);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        robot.setRespawnPoint(2,2);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());
    }

    @Test
    public void testRobotTakesThreeStepsOffTheBoard2Step(){
        Robot robot = new Robot(0,1, Direction.NORTH);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        robot.setRespawnPoint(2,2);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());

    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveOneCard(){
        Robot robot = new Robot(2,0, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesBackwardOntoPit(){
        Robot robot = new Robot(2,0, Direction.WEST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveBackCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveTwoCard(){
        Robot robot = new Robot(1,0, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveTwoCard(){
        Robot robot = new Robot(2,0, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesThreeForwardOntoPit(){
        Robot robot = new Robot(0,0, Direction.EAST);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveThreeCard(){
        Robot robot = new Robot(1,0, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveThreeCard(){
        Robot robot = new Robot(2,0, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(PITTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.isAt(0, 0));
    }
    
// --------------------------------------------------

    @Test
    public void testMovementRobotOnSlowConveyorbeltNORTH(){
        Robot robot = new Robot(0,2);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testMovementRobotOnSlowConveyorbeltEAST(){
        Robot robot = new Robot(0,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testMovementRobotOnSlowConveyorbeltSOUTH(){
        Robot robot = new Robot(2,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 2 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testMovementRobotOnSlowConveyorbeltWEST(){
        Robot robot = new Robot(2,2);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 2);
    }
    
    @Test
    public void testSlowConveyorbeltWall(){
        Robot robot = new Robot(0,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testSlowConveyorbeltPit(){
        Robot robot = new Robot(2,0);
        robot.program(new DoNothingCard());
        robot.setRespawnPoint(0, 1);
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testSlowConveyorbeltOffBoard(){
        Robot robot = new Robot(2,2);
        robot.program(new DoNothingCard());
        robot.setRespawnPoint(1, 1);
        Roborally roborally = new Roborally(SLOWCONVEYORBELTTESTBOARDOTHER, robot);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testRobotTurningLeftOnRotatingSlowConveyorbelt(){
        Robot robot = new Robot(0, 1, Direction.EAST);
        Card card = new DoNothingCard();
        robot.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assertEquals(1, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
        assert(robot.getOrientation() == Direction.NORTH);
    }

    @Test
    public void testRobotTurningRightOnRotatingSlowConveyorbelt(){
        Robot robot = new Robot(0, 2, Direction.EAST);
        Card card = new DoNothingCard();
        robot.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
        assert(robot.getOrientation() == Direction.SOUTH);
    }

    @Test
    public void testRobotTurningReverseOnRotatingSlowConveyorbelt(){
        Robot robot = new Robot(0, 4, Direction.EAST);
        Card card = new DoNothingCard();
        robot.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 4);
        assert(robot.getOrientation() == Direction.WEST);
    }

    @Test
    public void testRobotNotTurningOnRotatingSlowConveyorbelt(){
        Robot robot = new Robot(1, 2, Direction.WEST);
        Card card = new DoNothingCard();
        robot.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
        assert(robot.getOrientation() == Direction.WEST);
    }

    @Test
    public void testRobotNotTurningOnSlowConveyorbelt(){
    Robot robot = new Robot(1, 2, Direction.NORTH);
    Card card = new DoNothingCard();
    robot.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
    roborally.activateAllBoardElements();
    assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    assert(robot.getOrientation() == Direction.NORTH);
    }

    @Test
    public void testRobotNotTurningWhenPushedOffSlowConveyorbelt(){
        Robot robot = new Robot(1, 1, Direction.NORTH);
        Card card = new DoNothingCard();
        robot.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD, robot);
        roborally.activateAllBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
        assert(robot.getOrientation() == Direction.NORTH);
    }

    @Test
    public void testRobotPushedOffSlowConveyorbeltIntoOtherRobot(){
        Robot robot1 = new Robot(1, 0, Direction.NORTH);
        Robot robot2 = new Robot(1, 1, Direction.NORTH);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
    }

    @Test
    public void testTwoRobotsNextToEachOtherOnSlowConveyorbelt(){
        Robot robot1 = new Robot(1, 1, Direction.NORTH);
        Robot robot2 = new Robot(1, 2, Direction.NORTH);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
    }

    @Test
    public void testThreeRobotsNextToEachOtherOnSlowConveyorbelt(){
        Robot robot1 = new Robot(1, 1, "Robot1", 7);
        Robot robot2 = new Robot(1, 2, "Robot2", 7);
        Robot robot3 = new Robot(1, 3, "Robot3", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
        assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 2);        
    }

    @Test
    public void testThreeRobotsNextToEachOtherOnSlowConveyorbeltOtherOrder(){
        Robot robot1 = new Robot(1, 3, "Robot1", 7);
        Robot robot2 = new Robot(1, 2, "Robot2", 7);
        Robot robot3 = new Robot(1, 1, "Robot3", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
        assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 0);        
    }

    @Test
    public void testThreeRobotsNextToEachOtherOnSlowConveyorbeltWithPit(){
        Robot robot1 = new Robot(1, 3, "Robot1", 7);
        robot1.setRespawnPoint(0, 4);
        Robot robot2 = new Robot(1, 2, "Robot2", 7);
        robot2.setRespawnPoint(1, 4);
        Robot robot3 = new Robot(1, 1, "Robot3", 7);
        robot3.setRespawnPoint(0, 5);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTWITHPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 2);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);        
        assert(robot3.getXCoordinate() == 0 && robot3.getYCoordinate() == 5);        
    }

    @Test
    public void testTwoRobotsOnSlowConveyorbeltWithWallInFront(){
        Robot robot1 = new Robot(0, 0, "Robot1", 7);
        robot1.setRespawnPoint(1, 2);
        Robot robot2 = new Robot(0, 1, "Robot2", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSONSLOWCONVEYORBELTTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 0 && robot2.getYCoordinate() == 1);        
    }

    @Test
    public void testRobotNotTurningOnSlowConveyorbeltWithOtherRobotInFrontOfObstacle(){
        Robot robot1 = new Robot(0, 1, "Robot1", 7);
        robot1.setOrientation(Direction.EAST);
        Robot robot2 = new Robot(1, 1, "Robot2", 7);
        robot2.setOrientation(Direction.NORTH);
        Robot robot3 = new Robot(1, 0, "Robot3", 7);
        robot3.setOrientation(Direction.WEST);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 1);
        assert(robot1.getOrientation() == Direction.EAST);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 1);
        assert(robot2.getOrientation() == Direction.NORTH);
        assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 0);
        assert(robot3.getOrientation() == Direction.WEST);
    }

    // --------- "T-Section" tests (4 tests) ---------

    @Test
    public void testTwoRobotsPushedToSameSquareByConveyorBelt(){
    Robot robot1 = new Robot(0, 1, "Robot1", 7);
    Robot robot2 = new Robot(1, 2, "Robot2", 7);
    Card card = new DoNothingCard();
    robot1.program(card);
    robot2.program(card);
    Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
    roborally.addRobot(robot1);
    roborally.addRobot(robot2);
    roborally.activateAllBoardElements();
    assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 1);
    assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 2);
    }

    @Test
    public void testFourRobotsPushedToSamePit(){
        Robot robot1 = new Robot(1, 0, "Robot1", 7);
        Robot robot2 = new Robot(2, 1, "Robot2", 7);
        Robot robot3 = new Robot(1, 2, "Robot3", 7);
        Robot robot4 = new Robot(0, 1, "Robot4", 7);
        robot1.setRespawnPoint(0, 0);
        robot2.setRespawnPoint(2, 0);
        robot3.setRespawnPoint(2, 2);
        robot4.setRespawnPoint(0, 2);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 0 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 2 && robot2.getYCoordinate() == 0);
        assert(robot3.getXCoordinate() == 2 && robot3.getYCoordinate() == 2);
        assert(robot4.getXCoordinate() == 0 && robot4.getYCoordinate() == 2);
    }

    @Test
    public void testFourRobotsPushedToSameSquareDontMove(){
        Robot robot1 = new Robot(1, 0, "Robot1", 7);
        Robot robot2 = new Robot(2, 1, "Robot2", 7);
        Robot robot3 = new Robot(1, 2, "Robot3", 7);
        Robot robot4 = new Robot(0, 1, "Robot4", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        robot4.program(card);
        Roborally roborally = new Roborally(MULTIPLEROBOTSPUSHEDTOSAMESQUAREBYSLOWCONVEYORBELTWITHOUTPITTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 0);
        assert(robot2.getXCoordinate() == 2 && robot2.getYCoordinate() == 1);
        assert(robot3.getXCoordinate() == 1 && robot3.getYCoordinate() == 2);
        assert(robot4.getXCoordinate() == 0 && robot4.getYCoordinate() == 1);
    }

    @Test
    public void testRobotChainBehindTwoRobotsWithSameDestination(){
        Robot robot1 = new Robot(1, 3, "Robot1", 7);
        Robot robot2 = new Robot(1, 2, "Robot2", 7);
        Robot robot3 = new Robot(0, 1, "Robot3", 7);
        Card card = new DoNothingCard();
        robot1.program(card);
        robot2.program(card);
        robot3.program(card);
        Roborally roborally = new Roborally(SLOWCONVERYORBELTROTATIONTESTBOARD);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.activateAllBoardElements();
        assert(robot1.getXCoordinate() == 1 && robot1.getYCoordinate() == 3);
        assert(robot2.getXCoordinate() == 1 && robot2.getYCoordinate() == 2);
        assert(robot3.getXCoordinate() == 0 && robot3.getYCoordinate() == 1);
    }

    @Test
    public void testGearReverse(){
        Robot robot = new Robot(2,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(GEARTESTBOARD, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getOrientation().equals(Direction.SOUTH));
    }
    
    @Test
    public void testGearRight(){
        Robot robot = new Robot(1,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(GEARTESTBOARD, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getOrientation().equals(Direction.EAST));
    }
    
    @Test
    public void testGearLeft(){
        Robot robot = new Robot(0,0);
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(GEARTESTBOARD, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getOrientation().equals(Direction.WEST));
    }
    
    @Test
    public void testCheckpoint(){
        Robot robot = new Robot(3,2, Direction.SOUTH);
        robot.setRespawnPoint(0, 0);
        robot.program(0);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 0);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getRespawnXCoordinate() == 3 && robot.getRespawnYCoordinate() == 3);
    }

    @Test
    public void testCheckpoint2(){
        Robot robot = new Robot(2,3, Direction.EAST);
        robot.setRespawnPoint(0, 0);
        robot.program(0);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 0);
        roborally.playAllRegistersIfRobotsReady();
        assert(robot.getRespawnXCoordinate() == 3 && robot.getRespawnYCoordinate() == 3);
    }

    @Test
    public void testRobotPushesRobot1(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(1,1);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall1(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotOffTheBoard1(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(0,2);
        Robot robot2 = new Robot(1,2, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot1.setRespawnPoint(3, 3);
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(3, robot1.getXCoordinate());
        assertEquals(3, robot1.getYCoordinate());
        assertEquals(0, robot2.getXCoordinate());
        assertEquals(2, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushingChain1(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(1,0);
        Robot robot2 = new Robot(2,0);
        Robot robot3 = new Robot(3,0, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new DoNothingCard());
        robot3.program(new MoveOneCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(0, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(0, robot2.getYCoordinate());
        assertEquals(2, robot3.getXCoordinate());
        assertEquals(0, robot3.getYCoordinate());
    }

    @Test
    public void testRobotPushingChain1IntoWall(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1);
        Robot robot3 = new Robot(2,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new DoNothingCard());
        robot3.program(new MoveOneCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
        assertEquals(2, robot3.getXCoordinate());
        assertEquals(1, robot3.getYCoordinate());
    }
    
    @Test
    public void testRobotPushesRobot2(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(2,1);
        Robot robot2 = new Robot(3,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall2(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall2AfterOneStep(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(1,1);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotOffTheBoard2(){
        Roborally roborally = new Roborally(TESTBOARD4X4);
        Robot robot1 = new Robot(0,2);
        Robot robot2 = new Robot(1,2, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot1.setRespawnPoint(3, 3);
        robot2.program(new MoveTwoCard());
        robot2.setRespawnPoint(2, 2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(3, robot1.getXCoordinate());
        assertEquals(3, robot1.getYCoordinate());
        assertEquals(2, robot2.getXCoordinate());
        assertEquals(2, robot2.getYCoordinate());
    }

    @Test
    public void testTwoRobotMoveInCorrectOrder(){
        Robot robot1 = new Robot(2,1, Direction.SOUTH);
        Robot robot2 = new Robot(3,2, Direction.WEST);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot1);
        roborally.addRobot(robot2);
        robot1.program(0);
        robot2.program(4);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(robot1, roborally.getRobots().get(0));
    }

    @Test
    public void testRobotFiresLaserWest(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(doNothingIntArray);
        robot2.program(doNothingIntArray);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserNorth(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,2, Direction.NORTH);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(doNothingIntArray);
        robot2.program(doNothingIntArray);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserEast(){
        Robot robot1 = new Robot(2,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,1, Direction.EAST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(doNothingIntArray);
        robot2.program(doNothingIntArray);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserSouth(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(doNothingIntArray);
        robot2.program(doNothingIntArray);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserMisses(){
        Robot robot1 = new Robot(1,2, Direction.SOUTH);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotFiresLaserAtWallNorth(){
        Robot robot1 = new Robot(0,0, Direction.WEST);
        Robot robot2 = new Robot(0,1, Direction.NORTH);
        Roborally roborally = new Roborally(ROBOTLASERTESTBOARDWALL);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotFiresLaserAtWallEast(){
        Robot robot1 = new Robot(1,0, Direction.SOUTH);
        Robot robot2 = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally(ROBOTLASERTESTBOARDWALL);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserAtWallSouth(){
        Robot robot1 = new Robot(0,1, Direction.WEST);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally(ROBOTLASERTESTBOARDWALL);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserAtWallWest(){
        Robot robot1 = new Robot(0,0, Direction.WEST);
        Robot robot2 = new Robot(1,0, Direction.WEST);
        Roborally roborally = new Roborally(ROBOTLASERTESTBOARDWALL);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotBlocksLaser(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,2, Direction.NORTH);
        Robot robot3 = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        robot1.program(doNothingIntArray);
        robot2.program(doNothingIntArray);
        robot3.program(doNothingIntArray);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
        assertEquals(9, robot3.getHealth());

    }

    @Test
    public void testRobotLaserEdgeCase(){
        Robot robot1 = new Robot(0,0, Direction.EAST);
        Robot robot2 = new Robot(3,0, Direction.SOUTH);
        Robot robot3 = new Robot(3,3, Direction.WEST);
        Robot robot4 = new Robot(0,3, Direction.NORTH);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.addRobot(robot4);
        robot1.program(new DoNothingCard());
        robot2.program(new DoNothingCard());
        robot3.program(new DoNothingCard());
        robot4.program(new DoNothingCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot1.getHealth());
        assertEquals(4, robot2.getHealth());
        assertEquals(4, robot3.getHealth());
        assertEquals(4, robot4.getHealth());
    }

    @Test
    public void testRobotPoweredDownDoesNotMove(){
        Robot robot1 = new Robot(0,1, Direction.EAST);
        Robot robot2 = new Robot(2,2, Direction.WEST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(new MoveOneCard());
        robot2.program(new MoveOneCard());
        robot2.turnOnOrOff();
        roborally.playAllRegistersIfRobotsReady();
        robot2.program(new MoveOneCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot1.getXCoordinate());
    }

    @Test
    public void testRobotReactivates(){
        Robot robot = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        roborally.addRobot(robot);
        robot.program(new MoveOneCard());
        robot.turnOnOrOff();
        roborally.playAllRegistersIfRobotsReady();
        roborally.playAllRegistersIfRobotsReady();
        robot.program(new MoveOneCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(2, robot.getXCoordinate());
    }

    @Test
    public void testRobotRepairsAfterPowerdown(){
        Robot robot = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally(TESTBOARD4X4);
        robot.program(new MoveOneCard());
        robot.turnOnOrOff();
        robot.takeDamage(4);
        assertEquals(5, robot.getHealth());
        roborally.addRobot(robot);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(9, robot.getHealth());
    }

    @Test
    public void testBoardLaserStopsAtBoardEdge(){
        Robot robot = new Robot();
        robot.program(new DoNothingCard());
        Roborally roborally = new Roborally(BOARDLASERTESTBOARD, robot);
        roborally.playAllRegistersIfRobotsReady();
    }

    @Test
    public void testBoardLaserDamage(){
        Robot robot = new Robot(0,0);
        Roborally roborally = new Roborally(BOARDLASERTESTBOARD, robot);
        robot.program(new DoNothingCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(4, robot.getHealth());
    }

    @Test
    public void testWinning(){
        Robot robot = new Robot(0,0);
        Roborally roborally = new Roborally(WINNINGTESTBOARD, robot);
        robot.program(new RotateRightCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(roborally.getWinner(), null);
        robot.program(new MoveTwoCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(roborally.getWinner(), robot);
    }

    @Test
    public void testWinningIfFinalPointIsNotTheEnd(){
        Robot robot = new Robot(0,0);
        Roborally roborally = new Roborally(WINNINGTESTBOARD, robot);
        robot.program(new RotateRightCard());
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(roborally.getWinner(), null);
        Card[] cards = {new DoNothingCard(), new MoveTwoCard(),new UTurnCard(), new MoveOneCard(), new DoNothingCard()};
        robot.program(cards);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(roborally.getWinner(), robot);
    }

    @Test
    public void testWinningWhenTwoRobotsReachFinalPoint(){
        Robot robot1 = new Robot(0,0);
        Robot robot2 = new Robot(2,1);
        Roborally roborally = new Roborally(WINNINGTESTBOARD, robot1);
        roborally.addRobot(robot2);
        robot1.program(new RotateRightCard());
        robot2.program(new DoNothingCard());
        roborally.playAllRegistersIfRobotsReady();
        Card[] cards1 = {new DoNothingCard(), new DoNothingCard(),new DoNothingCard(), new DoNothingCard(), new MoveTwoCard()};
        Card[] cards2 = {new MoveOneCard(), new MoveBackCard(),new DoNothingCard(), new DoNothingCard(), new MoveTwoCard()};
        robot1.program(cards1);
        robot2.program(cards2);
        roborally.playAllRegistersIfRobotsReady();
        assertEquals(roborally.getWinner(), robot2);
    }

}