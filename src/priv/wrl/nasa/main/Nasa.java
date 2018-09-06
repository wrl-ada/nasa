package priv.wrl.nasa.main;

import priv.wrl.nasa.action.FinderAction;
import priv.wrl.nasa.entity.Finder;

import java.util.*;

public class Nasa {
    //设置坐标右上方默认位置
    private static int rows=0;
    private static int cols=0;

    /**
     * 探测器越界检查
     *
     * @param finder
     * @return
     */
    public static boolean outOfBounds(Finder finder) {
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
            finder=FinderAction.move ( finder, command );
            //System.out.print ( command + ": " + finder.toString () + "\n" );
        }
        System.out.print ( finder.toString () + "\n" );
    }

    public static void main(String[] args) {
        Nasa nasa=new Nasa ();
        Scanner console=new Scanner ( System.in );
        // 存放探测器对象及其要执行指令
        HashMap<Finder, String> mapObj=new LinkedHashMap<> ();
        System.out.print ( "Please input:\n" );
        String content=null;
        // 循环获取用户输入内容
        while (!"".equals ( content=console.nextLine () )) {
            String[] arr=content.split ( " " );
            if (arr.length == 2) {
                // 设置坐标的最右上方坐标
                nasa.getCoordinate ( arr );
            } else if (arr.length == 3) {
                // 获取探测器的起始位置
                Finder finder=nasa.getFinder ( arr );
                // 获取该探测器要执行的指令
                String order=console.nextLine ();
                if (order.split ( " " ).length == 1) {
                    mapObj.put ( finder, order );
                } else {
                    System.out.print ( "Please input order:\n" );
                }
            }
        }
        System.out.print ( "Result:\n" );
        mapObj.forEach ( (key, value) -> {
            // 探测器根据指令移动
            nasa.action ( key, value );
        } );
    }
}
