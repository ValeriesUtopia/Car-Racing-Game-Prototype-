package model;


import java.util.List;
import java.util.ArrayList;

public class TrackFactory {

    private static void autoPlaceObstacles(Track track, int numberOfObstacles, double radius) {
        List<Stop> stops = track.getStops();
        int stopCount = stops.size();

        for (int i = 0; i < numberOfObstacles; i++) {
            int index = i % stopCount;
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
        stops.add(new Stop("A", 100, 100));
        stops.add(new Stop("B", 300, 200));
        stops.add(new Stop("C", 500, 100));
        stops.add(new Stop("D", 700, 200));
        stops.add(new Stop("E", 900, 100));
        stops.add(new Stop("F", 800, 400));
        stops.add(new Stop("G", 500, 600));
        stops.add(new Stop("H", 200, 500));

        Track track = new Track("Challenge Track", Track.Difficulty.CHALLENGE, stops);
        autoPlaceObstacles(track, 5, 30);
        return track;
    }
}



/*原来版本，但是不满的版本
public class TrackFactory {

    public static Track createEasyTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 100, 100));
        stops.add(new Stop("B", 300, 100));
        stops.add(new Stop("C", 500, 100));

        Track track = new Track("Easy Track", Track.Difficulty.EASY, stops);
        // No obstacles or minimal ones
        return track;
    }

    public static Track createMediumTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 100, 100));
        stops.add(new Stop("B", 200, 200));
        stops.add(new Stop("C", 300, 100));
        stops.add(new Stop("D", 400, 200));

        Track track = new Track("Medium Track", Track.Difficulty.MEDIUM, stops);
        track.addObstacle(new SlipperyObstacle(250, 150, 40));
        return track;
    }

    public static Track createChallengeTrack() {
        List<Stop> stops = new ArrayList<>();
        stops.add(new Stop("A", 100, 100));
        stops.add(new Stop("B", 150, 200));
        stops.add(new Stop("C", 200, 100));
        stops.add(new Stop("D", 250, 200));
        stops.add(new Stop("E", 300, 100));

        Track track = new Track("Challenge Track", Track.Difficulty.CHALLENGE, stops);
        track.addObstacle(new SlipperyObstacle(140, 180, 30));
        track.addObstacle(new SlipperyObstacle(260, 180, 30));
        return track;
    }
}

 */
