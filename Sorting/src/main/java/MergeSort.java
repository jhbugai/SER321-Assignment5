import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class MergeSort {
    /**
     * Thread that declares the lambda and then initiates the work
     */

    public static int message_id = 0;

    public static JSONObject init(int[] array) {
        JSONArray arr = new JSONArray();
        for (var i : array) {
            arr.put(i);
        }
        JSONObject req = new JSONObject();
        req.put("method", "init");
        req.put("data", arr);
        return req;
    }

    public static JSONObject peek() {
        JSONObject req = new JSONObject();
        req.put("method", "peek");
        return req;
    }

    public static JSONObject remove() {
        JSONObject req = new JSONObject();
        req.put("method", "remove");
        return req;
    }

    public static void Test(int port, String host, char array) {
        JSONObject response = null;
        if (array == 'a') {
            int[] a = { 5, 1, 6, 2, 3, 4, 10,634,34,23,653, 23,2 ,6 };
            System.out.println("Sort array 'a'");
            System.out.println(host + ":" + port);
            response = NetworkUtils.send(port, host, init(a));
        } else if(array == 'b') {
            int[] b = new int[100];
            for (int i = 0; i < 100; i++) {
                b[i] = (int) ((Math.random() * 100));
            }
            response = NetworkUtils.send(port, host, init(b));
        } else if (array == 'c') {
            int[] c = new int[1000];
            for (int j = 0; j < 1000; j++) {
                c[j] = (int) ((Math.random() * 1000));
            }
            response = NetworkUtils.send(port, host, init(c));
        }

        System.out.println(response);
        response = NetworkUtils.send(port, host, peek());
        System.out.println(response);

        while (true) {
            response = NetworkUtils.send(port, host, remove());

            if (response.getBoolean("hasValue")) {
                System.out.println(response);
            } else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        // all the listening ports in the setup
        //ArrayList<Integer> ports = new ArrayList<>();

        String node = args[0];
        String host = args[1];
        int port = Integer.parseInt(args[2]);

        if (node.equalsIgnoreCase("branch")) {
            new Thread(new Branch(port, host, port + 1, port + 2)).start();
            System.out.println("Started");

            Scanner scan = new Scanner(System.in);
            boolean waiting = true;
            while (waiting) {
                System.out.println("Type 'done' when connections are established...");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("done")) {
                    waiting = false;
                } else {
                    waiting = true;
                }
            }

            System.out.println("Beginning sort on array 'a'");
            long startTime = System.currentTimeMillis();
            Test(port, host, 'a');
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("RESULTS:");
            System.out.println("TEST : 1 branch / 2 sorters 14 Entry Array\nDuration: " + duration + " ms");

        } else if (node.equalsIgnoreCase("sorter")) {
        new Thread(new Sorter(port)).start();
            System.out.println("Created new sorter");
    } else {
        System.out.println("WRONG");
        System.exit(0);
    }

        // make sure we didn't hang


//        new Thread(new Branch(ports.get(0), ports.get(1), ports.get(2))).start();
//
//        new Thread(new Branch(ports.get(1), ports.get(3), ports.get(4))).start();
//        new Thread(new Sorter(ports.get(3))).start();
//        new Thread(new Sorter(ports.get(4))).start();
//
//        new Thread(new Branch(ports.get(2), ports.get(5), ports.get(6))).start();
//        new Thread(new Sorter(ports.get(5))).start();
//        new Thread(new Sorter(ports.get(6))).start();
//
//        //2 Branches / 3 Sorters
//        new Thread(new Branch(ports.get(7), ports.get(8), ports.get(2))).start();
//        new Thread(new Sorter(ports.get(8))).start();
//
//        new Thread(new Branch(ports.get(10), ports.get(1), ports.get(7))).start();

//        // One Sorter / Array 'a'
//        long startTime = System.currentTimeMillis();
//        Test(ports.get(3), 'a');
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//        System.out.println("TEST : 1 Sorter / 14 Entry Array\nDuration: " + duration + " ms");
//
//        // One Sorter / Array 'b'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(3), 'b');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 1 Sorter / 100 Entry Array\nDuration: " + duration + " ms");
//
//        // One Sorter / Array 'c'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(3), 'c');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 1 Sorter / 1000 Entry Array\nDuration: " + duration + " ms");
//
//        // One branch / Two Sorters / Array 'a'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(2), 'a');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 1 Branch / 2 Sorters / 14 Entry Array\nDuration: " + duration + " ms");
//
//        // One branch / Two Sorters / Array 'b'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(2), 'b');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 1 Branch / 2 Sorters / 100 Entry Array\nDuration: " + duration + " ms");
//
//        // One branch / Two Sorters / Array 'c'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(2), 'c');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 1 Branch / 2 Sorters / 1000 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Four Sorters / Array 'a'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(0), 'a');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 4 sorters / 14 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Four Sorters / Array 'b'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(0), 'b');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 4 sorters / 100 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Four Sorters / Array 'c'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(0), 'c');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 4 sorters / 1000 Entry Array\nDuration: " + duration + " ms");
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////////
//
//        // Two Branch / Three Sorters / Array 'a'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(7), 'a');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 2 branch / 3 sorters / 14 Entry Array\nDuration: " + duration + " ms");
//
//        // Two Branch / Three Sorters / Array 'b'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(7), 'b');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 2 branch / 3 sorters / 100 Entry Array\nDuration: " + duration + " ms");
//
//        // Two Branch / Three Sorters / Array 'c'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(7), 'c');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 2 branch / 3 sorters / 1000 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Five Sorters / Array 'a'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(10), 'a');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 5 sorters / 14 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Five Sorters / Array 'b'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(10), 'b');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 5 sorters / 100 Entry Array\nDuration: " + duration + " ms");
//
//        // Three Branch / Five Sorters / Array 'c'
//        startTime = System.currentTimeMillis();
//        Test(ports.get(10), 'c');
//        endTime = System.currentTimeMillis();
//        duration = endTime - startTime;
//        System.out.println("TEST : 3 branch / 5 sorters / 1000 Entry Array\nDuration: " + duration + " ms");
    }
}
