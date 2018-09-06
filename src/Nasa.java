import java.util.*;

public class Nasa {
    //设置坐标右上方默认位置
    private static int rows=0;
    private static int cols=0;
    private String[] dirArr={"N", "W", "S", "E"};

    /**
     * 探测器移动
     *
     * @param finder
     * @param command
     * @return
     */
    private Finder move(Finder finder, String command) {
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

    private void moveWestAction(Finder finder) {
        finder.moveWest ();
        if (!outOfBounds ( finder )) {
            finder.setX ( finder.getX () );
        }
    }

    private void moveEastAction(Finder finder) {
        finder.moveEast ();
        if (!outOfBounds ( finder )) {
            finder.setX ( finder.getX () );
        }
    }

    private void moveNorthAction(Finder finder) {
        finder.moveNorth ();
        if (!outOfBounds ( finder )) {
            finder.setY ( finder.getY () );
        }
    }

    private void moveSourthAction(Finder finder) {
        finder.moveSouth ();
        if (!outOfBounds ( finder )) {
            finder.setY ( finder.getY () );
        }
    }

    /**
     * 检查探测器是否出界
     *
     * @param finder
     * @return
     */
    private boolean outOfBounds(Finder finder) {
        int x=finder.getX ();
        int y=finder.getY ();
        if (x < 0 || x > rows || y < 0 || y > cols) {
            System.err.print ( "Finder out of bounds" );
            return true;
        }
        return false;
    }

    /**
     * 根据用户输入获取坐标图的最右上方坐标，并赋值给rows和cols
     *
     * @param arr
     */
    private void getCoordinate(String[] arr) {
        rows=Integer.parseInt ( arr[0] );
        cols=Integer.parseInt ( arr[1] );
    }

    /**
     * 根据用户输入获取探测器对象
     *
     * @param obj
     * @return
     */
    private Finder getFinder(String[] obj) {
        Finder finder=null;
        finder=new Finder ( Integer.parseInt ( obj[0] ), Integer.parseInt ( obj[1] ), obj[2] );
        return finder;
    }

    /**
     * 根据指令探测器完成移动，返回最终位置
     *
     * @param finder
     * @param orderStr
     * @return
     */
    public synchronized void action(Finder finder, String orderStr) {
        for (String command : orderStr.split ( "" )) {
            finder=move ( finder, command );
            //System.out.print ( command + ": " + finder.toString () + "\n" );
        }
        System.out.print ( finder.toString () + "\n" );
    }

    public static void main(String[] args) {
        Nasa nasa=new Nasa ();
        Scanner console=new Scanner ( System.in );
        HashMap<Finder, String> mapObj=new TreeMap<> ();
        System.out.print ( "Please input:\n" );
        String content=null;
        while (!"".equals ( content=console.nextLine () )) {
            String[] arr=content.split ( " " );
            if (arr.length == 2) {
                nasa.getCoordinate ( arr );
            } else if (arr.length == 3) {
                Finder finder=nasa.getFinder ( arr );
                String order=console.nextLine ();
                if (!"".equals ( order.trim () )) {
                    mapObj.put ( finder, order );
                } else {
                    System.out.print ( "Please input order:\n" );
                }
            }
        }
        System.out.print ( "Result:\n" );
        mapObj.forEach ( (key, value) -> {
            nasa.action ( key, value );
        } );
    }
}
