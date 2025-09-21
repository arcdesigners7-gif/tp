package com.tp;

import net.fabricmc.api.ModInitializer;
import com.tp.net.TpNetworking;

public class Tp implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("Tp mod initializing");
        TpNetworking.registerServer();
    }
}
