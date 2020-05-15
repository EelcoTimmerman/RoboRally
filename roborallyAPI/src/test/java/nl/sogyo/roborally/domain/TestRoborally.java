package nl.sogyo.roborally.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class TestRoborally {
    final String TESTBOARD4X4 = "ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-X*ES-X*ES-X*||*ES-X*ES-X*ES-X*ES-E*||*ES-X*ES-S*ES-X*ES-X";

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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
        assertEquals(Direction.EAST, robot.getOrientation());
    }

    @Test
    public void testRobotTurnsLeft(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new RotateLeftCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(Direction.WEST, robot.getOrientation());
    }
    
    @Test
    public void testRobotUTurn(){
        Robot robot = new Robot(2,2, Direction.NORTH);
        Card card = new UTurnCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(TESTBOARD4X4, robot);
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
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
        roborally.playRoundIfAllRobotsReady();
        assertEquals(2, robot.getXCoordinate());
        assertEquals(2, robot.getYCoordinate());

    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveOneCard(){
        String boardString = "ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveOneCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesBackwardOntoPit(){
        String boardString = "ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.WEST);
        Card card = new MoveBackCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveTwoCard(){
        String boardString = "ES-X*ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveTwoCard(){
        String boardString = "ES-X*PT-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveTwoCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesThreeForwardOntoPit(){
        String boardString = "ES-X*ES-X*ES-X*PT-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesTwoForwardOntoPitMoveThreeCard(){
        String boardString = "ES-X*ES-X*PT-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }

    @Test
    public void testRobotMovesOneForwardOntoPitMoveThreeCard(){
        String boardString = "ES-X*PT-X*ES-X*ES-X";
        Robot robot = new Robot(0,0);
        robot.setOrientation(Direction.EAST);
        Card card = new MoveThreeCard();
        Card[] cards = {card, new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot.program(cards);
        Roborally roborally = new Roborally(boardString, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.isAt(0, 0));
    }
    

    @Test
    public void testMovementRobotOnBeltNORTH(){
        String boardString = "ES-X*ES-X*||*CSN-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,1);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testMovementRobotOnBeltEAST(){
        String boardString = "CSE-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testMovementRobotOnBeltSOUTH(){
        String boardString = "CSS-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testMovementRobotOnBeltWEST(){
        String boardString = "ES-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
    }
    
    @Test
    public void testConveyorbeltWall(){
        String boardString = "ES-X*CSW-W*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testConveyorbeltPit(){
        String boardString = "PT-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        robot.setRespawnPoint(0, 1);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testConveyorbeltOffBoard(){
        String boardString = "CSW-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        robot.setRespawnPoint(1, 1);
        Roborally roborally = new Roborally(board, robot);
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    }
    
    @Test
    public void testGearReverse(){
        String boardString = "180-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getOrientation().equals(Direction.SOUTH));
    }
    
    @Test
    public void testGearRight(){
        String boardString = "GR-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getOrientation().equals(Direction.EAST));
    }
    
    @Test
    public void testGearLeft(){
        String boardString = "GL-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getOrientation().equals(Direction.WEST));
    }
    
    @Test
    public void testCheckpoint(){
        String boardString = "ES-X*ES-X*||*CH-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0, Direction.SOUTH);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 0);
        robot.moveForward();
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 1);
    }

    @Test
    public void testCheckpoint2(){
        String boardString = "ES-X*ES-X*||*ES-X*CH-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,1, Direction.EAST);
        int[] nrs = {7,7,7,7,7};
        robot.program(nrs);
        Roborally roborally = new Roborally(board, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 1);
        robot.moveForward();
        roborally.playRoundIfAllRobotsReady();
        assert(robot.getRespawnXCoordinate() == 1 && robot.getRespawnYCoordinate() == 1);
    }

    @Test
    public void testRobotPushesRobot1(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(1,1);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall1(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotOffTheBoard1(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(0,2);
        Robot robot2 = new Robot(1,2, Direction.WEST);
        Card[] cards1 = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot1.program(cards1);
        robot1.setRespawnPoint(3, 3);
        Card[] cards2 = {new MoveOneCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
        robot2.program(cards2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(3, robot1.getXCoordinate());
        assertEquals(3, robot1.getYCoordinate());
        assertEquals(0, robot2.getXCoordinate());
        assertEquals(2, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushingChain1(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(1,0);
        Robot robot2 = new Robot(2,0);
        Robot robot3 = new Robot(3,0, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new DoNothingCard());
        robot3.program(new MoveOneCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(0, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(0, robot2.getYCoordinate());
        assertEquals(2, robot3.getXCoordinate());
        assertEquals(0, robot3.getYCoordinate());
    }

    @Test
    public void testRobotPushingChain1IntoWall(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1);
        Robot robot3 = new Robot(2,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new DoNothingCard());
        robot3.program(new MoveOneCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
        assertEquals(2, robot3.getXCoordinate());
        assertEquals(1, robot3.getYCoordinate());
    }
    
    @Test
    public void testRobotPushesRobot2(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(2,1);
        Robot robot2 = new Robot(3,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall2(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(0,1);
        Robot robot2 = new Robot(1,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotIntoWall2AfterOneStep(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(1,1);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot2.program(new MoveTwoCard());
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot1.getXCoordinate());
        assertEquals(1, robot1.getYCoordinate());
        assertEquals(1, robot2.getXCoordinate());
        assertEquals(1, robot2.getYCoordinate());
    }

    @Test
    public void testRobotPushesRobotOffTheBoard2(){
        Roborally roborally = new Roborally();
        Robot robot1 = new Robot(0,2);
        Robot robot2 = new Robot(1,2, Direction.WEST);
        robot1.program(new DoNothingCard());
        robot1.setRespawnPoint(3, 3);
        robot2.program(new MoveTwoCard());
        robot2.setRespawnPoint(2, 2);
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(3, robot1.getXCoordinate());
        assertEquals(3, robot1.getYCoordinate());
        assertEquals(2, robot2.getXCoordinate());
        assertEquals(2, robot2.getYCoordinate());
    }

    @Test
    public void testTwoRobotMoveInCorrectOrder(){
        Board board = new Board(TESTBOARD4X4);
        Robot robot1 = new Robot(2,1, Direction.SOUTH);
        Robot robot2 = new Robot(3,2, Direction.WEST);
        Roborally roborally = new Roborally(board, robot1);
        roborally.addRobot(robot2);
        robot1.program(0);
        robot2.program(4);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(robot1, roborally.getRobots().get(0));
    }

    @Test
    public void testRobotFiresLaserWest(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(2,1, Direction.WEST);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserNorth(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,2, Direction.NORTH);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserEast(){
        Robot robot1 = new Robot(2,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,1, Direction.EAST);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserSouth(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(4, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserMisses(){
        Robot robot1 = new Robot(1,2, Direction.SOUTH);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotFiresLaserAtWallNorth(){
        Robot robot1 = new Robot(0,0, Direction.WEST);
        Robot robot2 = new Robot(0,1, Direction.NORTH);
        Roborally roborally = new Roborally("ES-SE*ES-SW*||*ES-NE*ES-NW");
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotFiresLaserAtWallEast(){
        Robot robot1 = new Robot(1,0, Direction.WEST);
        Robot robot2 = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally("ES-E*ES-W");
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserAtWallSouth(){
        Robot robot1 = new Robot(0,1, Direction.WEST);
        Robot robot2 = new Robot(0,0, Direction.SOUTH);
        Roborally roborally = new Roborally("ES-SE*ES-SW*||*ES-NE*ES-NW");
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot1.getHealth());
    }
    
    @Test
    public void testRobotFiresLaserAtWallWest(){
        Robot robot1 = new Robot(0,0, Direction.WEST);
        Robot robot2 = new Robot(1,0, Direction.WEST);
        Roborally roborally = new Roborally("ES-E*ES-W");
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(7);
        robot2.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot1.getHealth());
    }

    @Test
    public void testRobotBlocksLaser(){
        Robot robot1 = new Robot(0,1, Direction.SOUTH);
        Robot robot2 = new Robot(0,2, Direction.NORTH);
        Robot robot3 = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally();
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        roborally.addRobot(robot3);
        robot1.program(7);
        robot2.program(7);
        robot3.program(7);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(4, robot1.getHealth());
        assertEquals(9, robot3.getHealth());

    }

    @Test
    public void testRobotPoweredDownDoesNotMove(){
        Robot robot1 = new Robot(0,1, Direction.EAST);
        Robot robot2 = new Robot(2,2, Direction.WEST);
        Roborally roborally = new Roborally("ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*CH-x");
        roborally.addRobot(robot1);
        roborally.addRobot(robot2);
        robot1.program(new MoveOneCard());
        robot2.program(new MoveOneCard());
        robot1.turnOnOrOff();
        roborally.playRoundIfAllRobotsReady();
        robot2.program(new MoveOneCard());
        roborally.playRoundIfAllRobotsReady();
        assertEquals(0, robot2.getXCoordinate());
        assertEquals(1, robot1.getXCoordinate());
    }

    @Test
    public void testRobotReactivates(){
        Robot robot = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally("ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*CH-x");
        robot.program(new MoveOneCard());
        robot.turnOnOrOff();
        roborally.addRobot(robot);
        roborally.playRoundIfAllRobotsReady();
        roborally.playRoundIfAllRobotsReady();
        robot.program(new MoveOneCard());
        roborally.playRoundIfAllRobotsReady();
        assertEquals(2, robot.getXCoordinate());
    }

    @Test
    public void testRobotRepairsAfterPowerdown(){
        Robot robot = new Robot(0,0, Direction.EAST);
        Roborally roborally = new Roborally("ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*CH-x");
        robot.program(new MoveOneCard());
        robot.turnOnOrOff();
        robot.takeDamage(4);
        assertEquals(5, robot.getHealth());
        roborally.addRobot(robot);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(9, robot.getHealth());
    }

}