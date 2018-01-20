package com.padc.nyinyi.padcburppleapp.dagger;

import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgent;
import com.padc.nyinyi.padcburppleapp.networks.BurppleDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/7/18.
 */


@Module
public class NetworkModule {

    @Provides
    @Singleton // obj copy ta ku pl shi chin lo singleton use tr
    public BurppleDataAgent provideBurppleDataAgent() {
        return new BurppleDataAgentImpl();
    }

}
