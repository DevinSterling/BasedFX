package com.devinsterling.basedfx.util;

import javafx.application.HostServices;

public class Context {
    private final HostServices hostServices;

    public Context(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public HostServices getHostServices() {
        return hostServices;
    }
}
