#include "switch.h"
#include "../inc/msp430.h"



#ifndef TEST
int main(void)
#else
int TestableMain(void)
#endif
{
    set_pin_as_output();    
    set_pin_as_input();

    P1REN |= BIT3;  //PIN 1.3 enabled for pull up/down resistor

    P1OUT |= BIT3;  //pin 1.3 chosen for pull up resistor

    P1OUT |= BIT0;  // pin 1.0 = ON initially

     while(1)
    {
        volatile int x = P1IN & BIT3; //If P1.3 is pressed, x = 0
        if(x == 0)
        {
          
            alternate_on_off();
        }
    
    
    }


    return(0);
}


void set_pin_as_output(void)// FOR LED
{
     P1DIR |= BIT0; //PIN 1.0 AS OUTPUT
}


void set_pin_as_input(void)// FOR SWITCH
{
    P1DIR &= ~BIT3;// PIN 1.3 AS INPUT
}

void alternate_on_off(void)
{
    P1OUT ^= BIT0;
}


