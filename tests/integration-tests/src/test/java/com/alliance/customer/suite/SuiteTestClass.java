package com.alliance.customer.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.alliance.customer.ForthOneTest;
import com.alliance.customer.MyJPAUnitTest;
import com.alliance.customer.MySecondUnitTest;
import com.alliance.customer.ThirdOneTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MyJPAUnitTest.class,
        MySecondUnitTest.class,
        ThirdOneTest.class,
        ForthOneTest.class
})
public class SuiteTestClass {
}
