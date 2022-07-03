package com.game.fluidfill.territorycolor;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.LinkedList;
import java.util.Queue;

class FluidFillAlgorithm {

    private static int width;
    private static int height;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    static void fluidFillTerritory(Bitmap image, Point node, int x, int y,int oldColor , int newColor){
        width = image.getWidth();
        height = image.getHeight();
        if (oldColor != newColor) {
            Queue<Point> queue = new LinkedList<Point>();
            do {
                x = node.x;
                y = node.y;
                while (x > 0 && image.getPixel(x - 1, y) == oldColor) {
                    x--;
                }
                boolean spanUp = false;
                boolean spanDown = false;
                while (x < width && image.getPixel(x, y) == oldColor) {
                    image.setPixel(x, y, newColor);
                    if (!spanUp && y > 0 && image.getPixel(x, y - 1) == oldColor) {
                        queue.add(new Point(x, y - 1));
                        spanUp = true;
                    } else if (spanUp && y > 0
                            && image.getPixel(x, y - 1) != oldColor) {
                        spanUp = false;
                    }
                    if (!spanDown && y < height - 1
                            && image.getPixel(x, y + 1) == oldColor) {
                        queue.add(new Point(x, y + 1));
                        spanDown = true;
                    } else if (spanDown && y < height - 1
                            && image.getPixel(x, y + 1) != oldColor) {
                        spanDown = false;
                    }
                    x++;
                }
            } while ((node = queue.poll()) != null);
        }
    }




}
