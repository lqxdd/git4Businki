package com.company;
import java.util.ArrayList;

public class DataManager {
    public static class Element {

        public String name;
        public int value;

        public Element(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public ArrayList<Element> elements = new ArrayList<Element>();

    public ArrayList<Element> getNormalized(double boundary) {
        double sum = 0;
        for (Element e : elements) {
            sum += e.value;
        }

        ArrayList<Element> result = new ArrayList<Element>();

        for (Element e : elements) {
            result.add(new Element(e.name, (int) (e.value * boundary / sum)));
        }

        return result;
    }

}
