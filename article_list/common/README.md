# Common 

Here are some common data structure declarations 



## definitions

### callback

They may be used in an adapter or dialog.

| func            | desc                  |
| --------------- | --------------------- |
| CallBack        | empty call            |
| BooleanCallback | empty a boolean value |
| IntCallback     | empty a int value     |


## ext

This is the place to store extension functions

### ViewExt

This is an extension for View,includes

| func                   | desc                 |
| ---------------------- | -------------------- |
| setSafeOnClickListener | prevent double click |



### ActivityExt

This is an extension for Activity,inludes

| func          | desc                 |
| ------------- | -------------------- |
| setFullScreen | set full screen page |



## listener

| func              | desc                 |
| ----------------- | -------------------- |
| SafeClickListener | prevent double click |







# utils

Common utility classes.



## App Opener

This is a utility class for launching third-party apps.  including open `tiktok`

| func                  | desc                   |
| --------------------- | ---------------------- |
| openTikTokUserProfile | open ticktok profile   |
| openPlayStore         | open google play store |

## Text Utils

This is a  utility class for  helping deal text

| func        | desc                                                       |
| ----------- | ---------------------------------------------------------- |
| isAllDigits | Determine whether a character consists entirely of digits. |

## Device Utils

This is a utility class for getting device and system info

| func               | desc                            |
| ------------------ | ------------------------------- |
| getScreenWidthInPx | Get the screen width in pixels. |
| getScreenWidthInDp | Get the screen width in dp      |
| unitPxToDp         | Convert px to dp                |

## Share Utils

This is a utility class for share to others app

| func      | desc                    |
| --------- | ----------------------- |
| shareText | share text to other app |