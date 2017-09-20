package com.github.willcode4fun.tools.term;

import org.junit.Test;

/**
 * Created by DECOSTT on 21/07/2017.
 */
public class TermIT {

    class MyModel{
        int i = 0;
    }

    @Test
    public void should_run_terminal(){
        new Term<MyModel>(new MyModel()) {
            @Override
            public void paint(MyModel model) {
               System.out.print(model.i);
            }
        }
        .addCallback("a", (model)->  model.i++)
        .addCallback("z", (model)->  model.i--)
        .start();
    }
}
