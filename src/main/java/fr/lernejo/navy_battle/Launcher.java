package fr.lernejo.navy_battle;

public class Launcher {


    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.startServer();
        if (args.length > 1) {
            String url = args[1];
            server.connectAdversary(port, url);
        }

    }
}

