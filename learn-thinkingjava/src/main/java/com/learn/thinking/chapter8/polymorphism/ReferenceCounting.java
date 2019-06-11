package com.learn.thinking.chapter8.polymorphism;

class Shard {

    private int refcount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shard() {
        System.out.println("Creating " + this);
    }

    public void addRef() {
        refcount++;
    }

    protected void dispose() {
        if (--refcount == 0) {
            System.out.println("Disposing " + this);
        }
    }

    @Override
    public String toString() {
        return "Shard " + id;
    }
}

class Composing {
    private Shard sharded;
    private static long counter = 0;
    private final long id = counter++;

    public Composing(Shard sharded) {
        System.out.println("Creating " + this);
        this.sharded = sharded;
        this.sharded.addRef();
    }

    protected void dispose() {
        System.out.println("disposing " + this);
        sharded.dispose();
    }

    @Override
    public String toString() {
        return "Composing " + id;
    }
}

/**
 * @author win10
 */
public class ReferenceCounting {
    public static void main(String[] args) {
        Shard shard = new Shard();
        Composing[] composing = {new Composing(shard), new Composing(shard),
                new Composing(shard), new Composing(shard), new Composing(shard)};
        for (Composing c : composing) {
            c.dispose();
        }
    }
}
/*
Output:
Creating Shard 0
Creating Composing 0
Creating Composing 1
Creating Composing 2
Creating Composing 3
Creating Composing 4
disposing Composing 0
disposing Composing 1
disposing Composing 2
disposing Composing 3
disposing Composing 4
Disposing Shard 0
 */