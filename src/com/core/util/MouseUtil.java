package com.core.util;

import com.core.common.Config;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

/**
 * @author Celphis
 */
@SuppressWarnings("ALL")
public class MouseUtil {
    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static boolean click(String img) throws InterruptedException {
        Point point = PointUtil.getPoint(img);
        if (point != null) {
            click(point);
            robot.delay(TimeAndRandomUtil.random(Config.CLICK_WAIT, 20));
            return true;
        }
        return false;
    }

    public static boolean click(String img, double precision) throws InterruptedException {
        Point point = PointUtil.getPoint(img, precision);
        if (point != null) {
            click(point);
            robot.delay(TimeAndRandomUtil.random(Config.CLICK_WAIT, 20));
            return true;
        }
        return false;
    }

    public static boolean rightClick(String img) throws InterruptedException {
        Point point = PointUtil.getPoint(img);
        if (point != null) {
            rightClick(point);
            robot.delay(TimeAndRandomUtil.random(Config.CLICK_WAIT, 20));
            return true;
        }
        return false;
    }

    public static boolean doubleClick(String img) throws InterruptedException {
        Point point = PointUtil.getPoint(img);
        if (point != null) {
            click(point);
            robot.delay(TimeAndRandomUtil.random(50, 20));
            click(point);
            robot.delay(TimeAndRandomUtil.random(Config.CLICK_WAIT, 20));
            return true;
        }
        return false;
    }

    public static void moveTo(Point to) throws InterruptedException {
        robot.mouseMove(to.x, to.y);
    }

    public static void press() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public static void release() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public static void dragTo(Point from, Point to) throws InterruptedException {
        moveTo(from);
        press();
        for (int i = 0; i < 100; i++) {
            int x = ((to.x * i) / 100) + (from.x * (100 - i) / 100);
            int y = ((to.y * i) / 100) + (from.y * (100 - i) / 100);
            robot.mouseMove(x, y);
            robot.delay(1 + new Random().nextInt(4));
        }
        release();
    }

    public static void click(Point point) throws InterruptedException {
        moveTo(point);
        press();
        robot.delay(TimeAndRandomUtil.random(50, 20));
        release();
    }

    public static void rightClick(Point point) throws InterruptedException {
        moveTo(point);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(TimeAndRandomUtil.random(50, 20));
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(TimeAndRandomUtil.random(Config.CLICK_WAIT, 20));
    }
}
