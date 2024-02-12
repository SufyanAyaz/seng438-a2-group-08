package org.jfree.data.test;

import static org.junit.Assert.*;

//import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	private Mockery mockingContext;
	private Mockery mockingContexts;
	private Values2D values;
	private KeyedValues keyedValues;
	
	@Before
	public void setUp() {
        mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
    }
	
	@Before
	public void setUp1() {
        mockingContexts = new Mockery();
        keyedValues = mockingContexts.mock(KeyedValues.class);
    }
	
	
	
	
	// Test cases for calculateColumnTotal()
	
	/*
	 * this test case test for the correct output of two values in the column being added
	 */
	 @Test
	 public void calculateColumnTotalForTwoValues() {
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 10.0, .000000001d);
	 }
	 
		/*
		 * this test case test for the correct output of positive values in the column being added
		 */	 
	    @Test
	    public void calculateColumnTotal_positiveValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(1000));
	                one(values).getValue(1, 0);
	                will(returnValue(100000));
	                one(values).getValue(2, 0);
	                will(returnValue(100000000));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(100101000, result, 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output of negative values in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_negativeValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(-1000));
	                one(values).getValue(1, 0);
	                will(returnValue(-100000));
	                one(values).getValue(2, 0);
	                will(returnValue(-100000000));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(-100101000, result, 0.0000001d);
	    }
	    
	    
		/*
		 * this test case test for the correct output of mixed values in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_mixedValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(4));
	                one(values).getValue(0, 0);
	                will(returnValue(-1000));
	                one(values).getValue(1, 0);
	                will(returnValue(2000));
	                one(values).getValue(2, 0);
	                will(returnValue(-3000));
	                one(values).getValue(3, 0);
	                will(returnValue(4000));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(2000, result, 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output of zeros in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_zeroValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(0));
	                one(values).getValue(1, 0);
	                will(returnValue(0));
	                one(values).getValue(2, 0);
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(0.0, result, 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output if there was a single value in the column
		 */	
	    @Test
	    public void calculateColumnTotal_singleValue() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(1));
	                one(values).getValue(0, 0);
	                will(returnValue(42));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(42, result, 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output if there were no values in the column
		 */	
	    @Test
	    public void calculateColumnTotal_emptyValues2D() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(0.0, result, 0.0000001d);
	    }


		/*
		 * this test case test for the correct output of NaN in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_NaNValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(2));
	                one(values).getValue(0, 0);
	                will(returnValue(Double.NaN));
	                one(values).getValue(1, 0);
	                will(returnValue(Double.NaN));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertTrue(Double.isNaN(result));
	    }

	    
		/*
		 * this test case test for the correct output of large values in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_largeValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(1.0e20));
	                one(values).getValue(1, 0);
	                will(returnValue(2.0e20));
	                one(values).getValue(2, 0);
	                will(returnValue(0.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(3.0e20, result, 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output of large values in the column being added
		 */	
	    @Test
	    public void calculateColumnTotal_infinityValues() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(2));
	                one(values).getValue(0, 0);
	                will(returnValue(Double.POSITIVE_INFINITY));
	                one(values).getValue(1, 0);
	                will(returnValue(Double.POSITIVE_INFINITY));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertTrue(Double.isInfinite(result));
	    }

	    
	    
		/*
		 * this test case test for the correct output of non numerical values in the column being added
		 */	
