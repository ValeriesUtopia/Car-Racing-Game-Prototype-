package model;


import java.util.List;
import java.util.ArrayList;

public class TrackFactory {

    private static void autoPlaceObstacles(Track track, int numberOfObstacles, double radius) {
        List<Stop> stops = track.getStops();
        int stopCount = stops.size();
        int interval = stopCount / numberOfObstacles;

        for (int i = 0; i < numberOfObstacles; i++) {
            int index = (i * interval) % stopCount;
            Stop s1 = stops.get(index);
            Stop s2 = stops.get((index + 1) % stopCount);

            double midX = (s1.getX() + s2.getX()) / 2.0;
            double midY = (s1.getY() + s2.getY()) / 2.0;

            track.addObstacle(new SlipperyObstacle(midX, midY, radius));
        }
    }

    public static Track createEasyTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 300, 150));
        stops.add(new Stop("B", 700, 150));
        stops.add(new Stop("C", 850, 350));
        stops.add(new Stop("D", 700, 550));
        stops.add(new Stop("E", 300, 550));
        stops.add(new Stop("F", 150, 350));

        Track track = new Track("Easy Track", Track.Difficulty.EASY, stops);
        autoPlaceObstacles(track, 1, 40);
        return track;
    }

    public static Track createMediumTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 200, 100));
        stops.add(new Stop("B", 400, 200));
        stops.add(new Stop("C", 600, 100));
        stops.add(new Stop("D", 800, 200));
        stops.add(new Stop("E", 700, 400));
        stops.add(new Stop("F", 500, 500));
        stops.add(new Stop("G", 300, 400));
        stops.add(new Stop("H", 100, 300));

        Track track = new Track("Medium Track", Track.Difficulty.MEDIUM, stops);
        autoPlaceObstacles(track, 3, 40);
        return track;
    }

    public static Track createChallengeTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 150, 150));
        stops.add(new Stop("B", 230, 172));
        stops.add(new Stop("C", 310, 150));
        stops.add(new Stop("D", 390, 180));
        stops.add(new Stop("E", 470, 150));
        stops.add(new Stop("F", 550, 180));
        stops.add(new Stop("G", 630, 150));
        stops.add(new Stop("H", 710, 187));
        stops.add(new Stop("I", 790, 247));
        stops.add(new Stop("J", 774, 330));
        stops.add(new Stop("K", 710, 375));
        stops.add(new Stop("L", 646, 390));
        stops.add(new Stop("M", 582, 405));
        stops.add(new Stop("N", 518, 420));
        stops.add(new Stop("O", 454, 435));
        stops.add(new Stop("P", 390, 450));
        stops.add(new Stop("Q", 454, 465));
        stops.add(new Stop("R", 518, 480));
        stops.add(new Stop("S", 582, 495));
        stops.add(new Stop("T", 646, 510));
        stops.add(new Stop("U", 710, 525));
        stops.add(new Stop("V", 774, 540));
        stops.add(new Stop("W", 854, 547));

        Track track = new Track("Challenge Track", Track.Difficulty.CHALLENGE, stops);
        autoPlaceObstacles(track, 9, 25);
        return track;
    }
}
