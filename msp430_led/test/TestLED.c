#include "../../unity/unity.h"
#include "../inc/msp430.h"
#include "LED.h"

void test_LED()
{
	P1OUT = 0x00;
	make_ON();	
	TEST_ASSERT_EQUAL_HEX8 (BIT0, P1OUT);


}



int main(void) {
    UNITY_BEGIN();
    RUN_TEST(test_LED);
    return UNITY_END();
}
