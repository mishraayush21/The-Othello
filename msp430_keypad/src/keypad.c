#include "../inc/msp430.h"


void output_the_key_pressed(uint8_t hex_bits)
{

	if (hex_bits & BIT0)
		P1OUT |= BIT7;
	else
		P1OUT &= ~BIT7;
	
	if  (hex_bits & BIT1)
		P1OUT |= BIT6;
	else
		P1OUT &= ~BIT6;

	if (hex_bits & BIT2)
		P2OUT |= BIT5;
	else
		P2OUT &= ~BIT5;
	
	if (hex_bits & BIT3)
		P2OUT |= BIT4;
	else
		P2OUT &= ~BIT4;

}




#ifndef TEST
int main(void)
#else
int TestableMain(void)
#endif
{

	char keys[4][4] = {
                    {'1', '2', '3', 'A'},
                    {'4', '5', '6', 'B'},
                    {'7', '8', '9', 'C'},
                    {'*', '0', '#', 'D'}
                } ;

    uint32_t keys_in_hex[4][4] = {
						   {0x00, 0x01, 0x02, 0x03},
						   {0x04, 0x05, 0x06, 0x07},
						   {0x08, 0x09, 0x0A, 0x0B},
						   {0x0C, 0x0D, 0x0E, 0x0F}
					};


    P1DIR |= (BIT0 | BIT1 | BIT2 | BIT3 | BIT7 | BIT6);
	P2DIR |= (BIT4 | BIT5);

    uint32_t io_pins[4] = {BIT0, BIT1, BIT2, BIT3};
	uint8_t i,j;

	while(1) 
	{
		for(i = 0; i < 4; i++)
		{
			P1OUT |= io_pins[i];

			for(j = 0; j < 4; j++)
			{
				if(P2IN & io_pins[j])
				{
					output_the_key_pressed(keys_in_hex[i][j]);
				}
			}
			P1OUT &= ~(BIT0 | BIT1 | BIT2 | BIT3);
		}
	}

	return(0);
}

/*
Outputs will be represented on an array of four LEDs, which 
represent a binary value corresponding to the pressed key.
*/

