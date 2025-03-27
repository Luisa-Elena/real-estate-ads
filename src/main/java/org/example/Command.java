package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


public interface Command {

    public void execute(CommandParams commandParams);
}
