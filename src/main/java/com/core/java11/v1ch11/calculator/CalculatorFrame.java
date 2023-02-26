package com.core.java11.v1ch11.calculator;

import javax.swing.*;

/**
 * A frame with a calculator panel.
 */
public class CalculatorFrame extends JFrame
{
   public CalculatorFrame()
   {
      add(new CalculatorPanel());
      pack();
   }
}
