#include "../../unity/unity.h"
#include "../inc/msp430.h"
#include "../src/switch.h"


void test_if_led_pin_set_for_output(void)
{
    P1DIR = 0x00;
    set_pin_as_output();
    TEST_ASSERT_EQUAL_HEX8(BIT0, P1DIR);    

}
void test_if_switch_pin_set_for_input(void)
{
    P1DIR = 0xFF;
    set_pin_as_input();
    TEST_ASSERT_EQUAL_HEX8(0xFF & ~BIT3, P1DIR);    

}




void test_alternate_can_make_on()
{
	P1OUT = 0x00;
    alternate_on_off();	
	TEST_ASSERT_EQUAL_HEX8 (BIT0, P1OUT);


}
void test_alternate_can_make_off()
{
    P1OUT = 0x01;
    alternate_on_off();
    TEST_ASSERT_EQUAL_HEX8(0x00, P1OUT);

}


int main(void) {
    UNITY_BEGIN();
    RUN_TEST(test_if_led_pin_set_for_output);
    RUN_TEST(test_if_switch_pin_set_for_input);
    RUN_TEST(test_alternate_can_make_on);
    RUN_TEST(test_alternate_can_make_off);
    return UNITY_END();
}
