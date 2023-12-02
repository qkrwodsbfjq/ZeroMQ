package org.example.server;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import static java.lang.Thread.sleep;

public class ReqRepBasicServer {
    public static void start() throws Exception {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");

            while (true) {
                byte[] reply = socket.recv();
                System.out.println("Received request: " + new String(reply, ZMQ.CHARSET));

                sleep(1000);

                String response = "World";
                socket.send(response.getBytes(ZMQ.CHARSET));
            }
        }
    }
}
