/**
 * Run all JUnit tests as a suite.
 * 
 * @author Yong Shin
 *
 */

package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@SuiteClasses( {
  PieceTest.class, 
  BoardTest.class } )
public class AllTests
{
  public static Test suite()
  {
    return new JUnit4TestAdapter(AllTests.class);
  }
}
