#Haroopad繁體中文介面
#####Haroopad in Traditional Chinese

是不是正在因為Haroopad一載下來就是簡體中文而感到困擾呢?

現在你只要花不到三分鐘，就可以把你Haroopad的介面改成繁體中文喔!

##Usage
1. 將`\zh-tw`資料夾底下的四個 json 檔載下
2. 找到 Haroopad 的語言資料夾，Windows 系統通常會在```C:\Users\**Username**\AppData\Roaming\Haroo Studio\Haroopad\Libraries\.locales\```
3. 將中文的資料夾`\zh`其中的四個 json 檔換成剛剛載下來的四個 json 檔
4. 重新啟動 Haroopad
5. Nice!

##Folder
- **src** 
	- 恩，裡面就是存source code
- **zh**
	- 裡面存著原始簡體中文的四個json檔
- **langs**
	- 存有繁簡轉換對照表的csv檔
	- 還有轉換完成輸出的json檔
- **zh-tw**
	- 這裡存了最後轉換完成的json檔
	- 跟`\langs`資料夾裡面的差別是有些用語手動修改成繁體中文



