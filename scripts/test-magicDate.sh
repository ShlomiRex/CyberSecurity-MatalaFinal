apktool b /home/shlomi/Desktop/CyberSecurity-MatalaFinal/MagicDateWithMalware -o /home/shlomi/Desktop/CyberSecurity-MatalaFinal/magicDateWithMalware.apk

jarsigner -keystore ../mykey.keystore -storepass shlomi333 /home/shlomi/Desktop/CyberSecurity-MatalaFinal/magicDateWithMalware.apk shlomi

adb install /home/shlomi/Desktop/CyberSecurity-MatalaFinal/magicDateWithMalware.apk

adb shell am start -n com.MagicDate/.MagicDate