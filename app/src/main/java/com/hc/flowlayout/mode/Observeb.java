package com.hc.flowlayout.mode;

import com.hc.flowlayout.mode.Observe;

import java.util.ArrayList;
import java.util.List;

public class Observeb {
    List<Observe> list = new ArrayList<>();
    public void addObserve(Observe observe){
        list.add(observe);
    }
    public void removeObserve(Observe observe){
        list.remove(observe);
    }
    public void notifyObserves(String string){
        for(Observe observe:list){
            observe.receive(string);
        }
    }
}
