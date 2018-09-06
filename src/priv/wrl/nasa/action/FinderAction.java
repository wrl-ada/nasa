package priv.wrl.nasa.action;

import priv.wrl.nasa.entity.Finder;

import static priv.wrl.nasa.main.Nasa.outOfBounds;

public class FinderAction {
    /**
     * 探测器移动
     *
     * @param finder
     * @param command
     * @return
     */
    public static Finder move(Finder finder, String command) {
        switch (finder.getDirection ()) {
            case "N":
                if ("L".equals ( command )) {
                    finder.setDirection ( "W" );
                } else if ("R".equals ( command )) {
                    finder.setDirection ( "E" );
                } else {
                    moveNorthAction ( finder );
                }
                break;
            case "W":
                if ("L".equals ( command )) {
                    finder.setDirection ( "S" );
                } else if ("R".equals ( command )) {
                    finder.setDirection ( "N" );
                } else {
                    moveWestAction ( finder );
                }
                break;
            case "S":
                if ("L".equals ( command )) {
                    finder.setDirection ( "E" );
                } else if ("R".equals ( command )) {
                    finder.setDirection ( "W" );
                } else {
                    moveSourthAction ( finder );
                }
                break;
            case "E":
                if ("L".equals ( command )) {
                    finder.setDirection ( "N" );
                } else if ("R".equals ( command )) {
                    finder.setDirection ( "S" );
                } else {
                    moveEastAction ( finder );
                }
                break;
        }
        return finder;
    }

    /**
     * 向西移动一步
     *
     * @param finder
     */
    private static void moveWestAction(Finder finder) {
        finder.moveWest ();
        if (!outOfBounds ( finder )) {
            finder.setX ( finder.getX () );
        }
    }

    /**
     * 向东移动一步
     *
     * @param finder
     */
    private static void moveEastAction(Finder finder) {
        finder.moveEast ();
        if (!outOfBounds ( finder )) {
            finder.setX ( finder.getX () );
        }
    }

    /**
     * 向北移动一步
     *
     * @param finder
     */
    private static void moveNorthAction(Finder finder) {
        finder.moveNorth ();
        if (!outOfBounds ( finder )) {
            finder.setY ( finder.getY () );
        }
    }

    /**
     * 向南移动一步
     *
     * @param finder
     */
    private static void moveSourthAction(Finder finder) {
        finder.moveSouth ();
        if (!outOfBounds ( finder )) {
            finder.setY ( finder.getY () );
        }
    }

}
