#include <Arduino.h>
#include <Wire.h>


const int buttonPin = 2;
int count = 0;
const int delayInterval = 200;
int nextTime = 0;
int lastTime = 0;
bool buttonStatus = false;
int lastDebouncingTime = 0;

void buttonPressed() {
  int now = millis();
  //count ++;
  /*if (digitalRead(buttonPin) == lastButtonStatus)  {
    lastDebouncingTime = millis();
  }*/
  
  if (now > lastTime + delayInterval && buttonStatus && digitalRead(buttonPin) == HIGH)  {
    nextTime = now + delayInterval;
    buttonStatus = false;
  }
  if (now > lastTime + delayInterval && !buttonStatus && digitalRead(buttonPin) == LOW) {
    count ++;
    nextTime = now + delayInterval;
    buttonStatus = true;
  }
  //lastTime = millis();
/*  if (millis() - lastDebouncingTime > delayInterval) {
    if (digitalRead(buttonPin)
  }*/
}

void setup() {
  Serial.begin(9600);

  pinMode(buttonPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
}

void loop() {
  Serial.println(count);
  //Serial.println(digitalRead(buttonPin));
}
