命令 : apkpatch.bat -f new.apk -t old.apk -o output1 -k debug.keystore -p android -a androiddebugkey -e android

-f <new.apk> ：新版本
-t <old.apk> : 旧版本
-o <output> ： 输出目录
-k <keystore>： 打包所用的keystore
-p <password>： keystore的密码
-a <alias>： keystore 用户别名
-e <alias password>： keystore 用户别名密码