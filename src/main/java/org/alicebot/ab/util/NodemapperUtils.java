package org.alicebot.ab.util;

import org.alicebot.ab.Nodemapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NodemapperUtils {

    /**
     * number of branches from node
     *
     * @param node Nodemapper object
     * @return number of branches
     */
    public static int size(Nodemapper node) {
        HashSet<String> set = new HashSet<>();
        if (node.isShortCut()) {
            set.add("<THAT>");
        }
        if (node.getKey() != null) {
            set.add(node.getKey());
        }
        if (node.getMap() != null) {
            set.addAll(node.getMap().keySet());
        }
        return set.size();
    }

    /**
     * insert a new link from this node to another, by adding a key, value pair
     *
     * @param node  Nodemapper object
     * @param key   key word
     * @param value word maps to this next node
     */
    public static void put(Nodemapper node, String key, Nodemapper value) {
        if (node.getMap() != null) {
            node.getMap().put(key, value);
        } else { // node.type == unary_node_mapper
            node.setKey(key);
            node.setValue(value);
        }
    }

    /**
     * get the node linked to this one by the word key
     *
     * @param node Nodemapper object
     * @param key  key word to map
     * @return the mapped node or null if the key is not found
     */
    public static Nodemapper get(Nodemapper node, String key) {
        if (node.getMap() != null) {
            return node.getMap().get(key);
        } else {// node.type == unary_node_mapper
            return key.equals(node.getKey()) ? node.getValue() : null;
        }
    }

    /**
     * check whether a node contains a particular key
     *
     * @param node Nodemapper object
     * @param key  key to test
     * @return true or false
     */
    public static boolean containsKey(Nodemapper node, String key) {
        if (node.getMap() != null) {
            return node.getMap().containsKey(key);
        } else {// node.type == unary_node_mapper
            return key.equals(node.getKey());
        }
    }

    /**
     * get key set of a node
     *
     * @param node Nodemapper object
     * @return set of keys
     */
    public static Set<String> keySet(Nodemapper node) {
        if (node.getMap() != null) {
            return node.getMap().keySet();
        } else {// node.type == unary_node_mapper
            Set<String> set = new HashSet<>();
            if (node.getKey() != null) {
                set.add(node.getKey());
            }
            return set;
        }
    }

    /**
     * test whether a node is a leaf
     *
     * @param node Nodemapper object
     * @return true or false
     */
    public static boolean isLeaf(Nodemapper node) {
        return (node.getCategory() != null);
    }

    /**
     * upgrade a node from a singleton to a multi-way map
     *
     * @param node Nodemapper object
     */
    public static void upgrade(Nodemapper node) {
        node.setMap(new HashMap<>());
        node.getMap().put(node.getKey(), node.getValue());
        node.setKey(null);
        node.setValue(null);
    }
}
