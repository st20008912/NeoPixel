#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>

#define PIN 6
#define MAX_LED 121

SoftwareSerial BT(2, 3);

int valr = 0;
int valg = 0;
int valb = 0;
int n = 0;
char BTread;
String data;
bool flash=0;
bool writeone=0;
Adafruit_NeoPixel strip = Adafruit_NeoPixel( MAX_LED, PIN, NEO_GRB + NEO_KHZ800 );
 
uint32_t color[13] = {strip.Color(4,0,0),strip.Color(5,2,0),strip.Color(3,3,0),strip.Color(2,5,0),strip.Color(0,4,0),strip.Color(0,3,3),strip.Color(0,0,4),
                      strip.Color(0,2,5),strip.Color(5,0,2),strip.Color(3,0,3),strip.Color(2,0,5),strip.Color(0,0,0),strip.Color(2,2,2)};


void setup()
{
  Serial.begin(9600);
  BT.begin(9600);
  strip.begin();
  strip.show();
 

}
 
void loop()
{
   if (BT.available()){
      BTread = BT.read();
      data += String(BTread);
      if (BTread=='\n')
        data="";
   }
    if (BTread=='C'){
      strip.clear();
      strip.show();
      delay(30);
    }
    else if (BTread=='R'){
      flash=1;
      n=0;
      delay(30);
    }
    else if (flash==1){
      strip.setPixelColor(n,color[BTread-48]);
      Serial.print(String(n));
      Serial.print("  ");
      Serial.print(String(BTread-48));
      Serial.print("\n");
      n++;
      if (n==121){
        data="";
        n=0;
        flash=0;
        strip.show();
        delay(30);
      }
      delay(30);
    }
    else if (BTread=='A'){
      writeone=1;
      data="";
      delay(30);
    }
    else if (writeone==1&&data.length()==4){
     Serial.print(data);
     Serial.print("\n");
     n=(data[0]-48)*100+(data[1]-48)*10+(data[2]-48);
     if(data[3]-48>=0 && data[3]-48<=12 && n>=0 && n<=120){
      strip.setPixelColor(n,color[data[3]-48]);
      strip.show();
     }
     data=""; 
     writeone=0;
     delay(30);
     
    }
}
