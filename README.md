BlurOMatic-Kotlin Solution 
===================================
The BlurOMatic application takes an image that is displayed to the user, and gives them the option to add blur to it. The action of blurring the image, and saving the image to the user device uses the WorkManager library. The WorkManager library provides a unified API for deferrable one-off or recurring background tasks that need guaranteed execution. When the user first launches the application they will be presented with the image, and three options corresponding with the level of blur they want to add to that picture. 
<br>
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/57158277/170210786-d01bc896-ad35-47f8-8e49-7cd6102e67a7.png" width="250">
</p>
Depending on which level of blur (1, 2, or 3) they choose, the application will prompt notifications stating "Blurring Image" before saving the file locally to the device. If the user clicks on the second option, then the image will be blurred twice when the go button is clicked. When the user selects the go button however, the first work request that is part of the chain is to cleanup any temporary images that was created during the blurring process. 
<br>
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/57158277/170211369-35a80d37-60a5-444f-93fb-0f47ea108e02.png" width="250">
  <img src="https://user-images.githubusercontent.com/57158277/170211666-6b308975-929e-4440-94ed-a7f2e5660bc0.png" width="250">
  <img src="https://user-images.githubusercontent.com/57158277/170211710-8e465c21-a794-4ae8-9586-831c6f254e14.png" width="250">
</p>
<br>
<br>
After repeating the blurring work request twice, the user will be shown the notification for when the WorkManager is executing the Save Image work request. Once the final work request is complete, the See File button will be visible. Here the user can view the new blurred image they have created. 
<p align="center">
  <img src="https://user-images.githubusercontent.com/57158277/170211821-37892974-1385-425f-a91a-e3670e13ef81.png" width="250">
  <img src="https://user-images.githubusercontent.com/57158277/170211961-b2770570-a2c6-4ab4-93f5-772574c942a5.png" width="250">
  <img src="https://user-images.githubusercontent.com/57158277/170212023-abbdc88d-4a99-4cf9-ac0b-ba142f61e527.png" width="250">
</p>
<br>
<br>
Looking back at all the previous screenshots, there one thing common in between all of them is that the device is charging. A constraint was added so that the save file work request would only execute if the device is charging. Otherwise the image will not be saved. Once the blur requests are processed, the application will just hang with a continous loading animation, until the user clicks on the cancel work. 
<br>
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/57158277/170213909-3ab17b43-fa48-4cff-ad7a-994006e7b9cc.png" width="250">
</p>
