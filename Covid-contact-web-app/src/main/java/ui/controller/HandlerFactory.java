package ui.controller;

import domain.service.ContactTracingService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, ContactTracingService model) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            // Java 10
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception exc) {
            throw new RuntimeException("Sorry, deze pagina bestaat niet!");
        }
        return handler;
    }

}
