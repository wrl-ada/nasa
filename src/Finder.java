public class Finder {
    private int x;
    private int y;
    private String direction;

    public Finder(int x, int y, String direction) {
        this.x=x;
        this.y=y;
        this.direction=direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x=x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y=y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction=direction;
    }

    public int moveWest(){
        return x--;
    }

    public int moveEast(){
        return x++;
    }

    public int moveNorth(){
        return y++;
    }

    public int moveSouth(){
        return y--;
    }

    public String toString(){
        return x+" "+y+" "+direction;
    }

}
