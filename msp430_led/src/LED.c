#include "LED.h"
#include "../inc/msp430.h"

#ifndef TEST
int main(void)
#else
int TestableMain(void)
#endif
{

    P1DIR |= BIT0; 

    while(1)
    {
    make_ON();
    
    }
    return(0);
}

void make_ON()
{
    P1OUT |= BIT0;
}


/*
int main(void)
{
    //P1OUT |= BIT0;
    
    P1DIR |= BIT0; 

    while(1)
    {
    make_ON();
    
    }

}
*/