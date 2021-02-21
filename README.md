# NeoPixel
An Android Studio Project for using bluetooth to control WS2812B matrix display
https://youtu.be/k5v-9pPiN1o

# Android Studio APP
目前APP只能做到即時顯示、儲存/讀取和清除  
因為對Android Studio還在摸索階段  
藍牙連線的IP只能在程式碼裡面做修改，無法在APP上指定
關於儲存/讀取功能也還在輸入儲存名稱並輸入相同名稱才能做讀取  
未來將做下拉式選單來更方便的選擇讀取內容

# Arduino
Android Code 資料夾內為燒錄至Arduino Nano的檔案
利用HC-05藍牙模組以及WS2812B的NeoPixel函式庫做控制
