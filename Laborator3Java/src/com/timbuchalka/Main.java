package com.timbuchalka;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Hotel v0 = new Hotel ("Hotel1");
        Node v1 = new Hotel("HCalifornia");
        Node v2 = new Hotel("HParis");
        Museum v3 = new Museum("MLuvru");
        Museum v4 = new Museum("MBrukenthal");
        Restaurant v5 = new Restaurant("RLebada");
        Restaurant v6 = new Restaurant("RGradina");
        Church v7 = new Church ("CPavel");
        Church v8 = new Church ("CPaul");

        v6.setRank (456);

        HourAndMinute h1 = new HourAndMinute(13, 15);
        v3.setOpenTime (h1);
        v3.setCloseTime (h1);
        Visitable.printTime (v3.openingTime, v3.closingTime);
        Visitable.printDuration (Visitable.getVisitingDuration(v3.openingTime, v3.closingTime));

        v3.defaultInitialization ();
        Visitable.printTime (v3.openingTime, v3.closingTime);
        Duration d = Visitable.getVisitingDuration(v3.openingTime, v3.closingTime);
        Visitable.printDuration (d);

        v0.setEntryFee (35);
        ((Hotel) v1).setEntryFee (35);
        ((Hotel) v2).setEntryFee (35);
        v3.setEntryFee (35);
        v4.setEntryFee (35);


        TravelMap map = new TravelMap();
        map.addNode(v0);
        map.addNode(v1);
        map.addNode(v2);
        map.addNode(v3);
        map.addNode(v4);
        map.addNode(v5);
        map.addNode(v6);
        map.addNode(v7);
        map.addNode(v8);


        map.addEdge(v1, v2, 15, true); //two way street by default
        map.addEdge(v1, v3, 10, true); //one-way street
        map.addEdge(v3, v2, 1, false);
        map.addEdge(v0, v7, 2, false);
        map.addEdge(v4, v5, 1);
        map.addEdge(v5, v6, 1);
        map.addEdge(v2, v6, 10);
        map.addEdge(v7, v8, 100);
        map.addEdge(v2, v0, 100);


        System.out.println("The map is: \n" + map.getNodesNames() + '\n');

        System.out.println (map.toString ());

        ShortestPath shortestPath = new ShortestPath (map);
        shortestPath.printGraph ();

        System.out.println (shortestPath.getDistanceBetween(1, 2));
        System.out.println (shortestPath.getDistanceBetween(v1, v2));
        System.out.println (shortestPath.getDistanceBetween(v7, v8));
        shortestPath.printDistancesBetweenAny ();


        map.getNodes()
                .stream()
                .filter(node -> (node instanceof Visitable))
                .filter(node -> !(node instanceof Payable))
                .sorted(Comparator.comparing (a -> String.valueOf(((Visitable) a).getOpenTime ().getHour ())))
                .forEach(s -> System.out.println(s.getName()));

        map.getNodes()
                .stream()
                .filter(node -> (node instanceof Payable))
                //.mapToInt(Node::getEntryFee)
                //.average()
                //.getAsDouble();
            .mapToDouble (Node::getEntryFee)
            .average()
            //.getAsDouble ()
            .ifPresent(System.out::println);

    }
}