//	    @Test(expected = InvalidParameterException.class)
//	    public void calculateColumnTotal_nonNumericValues() {
//	        // setup
//	        mockingContext.checking(new Expectations() {
//	            {
//	                one(values).getRowCount();
//	                will(returnValue(3));
//	                one(values).getValue(0, 0);
//	                will(returnValue(null)); // Non-numeric value
//	                one(values).getValue(1, 0);
//	                will(returnValue(null));
//	                one(values).getValue(2, 0);
//	                will(returnValue(null)); // Non-numeric value
//	            }
//	        });
//	        // exercise
//	        DataUtilities.calculateColumnTotal(values, 0);
//	        // verify
//
//	        // Sum of numeric values
//	        // tear-down: NONE in this test method
//	    }

	    
	    
	    
	    
	    
		// Test cases for getCumulativePercentages() 
	    
		/*
		 * this test case test for the correct output if valid values are used
		 */	
	    @Test
	    public void getCumulativePercentages_validKeysValues() {
	        mockingContexts.checking(new Expectations() {
	            {
	                allowing(keyedValues).getValue(0);
	                will(returnValue(5.0));

	                allowing(keyedValues).getKey(0);
	                will(returnValue("0"));

	                allowing(keyedValues).getValue(1);
	                will(returnValue(9.0));

	                allowing(keyedValues).getKey(1);
	                will(returnValue("1"));

	                allowing(keyedValues).getValue(2);
	                will(returnValue(2.0));

	                allowing(keyedValues).getKey(2);
	                will(returnValue("2"));

	                allowing(keyedValues).getItemCount();
	                will(returnValue(3));
	            }
	        });
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
	        System.out.print(result);
	        assertEquals(0.3125, (Double) result.getValue("0"), 0.0000001d);
	        assertEquals(0.875, (Double) result.getValue("1"), 0.0000001d);
	        assertEquals(1.0, (Double) result.getValue("2"), 0.0000001d);
	    }

	    
		/*
		 * this test case test for the correct output if no values are used
		 */	
	    @Test
	    public void getCumulativePercentages_emptyData() {
	        mockingContexts.checking(new Expectations() {
	            {
	            	allowing(keyedValues).getItemCount();
	                will(returnValue(0));
	            }
	        });
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
	        assertEquals(0, result.getItemCount());
	    }

	    
		/*
		 * this test case test for the correct output if a single value is used
		 */	
	    @Test
	    public void getCumulativePercentages_singleValue() {
	        mockingContexts.checking(new Expectations() {
	            {
	            	allowing(keyedValues).getItemCount();
	                will(returnValue(1));
	                allowing(keyedValues).getKey(0);
	                will(returnValue("0"));
	                allowing(keyedValues).getValue(0);
	                will(returnValue(10.0));
	            }
	        });
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
	        assertEquals(1, result.getItemCount());
	        assertEquals(1.0, (Double) result.getValue("0"), 0.0000001d);
	    }


		/*
		 * this test case test for the correct output if negative values are used
		 */	
	    @Test
	    public void getCumulativePercentages_negativeValues() {
	        mockingContexts.checking(new Expectations() {
	            {
	            	allowing(keyedValues).getItemCount();
	                will(returnValue(3));
	                allowing(keyedValues).getKey(0);
	                will(returnValue("0"));
	                allowing(keyedValues).getValue(0);
	                will(returnValue(-5.0));
	                allowing(keyedValues).getKey(1);
	                will(returnValue("1"));
	                allowing(keyedValues).getValue(1);
	                will(returnValue(-9.0));
	                allowing(keyedValues).getKey(2);
	                will(returnValue("2"));
	                allowing(keyedValues).getValue(2);
	                will(returnValue(-2.0));
	            }
	        });
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
	        assertEquals(0.3125, (Double) result.getValue("0"), 0.0000001d);
	        assertEquals(0.875, (Double) result.getValue("1"), 0.0000001d);
	        assertEquals(1.0, (Double) result.getValue("2"), 0.0000001d);
	    }
	    
	    
	    /*
		 * this test case test for the correct output if mixed values are used
		 */	
	    @Test
	    public void getCumulativePercentages_mixedValues() {
	        mockingContexts.checking(new Expectations() {
	            {
	                allowing(keyedValues).getItemCount();
	                will(returnValue(4));
	                allowing(keyedValues).getKey(0);
	                will(returnValue("0"));
	                allowing(keyedValues).getValue(0);
	                will(returnValue(5.0));
	                allowing(keyedValues).getKey(1);
	                will(returnValue("1"));
	                allowing(keyedValues).getValue(1);
	                will(returnValue(-3.0));
	                allowing(keyedValues).getKey(2);
	                will(returnValue("2"));
	                allowing(keyedValues).getValue(2);
	                will(returnValue(10.0));
	                allowing(keyedValues).getKey(3);
	                will(returnValue("3"));
	                allowing(keyedValues).getValue(3);
	                will(returnValue(-2.0));
	            }
	        });
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
	        assertEquals(0.5, (Double) result.getValue("0"), 0.0000001d);
	        assertEquals(0.2, (Double) result.getValue("1"), 0.0000001d);
	        assertEquals(1.2, (Double) result.getValue("2"), 0.0000001d);
	        assertEquals(1.0, (Double) result.getValue("3"), 0.0000001d);
	    }
	    
	    
//	    /*
//		 * this test case test for the correct output if invalid parameters are used
//		 */	
//	    @Test(expected = InvalidParameterException.class)
//	    public void getCumulativePercentages_nullData() {
//	        mockingContexts.checking(new Expectations() {
//	            {
//	                allowing(keyedValues).getItemCount();
//	                will(returnValue(0));
//	            }
//	        });
//	        KeyedValues result = DataUtilities.getCumulativePercentages(null);
//	    }
}
