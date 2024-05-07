package app;

import app.config.NettyServerProvider;
import app.controller.ContactController;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER =
            Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws MalformedURLException {

        // У якості параметру методу NettyServerProvider.startHttpServer
        // може передаватися один або більше ресурсів (*Controller.class)
        final String httpServer = NettyServerProvider.startHttpServer(
               ContactController.class);

        LOGGER.log(Level.INFO, httpServer);
    }
}
