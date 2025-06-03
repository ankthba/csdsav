import java.util.*;

public class Explorer {
    public static void explore(int startX, int startY, int endX, int endY) {
        List<String> path = new ArrayList<>();
        exploreHelper(startX, startY, endX, endY, path);
    }

    private static void exploreHelper(int currentX, int currentY, int endX, int endY, List<String> path) {
        if (currentX == endX && currentY == endY) {
            System.out.println(String.join(" -> ", path));
            return;
        }

        if (currentX > endX || currentY > endY) {
            return;
        }

        path.add("N");
        exploreHelper(currentX, currentY + 1, endX, endY, path);
        path.remove(path.size() - 1);

        path.add("E");
        exploreHelper(currentX + 1, currentY, endX, endY, path);
        path.remove(path.size() - 1);

        path.add("NE");
        exploreHelper(currentX + 1, currentY + 1, endX, endY, path);
        path.remove(path.size() - 1);
    }

    public static int exploreCount(int startX, int startY, int endX, int endY) {
        if (startX == endX && startY == endY) {
            return 1;
        }

        if (startX > endX || startY > endY) {
            return 0;
        }

        int count = 0;
        count += exploreCount(startX, startY + 1, endX, endY);
        count += exploreCount(startX + 1, startY, endX, endY); 
        count += exploreCount(startX + 1, startY + 1, endX, endY);

        return count;
    }

    public static boolean exploreExists(int startX, int startY, int endX, int endY) {
        return startX == endX && startY == endY || 
               (startX <= endX && startY <= endY && 
               (exploreExists(startX, startY + 1, endX, endY) ||
                exploreExists(startX + 1, startY, endX, endY) ||
                exploreExists(startX + 1, startY + 1, endX, endY)));
    }

    public static void exploreExistsWithPath(int startX, int startY, int endX, int endY, List<String> path) {
        if (startX == endX && startY == endY) {
            System.out.println("Path: " + String.join(" -> ", path));
            return;
        }

        if (startX > endX || startY > endY) {
            return;
        }
        path.add("N");
        exploreExistsWithPath(startX, startY + 1, endX, endY, path);
        path.remove(path.size() - 1);

        path.add("E");
        exploreExistsWithPath(startX + 1, startY, endX, endY, path);
        path.remove(path.size() - 1);

        path.add("NE");
        exploreExistsWithPath(startX + 1, startY + 1, endX, endY, path);
        path.remove(path.size() - 1);
    }
}